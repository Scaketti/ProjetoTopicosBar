/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Scaketti
 */
public class Pedido implements Serializable{
    private int nPedido;
    private ClienteTerminal mesa;
    private float valorPedido;
    private ArrayList <Produto> produtosConsumidos;
    
    public Pedido(ClienteTerminal mesa, ArrayList<Produto> produtosConsumidos, float valorPedido){
        this.mesa = mesa;
        this.valorPedido = valorPedido;
        this.produtosConsumidos = produtosConsumidos;
    }

    /**
     * @return the produtosConsumidos
     */
    public ArrayList <Produto> getProdutosConsumidos() {
        return produtosConsumidos;
    }

    /**
     * @param produtosConsumidos the produtosConsumidos to set
     */
    public void setProdutosConsumidos(ArrayList <Produto> produtosConsumidos) {
        this.produtosConsumidos = produtosConsumidos;
    }

    /**
     * @return the nPedido
     */
    public int getnPedido() {
        return nPedido;
    }

    /**
     * @param nPedido the nPedido to set
     */
    public void setnPedido(int nPedido) {
        this.nPedido = nPedido;
    }

    /**
     * @return the mesa
     */
    public ClienteTerminal getMesa() {
        return mesa;
    }

    /**
     * @param mesa the mesa to set
     */
    public void setMesa(ClienteTerminal mesa) {
        this.mesa = mesa;
    }

    /**
     * @return the valorPedido
     */
    public float getValorPedido() {
        return valorPedido;
    }

    /**
     * @param valorPedido the valorPedido to set
     */
    public void setValorPedido(float valorPedido) {
        this.valorPedido = valorPedido;
    }
}
