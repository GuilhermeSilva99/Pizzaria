
package dados.repositorios.interfaces_repositorios;

import java.util.ArrayList;
import negocio.entidade.Funcionario;
import negocio.exptions.PessoaException;

public interface IRepositorioFuncionario {
    
    void adicionar(Funcionario funcionario) throws PessoaException;
    void remover(Funcionario funcionario);
    void editar(Funcionario funcionario);
    
    boolean verificarExisteAdministrador();
    ArrayList<Funcionario> getFuncionarios();
    ArrayList<Funcionario> getFuncionariosAtivo();
    ArrayList<Funcionario> buscarFuncionarioPorNome(String nome);
    ArrayList<Funcionario> buscarFuncionarioPorId(int id);
    ArrayList<Funcionario> buscarFuncionarioAtivoPorNome(String nome);
    ArrayList<Funcionario> buscarFuncionarioAtivoPorId(int id);
    ArrayList<Funcionario> buscarFuncionarioPorCpf(String cpf);
    ArrayList<Funcionario> buscarFuncionarioAtivoPorCpf(String cpf);
    Funcionario buscarLogin(String email, String senha);
    Funcionario buscarEmail(String email);
}
