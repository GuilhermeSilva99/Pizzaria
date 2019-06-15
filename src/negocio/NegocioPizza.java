
package negocio;

import negocio.entidade.Pizza;
import negocio.exptions.PizzaException;
import dados.repositorios.interfaces_repositorios.IRepositorioPizza;
import java.util.ArrayList;
import negocio.interfaces.INegocioPizza;
import dados.repositorios.RepositorioPizza;

public class NegocioPizza implements INegocioPizza{
    private IRepositorioPizza repPizza;

    public NegocioPizza() {
        this.repPizza = new RepositorioPizza();
    }
    
    private void verificacaoDados(String nome, String ingredientes, String valor) throws PizzaException{
        PizzaException erro = new PizzaException("Dados Inválidos");
        boolean flag = false;
        try{
            double a = Double.parseDouble(nome.replace(",", "."));
            flag = true;
            erro.setNome(true);
        }catch(Exception e){}
        try{
            double a = Double.parseDouble(ingredientes.replace(",", "."));
            flag = true;
            erro.setIngredientes(true);
        }catch(Exception e){}
        try{
            double a = Double.parseDouble(valor.replace(",", "."));
        }catch(Exception e)
        {
            flag = true;
            erro.setValor(true);
        }
   
        if(flag){
            throw erro;
        }
    }
    
    @Override
    public void adicionar(String nome, String valor, String ingredientes, boolean disponivel, String tipoPizza) throws PizzaException{
        verificacaoDados(nome, ingredientes, valor);
        Pizza pizza = new Pizza(nome, Double.parseDouble(valor), ingredientes,disponivel,tipoPizza);
        if(repPizza.buscarPizzaAtivaPorNome(nome).size() == 0){
            repPizza.adicionar(pizza);
            return;
        }
        throw new PizzaException("Essa Pizza já existe!");
    }
    
    @Override
    public void remover(int id){
        Pizza p = repPizza.buscarPizzaAtivaPorId(id).get(0);
        repPizza.remover(p);
    }
    
    @Override
    public void editar(int id, String nome, String valor, String ingredientes, boolean disponivel, String tipoPizza) throws PizzaException{
        verificacaoDados(nome, ingredientes, valor);
        Pizza p = repPizza.buscarPizzaAtivaPorId(id).get(0);
        if(!nome.equals(p.getNome()) && repPizza.buscarPizzaAtivaPorNome(nome).size() > 0){
            throw new PizzaException("Essa Pizza já existe!");
        }
        p.setNome(nome);
        p.setDisponivel(disponivel);
        p.setIngredientes(ingredientes);
        p.setTipoPizza(tipoPizza);
        p.setValor(Double.parseDouble(valor));
        repPizza.editar(p);
    }
    
    @Override
    public Pizza buscarPizzaPorNome(String nome) throws PizzaException{
        nome = nome.toLowerCase().replace("  ", " ").trim();
        ArrayList<Pizza> pizzas = repPizza.buscarPizzaPorNome(nome);
        if(pizzas.size() == 0)
            throw new PizzaException("Nenhuma pizza foi encontrado com essas informações");
        return pizzas.get(0);
    }
    
    @Override
    public Pizza buscarPizzaAtivaPorNome(String nome) throws PizzaException{
        nome = nome.toLowerCase().replace("  ", " ").trim();
        ArrayList<Pizza> pizzas = repPizza.buscarPizzaAtivaPorNome(nome);
        if(pizzas.size() == 0)
            throw new PizzaException("Nenhuma pizza foi encontrado com essas informações");
        return pizzas.get(0);
    }
    
    @Override
    public Pizza buscarPizzaPorId(int id) throws PizzaException{
        ArrayList<Pizza> pizzas = repPizza.buscarPizzaPorId(id);
        if(pizzas.size() == 0)
            throw new PizzaException("Nenhuma pizza foi encontrado com essas informações");
        return pizzas.get(0);
    }
    
    @Override
    public Pizza buscarPizzaAtivaPorId(int id) throws PizzaException{
        ArrayList<Pizza> pizzas = repPizza.buscarPizzaAtivaPorId(id);
        if(pizzas.size() == 0)
            throw new PizzaException("Nenhuma pizza foi encontrado com essas informações");
        return pizzas.get(0);
    }
    
    @Override
    public ArrayList<Pizza> getPizzas() {
        ArrayList<Pizza> pizzas = null;
        try{
            pizzas = repPizza.getPizzas();  
        }catch(Exception e){}
        return pizzas;
    }
    
    @Override
    public ArrayList<Pizza> getPizzasAtiva() {
        ArrayList<Pizza> pizzas = null;
        try{
            pizzas = repPizza.getPizzasAtiva();  
        }catch(Exception e){}
        return pizzas;
    }
}
