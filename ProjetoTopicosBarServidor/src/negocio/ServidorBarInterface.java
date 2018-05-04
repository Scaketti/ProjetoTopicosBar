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
public interface ServidorBarInterface extends Remote{
    //Conexão com o servidor
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int conectarAoServidor(int numeroTerminal, String nome) throws RemoteException;
    
    //Desconecta do servidor
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int DesconectarDoServidor(int numeroTerminal) throws RemoteException;
}
