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
import visao.TelaTerminal;

/**
 *
 * @author Scaketti
 */
public class ClienteImpl extends UnicastRemoteObject implements ClienteTerminalInterface {
    TelaTerminal tCliente = null;
    public ClienteImpl(TelaTerminal tCliente) throws RemoteException {
        super();
        this.tCliente = tCliente;
    }

    public int notificaAlteracaoCardapio() {
        System.out.println("negocio.ClienteImpl.notificaAlteracaoCardapio()");
        tCliente.getCardapio();
        return 0;
    }
}
