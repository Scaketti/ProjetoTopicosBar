/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import java.rmi.Naming;
import sun.rmi.registry.RegistryImpl;

/**
 *
 * @author foxdie
 */
public class ClienteTerminal implements Serializable{
    private String nome;
    private int numTerminal;
    private String ip = "127.0.0.1";
    private int porta = 1100;
    
    public ClienteTerminal(String nome){
        this.nome = nome;
        this.numTerminal = porta;
    }
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
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

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the porta
     */
    public int getPorta() {
        return porta;
    }

    /**
     * @param porta the porta to set
     */
    public void setPorta(int porta) {
        this.porta = porta;
    }
}
