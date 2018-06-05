/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.sql.Connection;

/**
 *
 * @author foxdie
 */
public class teste {
    public static void main(String args[]){
        try {
            Connection conn = Conexao.abrir();
            ProdutoDAO pDAO = new ProdutoDAO(conn);

            System.out.println(pDAO.count());

            conn.close();
          
        } catch (Exception ex) {
            System.out.println("Erro");
            
        }
    }
    
}
