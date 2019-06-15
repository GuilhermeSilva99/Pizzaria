
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Felipe
 */
public class Conexao {
    private static final String DRIVE = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/pizzaria";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConexao(){
        try {
            Class.forName(DRIVE);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão", ex);
        }
        
    }
    
    public static void closeConexao(Connection con){
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
    }
    
    public static void closeConexao(Connection con, PreparedStatement st){
        if(st != null){
            try {
                st.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
        
        closeConexao(con);
    }
    
    public static void closeConexao(Connection con, PreparedStatement st, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
            }
        }
        
        closeConexao(con, st);
    }
}
