
package dados.repositorios.interfaces_repositorios;

import negocio.entidade.Pessoa;
import negocio.exptions.PessoaException;
import java.util.ArrayList;
import negocio.entidade.Funcionario;

public interface IRepositorioPessoa {
    void adicionar(Pessoa pessoa) throws PessoaException;
    void remover(Pessoa p);
    void editar(Pessoa p);
    Pessoa buscarPessoa(Pessoa p);
    ArrayList buscarPessoa(String nome);
    void salvarArquivo(Pessoa p);
    void salvarAtualizacaoArquivo(Pessoa p);
    ArrayList lerArquivo();
    
    ArrayList<Funcionario> getFuncionarios();
    ArrayList<Funcionario> getFuncionariosAtivo();
    ArrayList<Funcionario> buscarFuncionarioPorNome(String nome);
    ArrayList<Funcionario> buscarFuncionarioPorId(int id);
    ArrayList<Funcionario> buscarFuncionarioAtivoPorNome(String nome);
    ArrayList<Funcionario> buscarFuncionarioAtivoPorId(int id);
    ArrayList<Funcionario> buscarFuncionarioPorCpf(String cpf);
    ArrayList<Funcionario> buscarFuncionarioAtivoPorCpf(String cpf);
}
