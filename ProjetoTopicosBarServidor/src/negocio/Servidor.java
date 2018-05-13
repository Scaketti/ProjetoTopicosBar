/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import visao.TelaServidor;
import java.rmi.Naming;
import java.rmi.RemoteException;
import sun.rmi.registry.RegistryImpl;

/**
 *
 * @author Scaketti
 */
public class Servidor {
    
    public Servidor(int porta, TelaServidor tela){
        try{
            //Registrando o serviço em uma determinada porta.
            RegistryImpl registryImpl = new RegistryImpl(porta);    

            //Instanciando a classe ServidorImpl que é do tipo ServidorInterface.
            ServidorBarInterface servidor = (ServidorBarInterface) new ServidorImpl(tela);  
            
            //Registra o Servidor
            Naming.rebind("rmi://" + "localhost" + ":" + porta + "/bar", servidor); 
            
        }catch(Exception e){
            System.out.println("Erro : Mensagem : " + e.getMessage());
        }
    }
    
}
