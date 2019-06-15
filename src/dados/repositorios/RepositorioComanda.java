package dados.repositorios;

import conexao.Conexao;
import negocio.entidade.Comanda;
import negocio.entidade.RelatorioComanda;
import dados.repositorios.interfaces_repositorios.IRepositorioComanda;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import negocio.entidade.Cliente;
import negocio.entidade.Pizza;
import negocio.fachada.Fachada;
import negocio.fachada.interfaces.IFachada;

public class RepositorioComanda implements IRepositorioComanda{
    
    private Connection con = null;

    public RepositorioComanda() {
        con = Conexao.getConexao();
    }
    
    // --------------- Select Comanda -----------------
    @Override
    public Comanda getComandaPorChavePrimaria(String chave){
        String sql = "SELECT * FROM comanda WHERE CHAVE_PRIMARIA = '" + chave + "'";
        ArrayList<Comanda> comandas = selectComandas(sql);
        if(comandas.size() == 0)
            return null;
        return comandas.get(0);
    }
    private ArrayList<Comanda> selectComandas(String sql){
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Comanda> comandas = new ArrayList<>();
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            IFachada fachada = Fachada.getFachada();
            while(rs.next()){
                try{
                    Cliente cliente = new Cliente(rs.getString("NOME_CLIENTE"), rs.getString("CIDADE"), 
                    rs.getString("RUA"), rs.getString("NUMERO"));

                    Comanda comanda = new Comanda(comandas.size(), rs.getString("DATA_COMPRA"), 
                    rs.getString("HORARIO_COMPRA"), rs.getString("FORMA_PAGAMENTO"), 
                    fachada.buscarFuncionarioPorId(rs.getInt("FUNCIONARIO")),selectPizzasComanda(rs.getString("DATA_COMPRA") +
                            rs.getString("HORARIO_COMPRA")), 
                    cliente, rs.getDouble("VALOR_COMANDA"), rs.getString("ENTREGA"));
                        
                    comandas.add(comanda);
                }
                catch(Exception e){ System.err.println("Erro: " + e);}
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        
        return comandas;
    }
    // ------------------------------------------------
    
    // ---------------- Select Pizzas da Comanda ----------------------
    private ArrayList<Pizza> selectPizzasComanda(String chave){
        String sql = "SELECT * FROM pizzas_comanda WHERE COMANDA = '" + chave + "'";
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<Pizza> pizzas = new ArrayList<>();
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            IFachada fachada = Fachada.getFachada();
            while(rs.next()){
                try{
                    pizzas.add(fachada.buscarPizzaPorId(rs.getInt("PIZZA")));
                }
                catch(Exception e){ System.err.println("Erro: " + e);}
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        
        return pizzas;
    }
    private void selectPizzasComanda(){
        String sql = "CREATE VIEW pizzasDaComanda\n" +
"	AS SELECT CHAVE_PRIMARIA, COUNT(*), SUM(VALOR)\n" +
"	FROM comanda c, pizzas_comanda pc\n" +
"	WHERE c.CHAVE_PRIMARIA = pc.COMANDA  GROUP BY CHAVE_PRIMARIA";
    }
    // ----------------------------------------------------------------
    
    // ----------- Selects Relatorio de Comandas ----------------------
    @Override
    public ArrayList<RelatorioComanda> getRelatorioComanda(){
        String sql = "SELECT * FROM comanda";
        return selectRelatorioComandas(sql);
    }
    
    @Override
    public RelatorioComanda getRelatorioComandaPorData(String data){
        String sql = "SELECT * FROM comanda WHERE DATA_COMPRA = '" + data + "'";
        ArrayList<RelatorioComanda> relatorios = selectRelatorioComandas(sql);
        if(relatorios.size() == 0)
            return null;
        return relatorios.get(0);
    }
    
    private ArrayList<RelatorioComanda> selectRelatorioComandas(String sql){
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList<RelatorioComanda> relatorios = new ArrayList<>();
        
        try {
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            IFachada fachada = Fachada.getFachada();
            while(rs.next()){
                try{
                    String dataCompra = rs.getString("DATA_COMPRA");
                    int i = verificarExisteRelatorioComanda(relatorios, dataCompra);
                    if(i == -1){
                        RelatorioComanda relatorio = new RelatorioComanda(dataCompra, relatorios.size());
                        
                        Cliente cliente = new Cliente(rs.getString("NOME_CLIENTE"), rs.getString("CIDADE"), 
                        rs.getString("RUA"), rs.getString("NUMERO"));
                        
                        Comanda comanda = new Comanda(0, dataCompra, rs.getString("HORARIO_COMPRA"),
                        rs.getString("FORMA_PAGAMENTO"), fachada.buscarFuncionarioPorId(rs.getInt("FUNCIONARIO")),
                                selectPizzasComanda(dataCompra + rs.getString("HORARIO_COMPRA")), 
                                cliente, rs.getDouble("VALOR_COMANDA"), 
                        rs.getString("ENTREGA"));
                        
                        relatorio.setValorTotal(comanda.getValorComanda());
                        relatorio.setQuantidadeComandas(1);
                        relatorio.getComandas().add(comanda);
                        
                        relatorios.add(relatorio);
                    }
                    else{
                        Cliente cliente = new Cliente(rs.getString("NOME_CLIENTE"), rs.getString("CIDADE"), 
                        rs.getString("RUA"), rs.getString("NUMERO"));
                        
                        Comanda comanda = new Comanda(relatorios.get(i).getComandas().size(), dataCompra, 
                                rs.getString("HORARIO_COMPRA"), rs.getString("FORMA_PAGAMENTO"), 
                                fachada.buscarFuncionarioPorId(rs.getInt("FUNCIONARIO")),
                                selectPizzasComanda(dataCompra + rs.getString("HORARIO_COMPRA")), 
                                cliente, rs.getDouble("VALOR_COMANDA"), rs.getString("ENTREGA"));
                        
                        relatorios.get(i).setValorTotal(relatorios.get(i).getValorTotal() + comanda.getValorComanda());
                        relatorios.get(i).setQuantidadeComandas(relatorios.get(i).getQuantidadeComandas() + 1);
                        relatorios.get(i).getComandas().add(comanda);
                    }
                }
                catch(Exception e){ System.err.println("Erro: " + e);}
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        
        return relatorios;
    }
    
    private int verificarExisteRelatorioComanda(ArrayList<RelatorioComanda> comandas, String data){
        for(int i = 0; i < comandas.size(); i++){
            if(data.equals(comandas.get(i).getNomeData())){
                return i;
            }
        }
        return -1;
    }
    // -----------------------------------------------------------------
    
    // ---------- Insert Comanda ---------------------------------------
    @Override
    public boolean insertComanda(Comanda comanda){
        String sql = "INSERT INTO comanda (CHAVE_PRIMARIA, DATA_COMPRA, HORARIO_COMPRA, FORMA_PAGAMENTO, FUNCIONARIO, "
                + "VALOR_COMANDA, ENTREGA, NOME_CLIENTE, CIDADE, RUA, NUMERO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, comanda.getDataCompra() + comanda.getHorario());
            st.setString(2, comanda.getDataCompra());
            st.setString(3, comanda.getHorario());
            st.setString(4, comanda.getFormaPagamento());
            st.setInt(5, comanda.getFuncionario().getId());
            st.setDouble(6, comanda.getValorComanda());
            st.setString(7, comanda.getEntrega());
            st.setString(8, comanda.getCliente().getNome());
            st.setString(9, comanda.getCliente().getEndereco().getCidade());
            st.setString(10, comanda.getCliente().getEndereco().getRua());
            st.setString(11, comanda.getCliente().getEndereco().getNumero());
            st.executeUpdate();
            insertPizzasComanda(comanda.getDataCompra() + comanda.getHorario(), comanda.getPizzas());
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
        
    }
    
    private boolean insertPizzasComanda(String chavePrimariaComanda, ArrayList<Pizza> pizzas){
        try{
            for(int i = 0; i < pizzas.size(); i++){
                String sql = "INSERT INTO pizzas_comanda (COMANDA, PIZZA) VALUES (?, ?)";

                PreparedStatement st = null;
                st = con.prepareStatement(sql);
                st.setString(1, chavePrimariaComanda);
                st.setInt(2, pizzas.get(i).getId());
                st.executeUpdate();
            }
        } catch (SQLException ex) {
                System.err.println("Erro: " + ex);
                return false;
        }
        return true;
    }
    // -----------------------------------------------------------------
    
    // ------------------- Update Comanda --------------------
    @Override
    public boolean updateComanda(Comanda comanda){
        String sql = "UPDATE comanda SET DATA_COMPRA = ?, HORARIO_COMPRA = ?, FORMA_PAGAMENTO = ?, FUNCIONARIO = ?, "
                + "VALOR_COMANDA = ?, ENTREGA = ?, NOME_CLIENTE = ?, CIDADE = ?, RUA = ?, NUMERO = ?"
                + " WHERE CHAVE_PRIMARIA = '" + comanda.getDataCompra() + comanda.getHorario() + "'";
        
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, comanda.getDataCompra());
            st.setString(2, comanda.getHorario());
            st.setString(3, comanda.getFormaPagamento());
            st.setInt(4, comanda.getFuncionario().getId());
            st.setDouble(5, comanda.getValorComanda());
            st.setString(6, comanda.getEntrega());
            st.setString(7, comanda.getCliente().getNome());
            st.setString(8, comanda.getCliente().getEndereco().getCidade());
            st.setString(9, comanda.getCliente().getEndereco().getRua());
            st.setString(10, comanda.getCliente().getEndereco().getNumero());
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
        
    }
    // -------------------------------------------------------
    
    // ------------------- deletar cargos -----------------------
    
    private boolean deletePizzasComanda(String chave){
        String sql = "DELETE FROM pizzas_comanda WHERE COMANDA = ?";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, chave);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
        
    }
    
    @Override
    public boolean deleteComanda(String chave){
        deletePizzasComanda(chave);
        String sql = "DELETE FROM comanda WHERE CHAVE_PRIMARIA = ?";
        PreparedStatement st = null;
        try {
            st = con.prepareStatement(sql);
            st.setString(1, chave);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
        
    }
    
    //-----------------------------------------------------------
}
