/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Scaketti
 */
public interface ClienteCaixaInterface extends Remote {

    public void atualizaPedidos(ArrayList<Pedido> pedidos) throws RemoteException;

}
