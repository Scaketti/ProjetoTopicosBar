/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import visao.TelaTerminal;

/**
 *
 * @author Scaketti
 */
public class ClienteImpl extends UnicastRemoteObject implements ClienteTerminalInterface{
    
    public ClienteImpl(TelaTerminal tCliente) throws RemoteException{
        super();
    }
}
