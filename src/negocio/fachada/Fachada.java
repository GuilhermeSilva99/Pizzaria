
package negocio.fachada;

import negocio.entidade.Cargo;
import negocio.entidade.Endereco;
import negocio.entidade.Funcionario;
import negocio.entidade.InformacoesUsuario;
import negocio.entidade.Pizza;
import negocio.entidade.RelatorioComanda;
import negocio.exptions.CargoException;
import negocio.exptions.ComandaException;
import negocio.exptions.LoginException;
import negocio.exptions.PessoaException;
import negocio.exptions.PizzaException;
import java.util.ArrayList;
import negocio.NegocioCargo;
import negocio.NegocioComanda;
import negocio.NegocioFuncionario;
import negocio.NegocioPizza;
import negocio.entidade.Cliente;
import negocio.entidade.Comanda;
import negocio.fachada.interfaces.IFachada;
import negocio.interfaces.INegocioCargo;
import negocio.interfaces.INegocioComanda;
import negocio.interfaces.INegocioFuncionario;
import negocio.interfaces.INegocioPizza;

public class Fachada implements IFachada{
    private INegocioFuncionario negocioFuncionario;
    private INegocioCargo negocioCargo;
    private INegocioPizza negocioPizza;
    private INegocioComanda negocioComanda;
    private static Fachada fachada;

    public Fachada() {
        this.negocioFuncionario = new NegocioFuncionario();
        this.negocioCargo = new NegocioCargo();
        this.negocioPizza = new NegocioPizza();
        this.negocioComanda = new NegocioComanda();
    }
    
    public static Fachada getFachada(){
        if(fachada == null){
            fachada = new Fachada();
        }
        return fachada;
    }
    
    // Funcionarios.......................................................
    
    @Override
    public void adicionarFuncionario(Endereco endereco, String nomeUsuario, String senha, String status, Cargo cargo, String dataAdimissao, String nome, String cpf) throws PessoaException, LoginException {
        negocioFuncionario.adicionar(endereco, nomeUsuario, senha, status, cargo, dataAdimissao, nome, cpf);
    }

    @Override
    public void removerFuncionario(int id) {
        negocioFuncionario.remover(id);
    }

    @Override
    public void editarFuncionario(int id, String cidade, String rua, String numero, String email, String senha, String status, Cargo cargo, String dataAdimissao, String nome, String cpf) throws LoginException, PessoaException {
        negocioFuncionario.editar(id, cidade, rua, numero, email, senha, status, cargo, dataAdimissao, nome, cpf);
    }

    @Override
    public ArrayList<Funcionario> buscarFuncionarioPorNome(String nome) throws PessoaException{
        return negocioFuncionario.buscarFuncionarioPorNome(nome);
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioAtivoPorNome(String nome) throws PessoaException{
        return negocioFuncionario.buscarFuncionarioAtivoPorNome(nome);
    }
    
    @Override
    public Funcionario buscarFuncionarioPorId(int id) throws PessoaException{
        return negocioFuncionario.buscarFuncionarioPorId(id);
    }
    
    @Override
    public Funcionario buscarFuncionarioAtivoPorId(int id) throws PessoaException{
        return negocioFuncionario.buscarFuncionarioPorId(id);
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioPorCpf(String cpf) throws PessoaException{
        return negocioFuncionario.buscarFuncionarioPorCpf(cpf);
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioAtivoPorCpf(String cpf) throws PessoaException{
        return negocioFuncionario.buscarFuncionarioAtivoPorCpf(cpf);
    }
    
    @Override
    public ArrayList<Funcionario> getFuncionarios(){
        return negocioFuncionario.getFuncionarios();
    }
    
    @Override
    public ArrayList<Funcionario> getFuncionariosAtivo(){
        return negocioFuncionario.getFuncionariosAtivo();
    }
    
    @Override
    public boolean verificarExisteAdministrador(){
        return negocioFuncionario.VerificarExisteAdministrador();
    }
    
    // cargos.....................................................

    @Override
    public void adicionarCargo(String nome, String descricao, String salario) throws CargoException {
        negocioCargo.adicionar(nome, descricao, salario);
    }

    @Override
    public void removerCargo(int id) throws CargoException{
        negocioCargo.remover(id);
    }

    @Override
    public void editarCargo(int id, String nome, String descricao, String salario) throws CargoException {
        negocioCargo.editar(id, nome, descricao, salario);
    }

    @Override
    public Cargo buscarCargoPorNome(String nome) throws CargoException{
        return negocioCargo.buscarCargoPorNome(nome);
    }
    
    @Override
    public Cargo buscarCargoAtivoPorNome(String nome) throws CargoException{
        return negocioCargo.buscarCargoAtivoPorNome(nome);
    }
    
    @Override
    public Cargo buscarCargoPorId(int id) throws CargoException{
        return negocioCargo.buscarCargoPorId(id);
    }
    
    @Override
    public Cargo buscarCargoAtivoPorId(int id) throws CargoException{
        return negocioCargo.buscarCargoAtivoPorId(id);
    }
    
    @Override
    public ArrayList<Cargo> getCargos(){
        return negocioCargo.getCargos();
    }
    
    @Override
    public ArrayList<Cargo> getCargosAtivo(){
        return negocioCargo.getCargosAtivo();
    }
    
    @Override
    public boolean verificarExisteCargoAdministrador(){
        return negocioCargo.verificarExisteCargoAdministrador();
    }
    
    // Login......................................................
    
    @Override
    public void login(String email, String senha) throws LoginException{
        Funcionario fun = negocioFuncionario.buscarLogin(email, senha);
        InformacoesUsuario.getInstance();
        InformacoesUsuario.getInstance().setFuncionario(fun);
        InformacoesUsuario.getInstance().setNome(fun.getNome());
        if(fun.getCargo().getNome().equals("administrador"))
            InformacoesUsuario.getInstance().setAdministrador(true);
    }
    
    @Override
    public String usuarioLogado(){
        if(InformacoesUsuario.getInstance().getFuncionario().getCargo().getNome().toLowerCase().trim()
                .equals("administrador")){
            return "administrador";
        }
        else if(InformacoesUsuario.getInstance().getFuncionario().getCargo().getNome().toLowerCase().trim()
                .equals("gerente")){
            return "gerente";
        }
        else{
            return "funcionario";
        }
    }
    
    @Override
    public String nomeUsuarioLogado(){
        return InformacoesUsuario.getInstance().getNome();
    }
    
    // Pizzas.............................................................
    
    @Override
    public void adicionarPizza(String nome, String valor, String ingredientes, String disponivel, String tipoPizza) throws PizzaException{
        boolean b = false;
        if(disponivel.equals("Sim")){
           b = true;
        }else{
           b = false;
        }
        negocioPizza.adicionar(nome, valor, ingredientes, b, tipoPizza);
    }
    
    @Override
    public void removerPizza(int id){
        negocioPizza.remover(id);
    }
    
    @Override
    public void editarPizza(int id, String nome, String valor, String ingredientes, String disponivel, String tipoPizza) throws PizzaException{
       boolean b = false;
       if(disponivel.equals("Sim")){
           b = true;
       }else{
           b = false;
       }
       negocioPizza.editar(id, nome, valor, ingredientes, b, tipoPizza);
    }
    
    @Override
    public Pizza buscarPizzaPorNome(String nome) throws PizzaException{
        return negocioPizza.buscarPizzaPorNome(nome);
    }
    
    @Override
    public Pizza buscarPizzaAtivaPorNome(String nome) throws PizzaException{
        return negocioPizza.buscarPizzaAtivaPorNome(nome);
    }
    
    @Override
    public Pizza buscarPizzaPorId(int id) throws PizzaException{
        return negocioPizza.buscarPizzaPorId(id);
    }
    
    @Override
    public Pizza buscarPizzaAtivaPorId(int id) throws PizzaException{
        return negocioPizza.buscarPizzaAtivaPorId(id);
    }
    
    @Override
    public ArrayList<Pizza> getPizzas() {
        return negocioPizza.getPizzas();
    }
    
    @Override
    public ArrayList<Pizza> getPizzasAtiva() {
        return negocioPizza.getPizzasAtiva();
    }
    
    // Comandas....................................................
    
    @Override
    public void adicionarComanda(String dataCompra, String horario, String formaPagamento, String funcionario, ArrayList pizzas, Cliente cliente, double valorComanda, String entrega){
        negocioComanda.adicionarComanda(dataCompra, horario, formaPagamento, funcionario, pizzas, cliente, valorComanda, entrega);
    }
    
    @Override
    public void editarComanda(String dataCompra, String horario, String formaPagamento, String funcionario, ArrayList pizzas, Cliente cliente, double valorComanda, String entrega){
        negocioComanda.editarComanda(dataCompra, horario, formaPagamento, funcionario, pizzas, cliente, valorComanda, entrega);
    }
    
    public void deletarComanda(String chave){
        negocioComanda.deletarComanda(chave);
    }
    
    @Override
    public void mudarStatusComanda(String chave, String entrega){
        negocioComanda.mudarStatusComanda(chave, entrega);
    }
    
    @Override
    public ArrayList<RelatorioComanda> getRelatorioComanda(){
        return negocioComanda.getRelatorioComanda();
    }
    
    @Override
    public RelatorioComanda getRelatorioComandaPorData(String data) throws ComandaException{
        return negocioComanda.getRelatorioComandaPorData(data);
    }
    
    @Override
    public RelatorioComanda getRelatorioComandaDeHoje(){
        return negocioComanda.getRelatorioComandaDeHoje();
    }
    
    @Override
    public Comanda getComandaPorChavePrimaria(String chave){
        return negocioComanda.getComandaPorChavePrimaria(chave);
    }
    
    @Override
    public ArrayList listaFuncionariosComanda(){
        boolean auxAdm = InformacoesUsuario.getInstance().getAdministrador();
        InformacoesUsuario.getInstance().setAdministrador(true);
        ArrayList funcionarios = getFuncionariosAtivo();
        InformacoesUsuario.getInstance().setAdministrador(auxAdm);
        return funcionarios;
    }
}
