package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Produtos;
import view.viewJTable;

public class ProdutoDAO {
    
    /* METODO PARA CADASTRAR UM PRODUTO */
    public boolean create(Produtos p){
        Connection con = ConnectionFactory.getConnection();   
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produtos(nome,descricao,qtd,preco)"
                                        + "VALUES(?,?,?,?)");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setInt(3, p.getQtd());
            stmt.setDouble(4, p.getPreco());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "ATUALIZADO COM SUCESSO!");
           return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "OCORREU UM ERRO AO CRIAR");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /* METODO PARA LISTAR OS PRODUTOS */
    public List<Produtos> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produtos> lista = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM produtos");
            rs = stmt.executeQuery();
            while (rs.next()){    
                Produtos p = new Produtos();
                p.setGTIN(rs.getInt("GTIN"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtd(rs.getInt("qtd"));
                p.setPreco(rs.getDouble("preco"));
                lista.add(p);    
            }
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO AO LISTAR PRODUTOS", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
    
    /* METODO PARA ATUALIZAR OS PRODUTOS */
      public boolean update(Produtos p){
        Connection con = ConnectionFactory.getConnection();   
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE produtos set nome=?, descricao=?, qtd=?, preco=? WHERE GTIN=?");
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescricao());
            stmt.setInt(3, p.getQtd());
            stmt.setDouble(4, p.getPreco());
            stmt.setInt(5, p.getGTIN());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "ATUALIZADO COM SUCESSO!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "OCORREU UM ERRO AO CRIAR");
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
      
      /* METODO PARA REMOVER OS PRODUTOS */
      public boolean delete(Produtos p){
          Connection con = ConnectionFactory.getConnection();
          PreparedStatement stmt = null;
          
        try {
            stmt = con.prepareStatement("DELETE FROM produtos WHERE GTIN=?");
            stmt.setInt(1, p.getGTIN());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "DELETADO COM SUCESSO!");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "OCORREU UM ERRO!");
            return false;
        }
      }
      
      /* METODO PARA PROCURAR OS PRODUTOS POR NOME */
      public List<Produtos> readForName(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produtos> lista = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM produtos WHERE nome LIKE ?");
            stmt.setString(1, "%"+nome+"%");
            rs = stmt.executeQuery();
            
            while (rs.next()){ 
                
                Produtos p = new Produtos();
                p.setGTIN(rs.getInt("GTIN"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtd(rs.getInt("qtd"));
                p.setPreco(rs.getDouble("preco"));
                lista.add(p);        
            }    
        } catch (SQLException ex) {
            throw new RuntimeException("ERRO AO LISTAR PRODUTOS", ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
}
