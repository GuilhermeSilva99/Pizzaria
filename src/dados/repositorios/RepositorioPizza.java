package dados.repositorios;

import conexao.Conexao;
import negocio.entidade.Pizza;
import dados.repositorios.interfaces_repositorios.IRepositorioPizza;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class RepositorioPizza implements IRepositorioPizza{

    private Connection con = null;

    public RepositorioPizza() {
        con = Conexao.getConexao();
    }
    
    // ---------------- Selects de cargos --------------------------------
    
    @Override
    public ArrayList<Pizza> getPizzas(){
        String sql = "SELECT * FROM pizza";
        return selectPizza(sql);
    }
    
    @Override
    public ArrayList<Pizza> getPizzasAtiva(){
        String sql = "SELECT * FROM pizza WHERE INATIVO = 0";
        return selectPizza(sql);
    }
    
    @Override
    public ArrayList<Pizza> buscarPizzaPorNome(String nome){
        String sql = "SELECT * FROM pizza WHERE NOME = '" + nome + "'";
        return selectPizza(sql);
    }
    
    @Override
    public ArrayList<Pizza> buscarPizzaPorId(int id){
        String sql = "SELECT * FROM pizza WHERE ID = " + id;
        return selectPizza(sql);
    }
    
    @Override
    public ArrayList<Pizza> buscarPizzaAtivaPorNome(String nome){
        String sql = "SELECT * FROM pizza WHERE NOME = '" + nome + "' AND INATIVO = 0";
        return selectPizza(sql);
    }
    
    @Override
    public ArrayList<Pizza> buscarPizzaAtivaPorId(int id){
        String sql = "SELECT * FROM pizza WHERE ID = " + id + " AND INATIVO = 0";
        return selectPizza(sql);
    }
    
    private ArrayList<Pizza> selectPizza(String sql){
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Pizza> pizzas = new ArrayList<>();
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()){
                boolean b = false;
                if(rs.getString("DISPONIBILIDADE").equals("1"))
                    b = true;
                Pizza pizza = new Pizza(Integer.parseInt(rs.getString("ID")), rs.getString("NOME"), 
                        Double.parseDouble(rs.getString("VALOR")), rs.getString("INGREDIENTES"), 
                        b, rs.getString("TIPO_PIZZA"),
                        Boolean.parseBoolean(rs.getString("INATIVO")));
                pizzas.add(pizza);
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        
        return pizzas;
    }
    
    // -------------------- salvar e atualizar cargos ------------------------
    
    private boolean salvarAtualizarBancoDados(Pizza pizza, String sql){
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, pizza.getNome());
            st.setDouble(2, pizza.getValor());
            st.setString(3, pizza.getIngredientes());
            st.setString(4, pizza.getTipoPizza());
            st.setBoolean(5, pizza.getDisponivel());
            st.setBoolean(6, pizza.getInativo());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
        
    }
    
    // ------------------- deletar cargos -----------------------
    
    private boolean deleteBancoDados(Pizza pizza){
        String sql = "DELETE FROM pizza WHERE ID = ?";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setInt(1, pizza.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
        
    }
    
    //-----------------------------------------------------------
    
    @Override
    public void adicionar(Pizza pizza){
        String sql = "INSERT INTO pizza (NOME, VALOR, INGREDIENTES, TIPO_PIZZA, DISPONIBILIDADE, INATIVO) VALUES (?, ?, ?, ?, ?, ?)";
        salvarAtualizarBancoDados(pizza, sql);
    }

    @Override
    public void remover(Pizza pizza){
        pizza.setInativo(true);
        editar(pizza);
    }

    @Override
    public void editar(Pizza pizza){
        String sql = "UPDATE pizza SET NOME = ?, VALOR = ?, INGREDIENTES = ?, TIPO_PIZZA = ?, DISPONIBILIDADE = ?, "
                + "INATIVO = ? WHERE ID = " + pizza.getId();
        salvarAtualizarBancoDados(pizza, sql);
    }
}
