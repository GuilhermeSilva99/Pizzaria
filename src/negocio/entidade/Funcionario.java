
package negocio.entidade;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.NegocioFuncionario;
import negocio.exptions.CargoException;
import negocio.exptions.LoginException;
import negocio.exptions.PessoaException;
import negocio.fachada.Fachada;
import negocio.fachada.interfaces.IFachada;
import negocio.interfaces.INegocioFuncionario;

public class Funcionario extends Pessoa {
    private Login login;
    private String status;
    private Cargo cargo;
    private String dataAdimissao;

    public Funcionario(int id, Endereco endereco, String nomeUsuario, String senha, String status, Cargo cargo, 
            String dataAdimissao, String nome, String cpf, boolean inativo) {
        super(nome, cpf, endereco);
        login = new Login(nomeUsuario, senha);
        this.status = status;
        this.cargo = cargo;
        this.dataAdimissao = dataAdimissao;
        this.setId(id);
        this.setInativo(inativo);
    }
    
    public Funcionario(Endereco endereco, String nomeUsuario, String senha, String status, Cargo cargo, String dataAdimissao, String nome, String cpf) {
        super(nome, cpf, endereco);
        login = new Login(nomeUsuario, senha);
        this.status = status;
        this.cargo = cargo;
        this.dataAdimissao = dataAdimissao;
        //int id = id() + 1;
        //this.setId(id);
    }
    
    public Funcionario(String nome, String email, String senha, int id){
        super(nome, null, id);
        login = new Login(email, senha);
    }
    
    public void setLoginNomeUsuario(String nomeUsuario){
        login.setNomeUsuario(nomeUsuario);
    }
    
    public void setLoginSenha(String senha){
        login.setSenha(senha);
    }
    
    public Funcionario(String nome, String cpf, int id){
        super(nome, cpf, id);
    }
    
    public String getSenha() {
        return login.getSenha();
    }
    
    public String getNomeUsuario() {
        return login.getNomeUsuario();
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public String getDataAdimissao() {
        return dataAdimissao;
    }

    public void setDataAdimissao(String dataAdimissao) {
        this.dataAdimissao = dataAdimissao;
    }
    
    public static void verificacaoAdm(){
        try{
            IFachada fachada = Fachada.getFachada();
            if(!fachada.verificarExisteAdministrador()){
                try {
                    if(!fachada.verificarExisteCargoAdministrador())
                        fachada.adicionarCargo("administrador", "manda em tudo", "0");
                    
                    Endereco end = new Endereco("------", "------","sn");
                    fachada.adicionarFuncionario(end, "admin", "admin", "", 
                            fachada.buscarCargoAtivoPorNome("administrador"), "", "admin", "00000000000");
                    
                } catch (CargoException | PessoaException | LoginException ex) {
                    System.err.println("Erro: " + ex);  
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
