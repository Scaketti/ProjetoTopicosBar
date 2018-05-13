/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Scaketti
 */
public interface ServidorBarInterface extends Remote {

    //TERMINAL
    
    //Conexão do terminal com o servidor
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int conectarAoServidor(String nome, String ip, int porta, int numTerminal) throws RemoteException;

    //Desconecta terminal do servidor
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int DesconectarDoServidor(int numTerminal) throws RemoteException;

    
    //CAIXA
    
    //Conexão do caixa com o servidor
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int conectarAoServidor(String nome, String ip, int porta) throws RemoteException;

    //Desconecta caixa do servidor
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int DesconectarDoServidor(String ip) throws RemoteException;
    
    //Insere produto no estoque
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int insereProduto() throws RemoteException;
    
    //Altera produto do estoque
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int alteraProduto() throws RemoteException;
}