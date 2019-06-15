package negocio.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import negocio.NegocioComanda;
import negocio.interfaces.INegocioComanda;

/**
 * Essa classe modela os Pedidos(comandas),
 * Ela tem como atributos um atributo static que atribui um codigo unico para cada objeto de Comanda,
 * Para cada objeto de comanda tem um atributo dataCompra que guarda a data da venda, uma string que guarda a forma de pagamento,
 * Um funcionario associado a venda, um cliente, e um arraylist pizzas que guarda as pizzas pedidas.
 * @author Alex Lopes
 */
public class Comanda implements Serializable{
    private String dataCompra;
    private String horario;
    private String formaPagamento;
    private Funcionario funcionario;
    private Cliente cliente;
    private String entrega;
    private ArrayList<Pizza> pizzas;
    private double valorComanda;
    private int id;

    public Comanda(int id, String dataCompra, String horario, String formaPagamento, Funcionario funcionario, 
            ArrayList<Pizza> pizzas, Cliente cliente, double valorComanda, String entrega) {
        this.dataCompra = dataCompra;
        this.horario = horario;
        this.formaPagamento = formaPagamento;
        this.funcionario = funcionario;
        this.pizzas = pizzas;
        this.cliente = cliente;
        this.valorComanda = valorComanda;
        this.entrega = entrega;
        this.id = id;
    }
    
    public Comanda(String dataCompra, String horario, String formaPagamento, Funcionario funcionario, ArrayList<Pizza> pizzas, Cliente cliente, double valorComanda, String entrega) {
        this.dataCompra = dataCompra;
        this.horario = horario;
        this.formaPagamento = formaPagamento;
        this.funcionario = funcionario;
        this.pizzas = pizzas;
        this.cliente = cliente;
        this.valorComanda = valorComanda;
        this.entrega = entrega;
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    

    public String getEntrega() {
        return entrega;
    }

    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }
    
    public Comanda(int id){
        this.id = id;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorComanda() {
        return valorComanda;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Comanda){
            Comanda comanda = (Comanda) obj;
            if(comanda.getId() == this.id){
                return true;
            }
        }
        return false;
    }
}
