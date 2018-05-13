/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;
import visao.TelaServidor;

/**
 *
 * @author Scaketti
 */
public class ServidorImpl extends UnicastRemoteObject implements ServidorBarInterface {
    
    public ServidorImpl(TelaServidor tela) throws RemoteException{
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
    public int insereProduto() throws RemoteException {
        return 0;
    }

    @Override
    public int alteraProduto() throws RemoteException {
        return 0;
    }
}
