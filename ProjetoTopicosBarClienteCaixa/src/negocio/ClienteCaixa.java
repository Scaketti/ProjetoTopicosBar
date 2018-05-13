/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.rmi.Naming;
import sun.rmi.registry.RegistryImpl;
import visao.TelaCaixa;

/**
 *
 * @author Scaketti
 */
public class ClienteCaixa {

    private String nome;
    private String ip = "127.0.0.1";
    private int porta = 1101;
    
    public ClienteCaixa(String nome){
        this.nome = nome;
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
    
    public void registraCliente(TelaCaixa tCliente) {
        try {
            //Registrando o serviço em uma determinada porta.
            RegistryImpl registryImpl = new RegistryImpl(getPorta());

            //Instanciando a classe ServidorImpl que é do tipo ServidorInterface.
            ClienteCaixaInterface cliente = new ClienteImpl(tCliente);

            //Registra o Servidor
            Naming.rebind("rmi://" + getIp() + ":" + getPorta() + "/bar", cliente);

        } catch (Exception e) {
            System.out.println("Erro : Mensagem : " + e.getMessage());
        }
    }
}
