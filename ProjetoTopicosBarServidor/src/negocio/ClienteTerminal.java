/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.rmi.Naming;
import sun.rmi.registry.RegistryImpl;

/**
 *
 * @author Scaketti
 */
public class ClienteTerminal{
    
    private int numTerminal;
    
    
    public ClienteTerminal(){
        super();
    }

    /**
     * @return the numTerminal
     */
    public int getNumTerminal() {
        return numTerminal;
    }

    /**
     * @param numTerminal the numTerminal to set
     */
    public void setNumTerminal(int numTerminal) {
        this.numTerminal = numTerminal;
    }
}
