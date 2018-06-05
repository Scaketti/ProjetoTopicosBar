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
public interface ServidorBarInterface extends Remote {

    //TERMINAL
    
    //Conexão do terminal com o servidor
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int conectarAoServidor(String nome, String ip, int porta, int numTerminal) throws RemoteException;

    //Desconecta terminal do servidor
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int DesconectarDoServidor(int numTerminal) throws RemoteException;

    public int realizarPedido(Produto pProduto, String nomeCliente, String ip, int porta) throws RemoteException;
    
    
    //CAIXA
    
    //Conexão do caixa com o servidor
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int conectarAoServidor(String nome, String ip, int porta) throws RemoteException;

    //Desconecta caixa do servidor
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int DesconectarDoServidor(String ip) throws RemoteException;
    
    //Insere produto no estoque
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int insereProduto(Produto pNovo, String nomeFuncionario) throws RemoteException;
    
    //Altera produto do estoque
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int alteraProduto(Produto pAlterado, String nomeFuncionario) throws RemoteException;
    
    //Pesquisa produto do estoque
    //Retorna o valor 0 se sucesso e 1 caso falha
    public ArrayList<Produto> pesquisaProduto(String nome) throws RemoteException;
    
    //Pesquisa produto do estoque
    //Retorna o valor 0 se sucesso e 1 caso falha
    public Produto pesquisaProduto(String nomeProduto, String nome) throws RemoteException;
    
    //Exclui produto do estoque
    //Retorna o valor 0 se sucesso e 1 caso falha
    public int excluiProduto(Produto pExcluir, String nomeFuncionario) throws RemoteException;
}