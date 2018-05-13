/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import visao.TelaCaixa;

/**
 *
 * @author foxdie
 */
public class ClienteImpl extends UnicastRemoteObject implements ClienteCaixaInterface{
    
    public ClienteImpl(TelaCaixa tCliente) throws RemoteException{
        super();
    }
}
