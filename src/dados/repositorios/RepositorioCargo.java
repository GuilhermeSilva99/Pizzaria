package dados.repositorios;

import conexao.Conexao;
import negocio.entidade.Cargo;
import negocio.entidade.InformacoesUsuario;
import negocio.exptions.CargoException;
import dados.repositorios.interfaces_repositorios.IRepositorioCargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioCargo implements IRepositorioCargo{
    
    private Connection con = null;

    public RepositorioCargo() {
        con = Conexao.getConexao();
    }
    
    // ---------------- Selects de cargos --------------------------------
    
    @Override
    public boolean verificarExisteCargoAdministrador(){
        String sql = "SELECT * FROM cargo WHERE NOME = 'administrador'";
        if(selectCargo(sql).size() > 0)
            return true;
        else
            return false;
    }
    
    @Override
    public ArrayList<Cargo> getCargos(){
        String sql = "SELECT * FROM cargo";
        if(!InformacoesUsuario.getInstance().getAdministrador()) // Se não for um Administrador
            sql = "SELECT * FROM cargo WHERE NOME != 'gerente'";
        return selectCargo(sql);
    }
    
    @Override
    public ArrayList<Cargo> getCargosAtivo(){
        String sql = "SELECT * FROM cargo WHERE INATIVO = 0";
        if(!InformacoesUsuario.getInstance().getAdministrador()) // Se não for um Administrador
            sql = "SELECT * FROM cargo WHERE INATIVO = 0 AND NOME != 'gerente'";
        return selectCargo(sql);
    }
    
    @Override
    public ArrayList<Cargo> buscarCargoPorNome(String nome){
        String sql = "SELECT * FROM cargo WHERE NOME = '" + nome + "'";
        return selectCargo(sql);
    }
    
    @Override
    public ArrayList<Cargo> buscarCargoPorId(int id){
        String sql = "SELECT * FROM cargo WHERE ID = " + id;
        return selectCargo(sql);
    }
    
    @Override
    public ArrayList<Cargo> buscarCargoAtivoPorNome(String nome){
        String sql = "SELECT * FROM cargo WHERE NOME = '" + nome + "' AND INATIVO = 0";
        return selectCargo(sql);
    }
    
    @Override
    public ArrayList<Cargo> buscarCargoAtivoPorId(int id){
        String sql = "SELECT * FROM cargo WHERE ID = " + id + " AND INATIVO = 0";
        return selectCargo(sql);
    }
    
    @Override
    public ArrayList<Cargo> buscarCargoVinculadoFuncionario(int id){
        String sql = "SELECT * FROM funcionario WHERE CARGO = " + id + " AND INATIVO = 0";
        return selectCargo(sql);
    }
    
    private ArrayList<Cargo> selectCargo(String sql){
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Cargo> cargos = new ArrayList<>();
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            
            while(rs.next()){
                Cargo cargo = new Cargo(Integer.parseInt(rs.getString("ID")), rs.getString("NOME"), rs.getString("DESCRICAO"), 
                        Double.parseDouble(rs.getString("SALARIO_BASE")), Boolean.parseBoolean(rs.getString("INATIVO")));
                cargos.add(cargo);
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        
        return cargos;
    }
    
    // -------------------- salvar e atualizar cargos ------------------------
    
    private boolean salvarAtualizarBancoDados(Cargo cargo, String sql){
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, cargo.getNome());
            st.setString(2, cargo.getDescricao());
            st.setDouble(3, cargo.getSalarioBase());
            st.setBoolean(4,cargo.getInativo());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
        
    }
    
    // ------------------- deletar cargos -----------------------
    
    private boolean deleteBancoDados(Cargo cargo){
        String sql = "DELETE FROM cargo WHERE ID = ?";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setInt(1, cargo.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
        
    }
    
    //-----------------------------------------------------------
    
    
    @Override
    public void adicionar(Cargo cargo) throws CargoException{
        String sql = "INSERT INTO cargo (NOME, DESCRICAO, SALARIO_BASE, INATIVO) VALUES (?, ?, ?, ?)";
        salvarAtualizarBancoDados(cargo, sql);  
    }
    
    @Override
    public void remover(Cargo cargo){
        cargo.setInativo(true);
        editar(cargo);
    }
    
    @Override
    public void editar(Cargo cargo){
        String sql = "UPDATE cargo SET NOME = ?, DESCRICAO = ?, SALARIO_BASE = ?, INATIVO = ? WHERE ID = " + cargo.getId();
        salvarAtualizarBancoDados(cargo, sql);
    }
}
