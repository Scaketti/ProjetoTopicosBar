/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.Conexao;
import dados.ProdutoDAO;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import visao.TelaServidor;

/**
 *
 * @author Scaketti
 */
public class ServidorImpl extends UnicastRemoteObject implements ServidorBarInterface {

    TelaServidor tela;

    ArrayList<Pedido> listaPedidos = new ArrayList();
    int nPedidoTotal = 0;

    public ServidorImpl(TelaServidor tela) throws RemoteException {
        super();
        this.tela = tela;
    }

    //TERMINAL 
    @Override
    public int conectarAoServidor(String nome, String ip, int porta, int numTerminal) throws RemoteException {
        try {
            ClienteTerminal cConectando = new ClienteTerminal(nome);
            cConectando.setNumTerminal(numTerminal);
            cConectando.setIp(ip);
            cConectando.setPorta(porta);

            tela.getcTerminalConectados().add(cConectando);

            String texto = tela.getLogMsgClienteTerminal().concat("[" + getDateTime() + "] Cliente (" + nome + ") conectou ao servidor.\n");

            tela.setLogMsgClienteTerminal(texto);

            if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Clientes")) {
                tela.getTxtMovimentacao().setText(tela.getLogMsgClienteTerminal());
            }

            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int DesconectarDoServidor(String nome, int nTerminal, ArrayList<Produto> listaProdutos, float valorTotal) throws RemoteException {
        for (ClienteTerminal c : tela.getcTerminalConectados()) {
            if (c.getNome().equals(nome)) {
                Pedido novoPedido = new Pedido(c, listaProdutos, valorTotal);
                novoPedido.setnPedido(nPedidoTotal++);

                listaPedidos.add(novoPedido);

                try {
                    for (ClienteCaixa caixa : tela.getcCaixaConectados()) {
                        ClienteCaixaInterface cliente = (ClienteCaixaInterface) Naming.lookup("rmi://" + caixa.getIp() + ":" + caixa.getPorta() + "/bar");
                        cliente.atualizaPedidos(listaPedidos);
                    }

                } catch (Exception e) {
                    System.out.println("Erro: Mensagem: " + e.getMessage());
                }

                tela.getcTerminalConectados().remove(c);

                String texto = tela.getLogMsgClienteTerminal().concat("[" + getDateTime() + "] Cliente (" + nome + "), mesa (" + nTerminal + ") fechou a conta e desconectou do servidor.\n");

                tela.setLogMsgClienteTerminal(texto);

                if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Clientes")) {
                    tela.getTxtMovimentacao().setText(tela.getLogMsgClienteTerminal());
                }
                return 0;
            }
        }
        return 1;
    }

    @Override
    public int requisitarProduto(Produto pProduto, String nomeCliente, String ip, int porta) throws RemoteException {
        Produto produtoBanco = pesquisaProduto(pProduto.getNome(), "Sistema");

        if ((produtoBanco.getQtd() - pProduto.getQtd()) >= 0) {

            produtoBanco.setQtd(produtoBanco.getQtd() - pProduto.getQtd());
            alteraProduto(produtoBanco, "Sistema");

            String texto = tela.getLogMsgClienteTerminal().concat("[" + getDateTime() + "] Cliente (" + nomeCliente + ") pediu um novo produto.\n");

            tela.setLogMsgClienteTerminal(texto);

            if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Clientes")) {
                tela.getTxtMovimentacao().setText(tela.getLogMsgClienteTerminal());
            }

            try {
                for (ClienteTerminal c : tela.getcTerminalConectados()) {
                    ClienteTerminalInterface cliente = (ClienteTerminalInterface) Naming.lookup("rmi://" + c.getIp() + ":" + c.getPorta() + "/bar");
                    cliente.notificaAlteracaoCardapio();
                }
            } catch (Exception e) {
                System.out.println("Erro: Mensagem: " + e.getMessage());
            }
            return 0;
        }
        return 1;
    }

    //CAIXA
    @Override
    public int conectarAoServidor(String nome, String ip, int porta) throws RemoteException {
        try {
            ClienteCaixa cConectando = new ClienteCaixa();

            cConectando.setNome(nome);
            cConectando.setIp(ip);
            cConectando.setPorta(porta);

            tela.getcCaixaConectados().add(cConectando);

            String texto = tela.getLogMsgClienteCaixa().concat("[" + getDateTime() + "] Funcionário (" + nome + ") conectou ao servidor.\n");

            tela.setLogMsgClienteCaixa(texto);

            if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Atendentes")) {
                tela.getTxtMovimentacao().setText(tela.getLogMsgClienteCaixa());
            }

            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    @Override
    public int DesconectarDoServidor(String nomeFuncionario) throws RemoteException {

        for (ClienteCaixa c : tela.getcCaixaConectados()) {
            if (c.getNome().equals(nomeFuncionario)) {
                tela.getcCaixaConectados().remove(c);

                String texto = tela.getLogMsgClienteCaixa().concat("[" + getDateTime() + "] Funcionário (" + nomeFuncionario + ") inseriu um novo produto.\n");

                tela.setLogMsgClienteCaixa(texto);

                if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Atendentes")) {
                    tela.getTxtMovimentacao().setText(tela.getLogMsgClienteCaixa());
                }

                return 0;
            }
        }
        return 1;
    }

    @Override
    public int insereProduto(Produto pNovo, String nomeFuncionario) throws RemoteException {
        try {
            Connection conn = Conexao.abrir();
            ProdutoDAO pDAO = new ProdutoDAO(conn);

            pDAO.insereProduto(pNovo);

            String texto = tela.getLogMsgClienteCaixa().concat("[" + getDateTime() + "] Funcionário (" + nomeFuncionario + ") inseriu um novo produto (" + pNovo.getNome() + ").\n");

            tela.setLogMsgClienteCaixa(texto);

            if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Atendentes")) {
                tela.getTxtMovimentacao().setText(tela.getLogMsgClienteCaixa());
            }

            conn.close();

            try {
                ClienteTerminalInterface cliente = (ClienteTerminalInterface) Naming.lookup("rmi://" + "127.0.0.1" + ":" + "1100" + "/bar");
                System.out.println("negocio.ServidorImpl.insereProduto()");
                cliente.notificaAlteracaoCardapio();

            } catch (Exception e) {
                System.out.println("Erro: Mensagem: " + e.getMessage());
            }

            return 0;
        } catch (Exception ex) {
            System.out.println("Erro");
            return -1;
        }
    }

    @Override
    public int alteraProduto(Produto p, String nomeFuncionario) throws RemoteException {
        try {
            Connection conn = Conexao.abrir();
            ProdutoDAO pDAO = new ProdutoDAO(conn);

            pDAO.alteraProduto(p);

            String texto = tela.getLogMsgClienteCaixa().concat("[" + getDateTime() + "] Funcionário (" + nomeFuncionario + ") alterou os dados de um produto (" + p.getNome() + ").\n");

            tela.setLogMsgClienteCaixa(texto);

            if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Atendentes")) {
                tela.getTxtMovimentacao().setText(tela.getLogMsgClienteCaixa());
            }

            try {
                ClienteTerminalInterface cliente = (ClienteTerminalInterface) Naming.lookup("rmi://" + "127.0.0.1" + ":" + "1100" + "/bar");

                cliente.notificaAlteracaoCardapio();

            } catch (Exception e) {
                System.out.println("Erro: Mensagem: " + e.getMessage());
            }

            conn.close();
            return 0;
        } catch (Exception ex) {
            System.out.println("Erro");
            return -1;
        }
    }

    @Override
    public ArrayList<Produto> pesquisaProduto(String nomeFuncionario) throws RemoteException {
        try {
            Connection conn = Conexao.abrir();
            ProdutoDAO pDAO = new ProdutoDAO(conn);
            ArrayList<Produto> pBusca;

            pBusca = pDAO.pesquisaProduto();

            conn.close();
            return pBusca;
        } catch (Exception ex) {
            System.out.println("Erro");
            return null;
        }
    }

    @Override
    public Produto pesquisaProduto(String nomeProduto, String nomeFuncionario) throws RemoteException {

        try {
            Connection conn = Conexao.abrir();
            ProdutoDAO pDAO = new ProdutoDAO(conn);
            ArrayList<Produto> pBusca;
            Produto produtoEncontrado = null;
            pBusca = pDAO.pesquisaProduto();

            for (Produto p : pBusca) {
                if (p.getNome().equals(nomeProduto)) {
                    produtoEncontrado = p;

                    String texto = tela.getLogMsgClienteCaixa().concat("[" + getDateTime() + "] Funcionário (" + nomeFuncionario + ") pesquisou um produto (" + nomeProduto + ").\n");

                    tela.setLogMsgClienteCaixa(texto);

                    if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Atendentes")) {
                        tela.getTxtMovimentacao().setText(tela.getLogMsgClienteCaixa());
                    }
                }
            }

            conn.close();
            return produtoEncontrado;
        } catch (Exception ex) {
            System.out.println("Erro");
            return null;
        }
    }

    @Override
    public int excluiProduto(Produto pExcluir, String nomeFuncionario) throws RemoteException {
        try {
            Connection conn = Conexao.abrir();
            ProdutoDAO pDAO = new ProdutoDAO(conn);

            pDAO.excluiProduto(pExcluir.getIdProduto());

            String texto = tela.getLogMsgClienteCaixa().concat("[" + getDateTime() + "] Funcionário (" + nomeFuncionario + ") excluiu um produto (" + pExcluir.getNome() + ").\n");

            tela.setLogMsgClienteCaixa(texto);

            if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Atendentes")) {
                tela.getTxtMovimentacao().setText(tela.getLogMsgClienteCaixa());
            }

            try {
                ClienteTerminalInterface cliente = (ClienteTerminalInterface) Naming.lookup("rmi://" + "127.0.0.1" + ":" + "1100" + "/bar");
                cliente.notificaAlteracaoCardapio();

            } catch (Exception e) {
                System.out.println("Erro: Mensagem: " + e.getMessage());
            }
            conn.close();
            return 0;
        } catch (Exception ex) {
            System.out.println("Erro");
            return -1;
        }
    }

    @Override
    public int finalizaConta(Pedido contaFechada, String nomeFuncionario) throws RemoteException {

        for (Pedido p : listaPedidos) {
            if (p.getnPedido() == contaFechada.getnPedido()) {
                listaPedidos.remove(p);
            }
        }

        try {
            for (ClienteCaixa caixa : tela.getcCaixaConectados()) {
                ClienteCaixaInterface cliente = (ClienteCaixaInterface) Naming.lookup("rmi://" + caixa.getIp() + ":" + caixa.getPorta() + "/bar");
                cliente.atualizaPedidos(listaPedidos);
            }

            String texto = tela.getLogMsgClienteCaixa().concat("[" + getDateTime() + "] Funcionário (" + nomeFuncionario + ") fechou uma conta da mesa " + contaFechada.getMesa().getNumTerminal() + ".\n");

            tela.setLogMsgClienteCaixa(texto);

            if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Atendentes")) {
                tela.getTxtMovimentacao().setText(tela.getLogMsgClienteCaixa());
            }

            return 0;

        } catch (Exception e) {
            System.out.println("Erro: Mensagem: " + e.getMessage());
            return 1;
        }
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
