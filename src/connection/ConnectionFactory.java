package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ConnectionFactory {
    //CONEXAO DO SERVE MYSQL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mercadinho";
    private static final String USER = "root";
    private static final String PASS = "";
     
    public static Connection getConnection(){ 
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
             throw new RuntimeException("ERRO NA CONEXAO", ex);  
        }
    }

    /* FECHAR CONEXAO COM O CONNECTION */
    public static void closeConnection(Connection con){ 
            try {
                if (con!=null){
                  con.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("NAO FECHADO A CONEXAO", ex);
            }
    }
    
    /* FECHAR CONEXAO COM O CONNECTION E PREPAREDSTATEMENT */
    public static void closeConnection(Connection con, PreparedStatement stmt){
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
                throw new RuntimeException("ERRO DE CONEXAO", ex);
            }
    }
    
     /* FECHAR CONEXAO COM O CONNECTION, PREPAREDSTATEMENT E RESULTSET */
     public static void closeConnection(Connection con, PreparedStatement stmt,
                                        ResultSet rs){
         closeConnection(con, stmt);
         try {
             if (rs!=null) {
                 rs.close();
             }
            } catch (SQLException ex) {
                throw new RuntimeException("ERRO DE CONEXAO", ex);
            }
    }
}
