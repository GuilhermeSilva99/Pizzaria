package dados.repositorios;

import conexao.Conexao;
import dados.repositorios.interfaces_repositorios.IRepositorioFuncionario;
import negocio.entidade.Funcionario;
import negocio.entidade.InformacoesUsuario;
import negocio.exptions.PessoaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.entidade.Cargo;
import negocio.entidade.Endereco;
import negocio.fachada.Fachada;
import negocio.fachada.interfaces.IFachada;

public class RepositorioFuncionario implements IRepositorioFuncionario{
    
    private Connection con = null;

    public RepositorioFuncionario() {
        con = Conexao.getConexao();
    }
    
    // ---------------- Selects de cargos --------------------------------
    
    @Override
    public boolean verificarExisteAdministrador(){
        String sql = "SELECT * FROM funcionario f WHERE 'administrador' = (SELECT NOME FROM cargo c WHERE f.CARGO = c.ID)";
        if(selectFuncionario(sql).size() > 0)
            return true;
        else
            return false;
    }
    
    @Override
    public ArrayList<Funcionario> getFuncionarios(){
        String sql = "SELECT * FROM funcionario";
        if(!InformacoesUsuario.getInstance().getAdministrador()) // Se não for um Administrador
            sql = "SELECT * FROM funcionario f WHERE 'gerente' != (SELECT NOME FROM cargo c WHERE f.CARGO = c.ID)";
        return selectFuncionario(sql);
    }
    
    @Override
    public ArrayList<Funcionario> getFuncionariosAtivo(){
        String sql = "SELECT * FROM funcionario WHERE INATIVO = 0";
        if(!InformacoesUsuario.getInstance().getAdministrador()) // Se não for um Administrador
            sql = "SELECT * FROM funcionario f WHERE INATIVO = 0 AND 'gerente' != (SELECT NOME FROM cargo c WHERE f.CARGO = c.ID)";
        return selectFuncionario(sql);
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioPorNome(String nome){
        String sql = "SELECT * FROM funcionario WHERE NOME = '" + nome + "'";
        return selectFuncionario(sql);
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioPorId(int id){
        String sql = "SELECT * FROM funcionario WHERE ID = " + id;
        return selectFuncionario(sql);
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioAtivoPorNome(String nome){
        String sql = "SELECT * FROM funcionario WHERE NOME = '" + nome + "' AND INATIVO = 0";
        return selectFuncionario(sql);
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioAtivoPorId(int id){
        String sql = "SELECT * FROM funcionario WHERE ID = " + id + " AND INATIVO = 0";
        return selectFuncionario(sql);
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioAtivoPorCpf(String cpf){
        String sql = "SELECT * FROM funcionario WHERE CPF = '" + cpf + "' AND INATIVO = 0";
        return selectFuncionario(sql);
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioPorCpf(String cpf){
        String sql = "SELECT * FROM funcionario WHERE CPF = '" + cpf + "'";
        return selectFuncionario(sql);
    }
    
    @Override
    public Funcionario buscarLogin(String email, String senha){
        String sql = "SELECT * FROM funcionario WHERE EMAIL = '" + email + "' AND SENHA = '" + senha + "'";
        ArrayList<Funcionario> resultado = selectFuncionario(sql);
        if(resultado.size() == 0)
            return null;
        
        return resultado.get(0);
    }
    
    @Override
    public Funcionario buscarEmail(String email){
        String sql = "SELECT * FROM funcionario WHERE EMAIL = '" + email + "'";
        ArrayList<Funcionario> resultado = selectFuncionario(sql);
        if(resultado.size() == 0)
            return null;
        
        return resultado.get(0);
    }
    
    private ArrayList<Funcionario> selectFuncionario(String sql){
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            IFachada fachada = Fachada.getFachada();
            while(rs.next()){
                try{
                    Cargo cargo = fachada.buscarCargoPorId(Integer.parseInt(rs.getString("CARGO")));
                    
                    Endereco endereco = new Endereco(rs.getString("CIDADE"), rs.getString("RUA"), rs.getString("NUMERO"));
                    
                    Funcionario funcionario = new Funcionario(Integer.parseInt(rs.getString("ID")), endereco, 
                    rs.getString("EMAIL"), rs.getString("SENHA"), rs.getString("STATUS"), cargo, rs.getString("DATA_ADMISSAO"),
                    rs.getString("NOME"), rs.getString("CPF"), Boolean.parseBoolean(rs.getString("INATIVO")));
                    
                    funcionarios.add(funcionario);
                }
                catch(Exception e){ System.err.println("Erro: " + e);}
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        
        return funcionarios;
    }
    
    // -------------------- salvar e atualizar cargos ------------------------
    
    private boolean salvarAtualizarBancoDados(Funcionario funcionario, String sql){
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, funcionario.getNome());
            st.setString(2, funcionario.getCpf());
            st.setString(3, funcionario.getNomeUsuario());
            st.setString(4, funcionario.getSenha());
            st.setInt(5, funcionario.getCargo().getId());
            st.setString(6, funcionario.getStatus());
            st.setString(7, funcionario.getDataAdimissao());
            st.setString(8, funcionario.getEndereco().getCidade());
            st.setString(9, funcionario.getEndereco().getRua());
            st.setString(10, funcionario.getEndereco().getNumero());
             st.setBoolean(11, funcionario.getInativo());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
        
    }
    
    // ------------------- deletar cargos -----------------------
    
    private boolean deleteBancoDados(Funcionario funcionario){
        String sql = "DELETE FROM funcionario WHERE ID = ?";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setInt(1, funcionario.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
        
    }
    
    //-----------------------------------------------------------
    @Override
    public void adicionar(Funcionario funcionario) throws PessoaException{
        String sql = "INSERT INTO funcionario (NOME, CPF, EMAIL, SENHA, CARGO, STATUS, DATA_ADMISSAO, CIDADE, RUA, NUMERO,"
                + "INATIVO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        salvarAtualizarBancoDados(funcionario, sql);
        //salvarArquivo(pessoa);
    }
    
    @Override
    public void remover(Funcionario funcionario){
        funcionario.setInativo(true);
        editar(funcionario);
    }
    
    @Override
    public void editar(Funcionario funcionario){
        String sql = "UPDATE funcionario SET NOME = ?, CPF = ?, EMAIL = ?, SENHA = ?, CARGO = ?, STATUS = ?, "
                + "DATA_ADMISSAO = ?, CIDADE = ?, RUA = ?, NUMERO = ?, INATIVO = ? WHERE ID = " + funcionario.getId();
        salvarAtualizarBancoDados(funcionario, sql);
        //salvarAtualizacaoArquivo(p);
    }
}
