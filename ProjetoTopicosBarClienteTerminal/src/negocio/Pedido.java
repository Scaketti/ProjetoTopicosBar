/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.ArrayList;

/**
 *
 * @author foxdie
 */
public class Pedido {
    private int mesa;
    private String nome;
    private ArrayList <Produto> produtosConsumidos;

    /**
     * @return the mesa
     */
    public int getMesa() {
        return mesa;
    }

    /**
     * @param mesa the mesa to set
     */
    public void setMesa(int mesa) {
        this.mesa = mesa;
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
}
