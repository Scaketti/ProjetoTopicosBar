/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.rmi.Naming;
import sun.rmi.registry.RegistryImpl;
import visao.TelaTerminal;

/**
 *
 * @author foxdie
 */
public class ClienteTerminal{
    private String nome;
    private int numTerminal;
    private String ip = "127.0.0.1";
    private int porta = 1100;
    
    public ClienteTerminal(int numTerminal, String nome){
        this.nome = nome;
        this.numTerminal = numTerminal;
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
    
    public void registraCliente(TelaTerminal tCliente) {
        try {
            //Registrando o serviço em uma determinada porta.
            RegistryImpl registryImpl = new RegistryImpl(porta);

            //Instanciando a classe ServidorImpl que é do tipo ServidorInterface.
            ClienteTerminalInterface cliente = new ClienteImpl(tCliente);

            //Registra o Servidor
            Naming.rebind("rmi://" + ip + ":" + porta + "/bar", cliente);

        } catch (Exception e) {
            System.out.println("Erro : Mensagem : " + e.getMessage());
        }
    }
}
