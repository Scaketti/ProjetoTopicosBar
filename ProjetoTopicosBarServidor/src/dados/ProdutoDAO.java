/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.awt.List;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import negocio.Produto;

/**
 *
 * @author Scaketti
 */
public class ProdutoDAO {

    Connection conn;

    public ProdutoDAO(Connection conn) {
        this.conn = conn;
    }

    public int insereProduto(Produto p) throws Exception {
        /* Define a SQL */
        Statement stmt = conn.createStatement();

        try {
            stmt.executeUpdate("INSERT INTO Produto (NOME, QTD, PRECO) VALUES ('"
                    + p.getNome() + "', "
                    + p.getQtd() + ", "
                    + p.getPreco() + ")");

            return 0;
        } catch (SQLException Erro) {
            JOptionPane.showMessageDialog(null, "Erro Cmdo SQL" + Erro.getMessage());
            return -1;
        }
    }

    public int alteraProduto(Produto p) throws Exception {
        /* Define a SQL */
        Statement stmt = conn.createStatement();

        try {
            stmt.executeUpdate("UPDATE Produto SET QTD = " + 
                    p.getQtd() + ", PRECO = " + 
                    p.getPreco() + " WHERE NOME = '" + 
                    p.getNome() + "'");

            return 0;
        } catch (SQLException Erro) {
            JOptionPane.showMessageDialog(null, "Erro Cmdo SQL" + Erro.getMessage());
            return -1;
        }
    }

    public ArrayList<Produto> pesquisaProduto() throws Exception {
        /* Define a SQL */
        Statement stmt = conn.createStatement();

        try {
            ArrayList<Produto> busca = new ArrayList<>();

            ResultSet set = stmt.executeQuery("SELECT * FROM Produto");

            while (set.next()) {
                Produto est = new Produto();

                est.setNome(set.getString("nome"));
                est.setQtd(set.getInt("qtd"));
                est.setPreco(set.getFloat("preco"));

                busca.add(est);
            }
            
            set.close();
            stmt.close();

            return busca;
            
        } catch (SQLException Erro) {
            JOptionPane.showMessageDialog(null, "Erro Cmdo SQL" + Erro.getMessage());
            return null;
        }
    }

    public Produto pesquisaProduto(Produto p) throws Exception {
        /* Define a SQL */
        Statement stmt = conn.createStatement();

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Produto WHERE NOME = " + p.getNome() + ")");
            
            Produto pBusca = new Produto();
            pBusca.setNome(rs.getString("nome"));
            pBusca.setPreco(rs.getFloat("preco"));
            pBusca.setQtd(rs.getInt("qtd"));

            return pBusca;
        } catch (SQLException Erro) {
            JOptionPane.showMessageDialog(null, "Erro Cmdo SQL" + Erro.getMessage());
            return null;
        }
    }

    public int excluiProduto(Produto p) throws Exception {
        /* Define a SQL */
        Statement stmt = conn.createStatement();

        try {
            stmt.executeUpdate("DELETE * FROM Produto WHERE NOME = '"
                    + p.getNome() + "')");

            return 0;
        } catch (SQLException Erro) {
            JOptionPane.showMessageDialog(null, "Erro Cmdo SQL" + Erro.getMessage());
            return -1;
        }
    }
}
