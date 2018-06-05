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
        Statement stmtInsert = conn.createStatement();

        try {
            int qtd = count();

            if (qtd != -1) {
                stmtInsert.executeUpdate("INSERT INTO Produto (IDPRODUTO, NOME, QTD, PRECO) VALUES ( "
                        + qtd + ", '"
                        + p.getNome() + "', "
                        + p.getQtd() + ", "
                        + p.getPreco() + ")");

                return 0;
            }
            return -1;
        } catch (SQLException Erro) {
            JOptionPane.showMessageDialog(null, "Erro Cmdo SQL" + Erro.getMessage());
            return -1;
        }
    }

    public int count() throws Exception {
        Statement stmtCount = conn.createStatement();

        try {
            ResultSet count = stmtCount.executeQuery("SELECT * FROM Produto ORDER BY IDPRODUTO DESC");
            int qtd;
            if (count.next()) {
                qtd = count.getInt("IDPRODUTO") + 1;
            } else {
                qtd = 1;
            }

            return qtd;
        } catch (SQLException Erro) {
            JOptionPane.showMessageDialog(null, "Erro Cmdo SQL" + Erro.getMessage());
            return -1;
        }
    }

    public int alteraProduto(Produto p) throws Exception {
        /* Define a SQL */
        Statement stmt = conn.createStatement();

        try {
            stmt.executeUpdate("UPDATE Produto SET NOME = '"
                    + p.getNome() + "', QTD = "
                    + p.getQtd() + ", PRECO = "
                    + p.getPreco() + " WHERE IDPRODUTO = '"
                    + p.getIdProduto() + "'");

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

                est.setIdProduto(set.getInt("idProduto"));
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

    public Produto pesquisaProduto(String nomeProduto) throws Exception {
        /* Define a SQL */
        Statement stmt = conn.createStatement();

        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Produto WHERE NOME LIKE " + nomeProduto + ")");

            Produto pBusca = new Produto();
            pBusca.setIdProduto(rs.getInt("idProduto"));
            pBusca.setNome(rs.getString("NOME"));
            pBusca.setPreco(rs.getFloat("PRECO"));
            pBusca.setQtd(rs.getInt("QTD"));

            return pBusca;
        } catch (SQLException Erro) {
            JOptionPane.showMessageDialog(null, "Erro Cmdo SQL" + Erro.getMessage());
            return null;
        }
    }

    public int excluiProduto(int idProduto) throws Exception {
        /* Define a SQL */
        Statement stmt = conn.createStatement();

        try {
            stmt.executeUpdate("DELETE FROM Produto WHERE idProduto = '"
                    + idProduto + "'");

            return 0;
        } catch (SQLException Erro) {
            JOptionPane.showMessageDialog(null, "Erro Cmdo SQL" + Erro.getMessage());
            return -1;
        }
    }
}
