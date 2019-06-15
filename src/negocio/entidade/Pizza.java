package negocio.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import negocio.NegocioPizza;
import negocio.interfaces.INegocioPizza;

/**
 * Essa Molda a estrutura de uma pizza, com o atributo nome, o valores de cada tamanho de pizza(Pequena, Media, Grande, e Extra Grande)
 * Um atributo do tipo string que descreve os ingredientes, um boolean que fala a disponibilidade da pizza no estoque,
 *E um atributo string que guarda o tipo da pizza(Doce,Salgada,Vegetariana,Fit), e  um atributo static que serve para definir um id
 * que é o outro atributo, para ter um identificador unico em cada pizza.
 * @author Alex Lopes
 *
 */
public class Pizza implements Serializable{
    private String nome;
    private double valor;
    private String ingredientes;
    private boolean disponivel;
    private String tipoPizza;
    private boolean inativo;
    private int id;

    /**
     * Contrutor Pizza
     * @param nome String - Nome da pizza;
     * @param valor Double - Valor da pizza Media;
     * @param ingredientes String - Descrição dos ingredientes;
     * @param disponivel Boolean - Identifica se a pizza está disponivel em estoque;
     * @param tipoPizza String - Mostra o tipo da pizza (Doce,Salgada,Vegetariana,Fit)
     */
    public Pizza(int id, String nome, double valor, String ingredientes, boolean disponivel, String tipoPizza, boolean inativo) {
        this.nome = nome;
        this.valor = valor;
        this.ingredientes = ingredientes;
        this.disponivel = disponivel;
        this.tipoPizza = tipoPizza;
        this.inativo = inativo;
        this.id= id;
    }
    
    public Pizza(String nome, double valor, String ingredientes, boolean disponivel, String tipoPizza) {
        this.nome = nome;
        this.valor = valor;
        this.ingredientes = ingredientes;
        this.disponivel = disponivel;
        this.tipoPizza = tipoPizza;
        int id = id() + 1;
        this.id= id;
    }
    
    public Pizza(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public boolean getInativo() {
        return inativo;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getTipoPizza() {
        return tipoPizza;
    }

    public void setTipoPizza(String tipoPizza) {
        this.tipoPizza = tipoPizza;
    }

    public int getId() {
        return id;
    }

    private int id(){
        INegocioPizza negocio = new NegocioPizza();
        ArrayList pizzas = negocio.getPizzas();
        if(pizzas.size() > 0){
            int id = ((Pizza) pizzas.get(pizzas.size() - 1)).getId();
            return id;
        } else{
            return 0;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pizza){
            Pizza pizza = (Pizza) obj;
            if(pizza.getInativo()){
                return false;
            }
            if(pizza.getNome() != null && pizza.getNome().equals(this.nome)){
                return true;
            }
            if(pizza.getId() != -1 && pizza.getId() == this.id){
                return true;
            }
        }
        return false;
    }
}
