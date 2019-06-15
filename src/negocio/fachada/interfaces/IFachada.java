
package negocio.fachada.interfaces;

import negocio.entidade.Cargo;
import negocio.entidade.Comanda;
import negocio.entidade.Endereco;
import negocio.entidade.Pizza;
import negocio.entidade.RelatorioComanda;
import negocio.exptions.CargoException;
import negocio.exptions.ComandaException;
import negocio.exptions.LoginException;
import negocio.exptions.PessoaException;
import negocio.exptions.PizzaException;
import java.util.ArrayList;
import negocio.entidade.Cliente;
import negocio.entidade.Funcionario;
import negocio.exptions.AdministradorException;

public interface IFachada {
    // funcionario
    void adicionarFuncionario(Endereco endereco, String nomeUsuario, String senha, String status, 
    Cargo cargo, String dataAdimissao, String nome, String cpf) throws PessoaException, LoginException;
    void removerFuncionario(int id);
    void editarFuncionario(int id, String cidade, String rua, String numero, String email, 
    String senha, String status, Cargo cargo, String dataAdimissao, String nome, String cpf) throws LoginException, PessoaException;
    ArrayList<Funcionario> buscarFuncionarioPorNome(String nome) throws PessoaException;
    ArrayList<Funcionario> buscarFuncionarioAtivoPorNome(String nome) throws PessoaException;
    Funcionario buscarFuncionarioPorId(int id) throws PessoaException;
    Funcionario buscarFuncionarioAtivoPorId(int id) throws PessoaException;
    ArrayList<Funcionario> buscarFuncionarioPorCpf(String cpf) throws PessoaException;
    ArrayList<Funcionario> buscarFuncionarioAtivoPorCpf(String cpf) throws PessoaException;
    ArrayList<Funcionario> getFuncionarios();
    ArrayList<Funcionario> getFuncionariosAtivo();
    boolean verificarExisteAdministrador();
    
    // cargos
    void adicionarCargo(String nome, String descricao, String salario) throws CargoException;
    void removerCargo(int id) throws CargoException;
    void editarCargo(int id, String nome, String descricao, String salario) throws CargoException;
    Cargo buscarCargoPorNome(String nome) throws CargoException;
    Cargo buscarCargoAtivoPorNome(String nome) throws CargoException;
    Cargo buscarCargoPorId(int id) throws CargoException;
    Cargo buscarCargoAtivoPorId(int id) throws CargoException;
    ArrayList<Cargo> getCargos();
    ArrayList<Cargo> getCargosAtivo();
    boolean verificarExisteCargoAdministrador();
    
    //login
    void login(String email, String senha) throws LoginException;
    String usuarioLogado();
    String nomeUsuarioLogado();
    
    //pizza
    void adicionarPizza(String nome, String valor, String ingredientes, String disponivel, String tipoPizza) throws PizzaException;
    void removerPizza(int id);
    void editarPizza(int id, String nome, String valor, String ingredientes, String disponivel, String tipoPizza) throws PizzaException;
    Pizza buscarPizzaPorNome(String nome) throws PizzaException;
    Pizza buscarPizzaAtivaPorNome(String nome) throws PizzaException;
    Pizza buscarPizzaPorId(int id) throws PizzaException;
    Pizza buscarPizzaAtivaPorId(int id) throws PizzaException;
    ArrayList<Pizza> getPizzas();
    ArrayList<Pizza> getPizzasAtiva();
    
    //comanda
    ArrayList listaFuncionariosComanda();
    void adicionarComanda(String dataCompra, String horario, String formaPagamento, String funcionario, ArrayList pizzas, Cliente cliente, double valorComanda, String entrega);
    void editarComanda(String dataCompra, String horario, String formaPagamento, String funcionario, ArrayList pizzas, Cliente cliente, double valorComanda, String entrega);
    void mudarStatusComanda(String chave, String entrega);
    void deletarComanda(String chave);
    ArrayList<RelatorioComanda> getRelatorioComanda();
    RelatorioComanda getRelatorioComandaPorData(String data) throws ComandaException;;
    RelatorioComanda getRelatorioComandaDeHoje();
    Comanda getComandaPorChavePrimaria(String chave);
    
}
