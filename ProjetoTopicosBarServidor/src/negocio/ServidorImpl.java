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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import visao.TelaServidor;

/**
 *
 * @author Scaketti
 */
public class ServidorImpl extends UnicastRemoteObject implements ServidorBarInterface {

    public ServidorImpl(TelaServidor tela) throws RemoteException {
        super();
    }

    @Override
    public int conectarAoServidor(String nome, String ip, int porta, int numTerminal) throws RemoteException {
        JOptionPane.showMessageDialog(null, "Nome do Cliente conectado: " + nome);

        /*Boolean valido = false;

        //Verifica se possui algum cliente conectado com o mesmo apelido
        for (Cliente c : clientesConectados) {
            valido = verificaApelido(apelido, c);
            if (valido) {
                return 1;
            }
        }

        if (!valido) { //Caso não exista nenhum cliente com o mesmo nome, tenta conectar
            Cliente clienteConectando = new Cliente(apelido, nome, ipCliente, portaCliente);
            clientesConectados.add(clienteConectando);
            tServidor.insereClienteLista(clienteConectando); //Chama método que insere o cliente na lista da tela
            
            for (Cliente cl : clientesConectados) { //Notifica os clientes 
                if (!cl.getApelido().equals(apelido)) {
                    notificarConexao(clienteConectando, cl); //Notifica a pessoa que ja está conectada
                    notificarConexao(cl, clienteConectando); //Recebe "ping" da pessoa que ja está conectada
                }
            }
            return 0;
        }*/
        return 0;
    }

    @Override
    public int DesconectarDoServidor(int numTerminal) throws RemoteException {
        /*Boolean valido = false;

        //Verifica se o mesmo ja está conectado
        for (Cliente c : clientesConectados) {
            valido = verificaApelido(apelido, c);
            if (valido) { //Caso ele esteja conectado, notifica a desconexão
                for (Cliente cl : clientesConectados) {
                    if (!cl.getApelido().equals(apelido)) {
                        notificarDesconexao(apelido, cl);
                    }
                }
                clientesConectados.remove(c);
                tServidor.removeClienteLista(c);
                break;
            }
        }*/
        return -1;
    }

    @Override
    public int conectarAoServidor(String nome, String ip, int porta) throws RemoteException {
        JOptionPane.showMessageDialog(null, "Nome do Atendente conectado: " + nome);

        /*Boolean valido = false;

        //Verifica se possui algum cliente conectado com o mesmo apelido
        for (Cliente c : clientesConectados) {
            valido = verificaApelido(apelido, c);
            if (valido) {
                return 1;
            }
        }

        if (!valido) { //Caso não exista nenhum cliente com o mesmo nome, tenta conectar
            Cliente clienteConectando = new Cliente(apelido, nome, ipCliente, portaCliente);
            clientesConectados.add(clienteConectando);
            tServidor.insereClienteLista(clienteConectando); //Chama método que insere o cliente na lista da tela
            
            for (Cliente cl : clientesConectados) { //Notifica os clientes 
                if (!cl.getApelido().equals(apelido)) {
                    notificarConexao(clienteConectando, cl); //Notifica a pessoa que ja está conectada
                    notificarConexao(cl, clienteConectando); //Recebe "ping" da pessoa que ja está conectada
                }
            }
            return 0;
        }*/
        return 0;
    }

    @Override
    public int DesconectarDoServidor(String ip) throws RemoteException {
        /*Boolean valido = false;

        //Verifica se o mesmo ja está conectado
        for (Cliente c : clientesConectados) {
            valido = verificaApelido(apelido, c);
            if (valido) { //Caso ele esteja conectado, notifica a desconexão
                for (Cliente cl : clientesConectados) {
                    if (!cl.getApelido().equals(apelido)) {
                        notificarDesconexao(apelido, cl);
                    }
                }
                clientesConectados.remove(c);
                tServidor.removeClienteLista(c);
                break;
            }
        }*/
        return -1;
    }

    @Override
    public int insereProduto(Produto p, String nome) throws RemoteException {
        try {
            Connection conn = Conexao.abrir();
            ProdutoDAO pDAO = new ProdutoDAO(conn);

            System.out.println("Nome: " + p.getNome());

            System.out.println("retorno: " + pDAO.insereProduto(p));
            
            //insere log sobre o funcionario

            conn.close();
            return 0;
        } catch (Exception ex) {
            System.out.println("Erro");
            return -1;
        }
    }

    @Override
    public int alteraProduto(Produto p, String nome) throws RemoteException {
        try {
            Connection conn = Conexao.abrir();
            ProdutoDAO pDAO = new ProdutoDAO(conn);

            pDAO.alteraProduto(p);
            
            //insere log sobre o funcionario

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
            
            //insere log sobre o funcionario
            
            conn.close();
            return pBusca;
        } catch (Exception ex) {
            System.out.println("Erro");
            return null;
        }
    }
    
    @Override
    public Produto pesquisaProduto(String nomeProduto, String nomeFuncionario) throws RemoteException {
        return null;
    }

    @Override
    public int excluiProduto(Produto p, String nome) throws RemoteException {
        return 0;
    }
}
