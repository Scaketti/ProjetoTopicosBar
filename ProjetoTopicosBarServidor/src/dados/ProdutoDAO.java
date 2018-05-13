/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import negocio.Produto;

/**
 *
 * @author Scaketti
 */
public class ProdutoDAO {

    public void insereProduto(Produto p) throws Exception {
        /* Define a SQL */
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO PRODUTO (NOME, QTD, PRECO) VALUES (");
        sql.append(p.getNome() + ", ");
        sql.append(p.getQtd() + ", ");
        sql.append(p.getPreco() + ")");

        /* Abre a conexão que criamos o retorno é armazenado na variavel conn */
        Connection conn = Conexao.abrir();
        
        System.out.println(conn);

        /* Mapeamento objeto relacional */
        PreparedStatement comando = conn.prepareStatement(sql.toString());
        
         System.out.println(comando);
        //comando.setString(1, "%" + p.getNomeCliente() + "%");

        /* Executa a SQL e captura o resultado da consulta */
        comando.executeQuery();

        /* Cria uma lista para armazenar o resultado da consulta */
       // List<Produto> lista = new ArrayList<Produto>();

        /* Percorre o resultado armazenando os valores em uma lista */
       // while (resultado.next()) {
            /* Cria um objeto para armazenar uma linha da consulta */
           /* Produto linha = new Produto();
            linha.setCodigoCliente(resultado.getInt("cod_cliente"));
            linha.setNomeCliente(resultado.getString("nome_cliente"));
            linha.setIdadeCliente(resultado.getInt("idade_cliente"));
            /* Armazena a linha lida em uma lista */
           /* lista.add(linha);
        }*/

        comando.close();
        conn.close();
    }

}
