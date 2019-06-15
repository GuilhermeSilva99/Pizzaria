
package negocio.interfaces;

import negocio.entidade.Cargo;
import negocio.entidade.Endereco;
import negocio.entidade.Funcionario;
import negocio.exptions.LoginException;
import negocio.exptions.PessoaException;
import java.util.ArrayList;

public interface INegocioFuncionario {
    void adicionar(Endereco endereco, String nomeUsuario, String senha, String status, Cargo cargo, 
    String dataAdimissao, String nome, String cpf) throws PessoaException, LoginException;
    void remover(int id);
    void editar(int id, String cidade, String rua, String numero, String email, 
    String senha, String status, Cargo cargo, String dataAdimissao, String nome, String cpf) throws PessoaException, LoginException;
    void verificacaoCadastroLogin(String nomeUsuario, String senha) throws LoginException;
    Funcionario buscarLogin(String nomeUsuario, String senha) throws LoginException;
    
    ArrayList<Funcionario> buscarFuncionarioPorNome(String nome) throws PessoaException;
    ArrayList<Funcionario> buscarFuncionarioAtivoPorNome(String nome) throws PessoaException;
    Funcionario buscarFuncionarioPorId(int id) throws PessoaException;
    Funcionario buscarFuncionarioAtivoPorId(int id) throws PessoaException;
    ArrayList<Funcionario> buscarFuncionarioPorCpf(String cpf) throws PessoaException;
    ArrayList<Funcionario> buscarFuncionarioAtivoPorCpf(String cpf) throws PessoaException;
    ArrayList<Funcionario> getFuncionarios();
    ArrayList<Funcionario> getFuncionariosAtivo();
    boolean VerificarExisteAdministrador();
}
