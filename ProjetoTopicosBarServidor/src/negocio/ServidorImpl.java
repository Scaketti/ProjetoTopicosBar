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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import visao.TelaServidor;

/**
 *
 * @author Scaketti
 */
public class ServidorImpl extends UnicastRemoteObject implements ServidorBarInterface {

    TelaServidor tela;

    public ServidorImpl(TelaServidor tela) throws RemoteException {
        super();
        this.tela = tela;
    }

    //TERMINAL 
    @Override
    public int conectarAoServidor(String nome, String ip, int porta, int numTerminal) throws RemoteException {

        ClienteTerminal cConectando = new ClienteTerminal();
        cConectando.setNumTerminal(numTerminal);
        cConectando.setNome(nome);
        cConectando.setIp(ip);
        cConectando.setPorta(porta);

        String texto = tela.getLogMsgClienteTerminal().concat("[" + getDateTime() + "] Cliente (" + nome + ") conectou ao servidor.\n");

        tela.setLogMsgClienteTerminal(texto);

        if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Clientes")) {
            tela.getTxtMovimentacao().setText(tela.getLogMsgClienteTerminal());
        }

        return 0;
    }

    @Override
    public int DesconectarDoServidor(int numTerminal) throws RemoteException {

        return -1;
    }

    public int realizarPedido(Produto pProduto, String nomeCliente, String ip, int porta) throws RemoteException {

        String texto = tela.getLogMsgClienteTerminal().concat("[" + getDateTime() + "] Cliente (" + nomeCliente + ") realizou um pedido.\n");

        tela.setLogMsgClienteTerminal(texto);

        if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Clientes")) {
            tela.getTxtMovimentacao().setText(tela.getLogMsgClienteTerminal());
        }

        try {
            
            ClienteTerminalInterface cliente = (ClienteTerminalInterface) Naming.lookup("rmi://" + ip + ":" + porta + "/bar");
            System.out.println("negocio.ServidorImpl.realizarPedido()");
            cliente.notificaAlteracaoCardapio();

        } catch (Exception e) {
            System.out.println("Erro: Mensagem: " + e.getMessage());
        }
        
        return 0;
    }

    //CAIXA
    @Override
    public int conectarAoServidor(String nome, String ip, int porta) throws RemoteException {
        ClienteCaixa cConectando = new ClienteCaixa();
        cConectando.setNome(nome);
        cConectando.setIp(ip);
        cConectando.setPorta(porta);

        String texto = tela.getLogMsgClienteCaixa().concat("[" + getDateTime() + "] Funcionário (" + nome + ") conectou ao servidor.\n");

        tela.setLogMsgClienteCaixa(texto);

        if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Atendentes")) {
            tela.getTxtMovimentacao().setText(tela.getLogMsgClienteCaixa());
        }

        return 0;
    }

    @Override
    public int DesconectarDoServidor(String ip) throws RemoteException {

        //tela.getLogMsgClienteCaixa().concat("Funcionário (" + nomeFuncionario + ") inseriu um novo produto.\n");
        if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Atendentes")) {
            tela.getTxtMovimentacao().setText(tela.getLogMsgClienteCaixa());
        }

        return -1;
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

        String texto = tela.getLogMsgClienteCaixa().concat("[" + getDateTime() + "] Funcionário (" + nomeFuncionario + ") pesquisou um produto (" + nomeProduto + ").\n");

        tela.setLogMsgClienteCaixa(texto);

        if (tela.getCmbTipoMovimentacao().getItemAt(tela.getCmbTipoMovimentacao().getSelectedIndex()).equals("Atendentes")) {
            tela.getTxtMovimentacao().setText(tela.getLogMsgClienteCaixa());
        }

        return null;
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

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
