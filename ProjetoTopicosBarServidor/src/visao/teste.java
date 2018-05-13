/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dados.ProdutoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Produto;

/**
 *
 * @author foxdie
 */
public class teste {
    public static void main(String[] args){
        Produto p = new Produto();
        
        
        p.setNome("Pão");
        p.setQtd(10);
        p.setPreco((float) 10.5);
        
       ProdutoDAO pDAO = new ProdutoDAO();
       
        try {
            pDAO.insereProduto(p);
        } catch (Exception ex) {
            System.out.println("Erro");
        }
    }
}

/*public class ProdutoDAO {
    
    public void insereProduto(Produto p) throws Exception {
        
        // Define a SQL
        String sql = ("INSERT INTO PRODUTO (NOME, QTD, PRECO) VALUES (?, ?, ?)");

        // Abre a conexão que criamos o retorno é armazenado na variavel conn 
        Connection conn = Conexao.abrir();
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setInt(2, p.getQtd());
            stmt.setFloat(3, p.getPreco());
            
            stmt.executeQuery();
            stmt.close();
        } 
        catch (SQLException e) {
            System.out.println("Errooooooo");
        }
    }
}
*/