
package UI.tela_vendas.tela_gerenciamento;

import negocio.entidade.Cliente;
import negocio.entidade.Funcionario;
import negocio.entidade.Pizza;
import java.util.ArrayList;

public class TableViewComanda {
    private String id = "";
    private String pagamento = "";
    private Funcionario funcionario = null;
    private Cliente cliente = null;
    private String entrega;
    private ArrayList<Pizza> pizzas = null;
    private String valor = "";
    private String endereco;
    private String data;
    private String horario;

    public TableViewComanda(String id, String pagamento, Funcionario funcionario, Cliente cliente, ArrayList<Pizza> pizzas, double valor, String entrega) {
        this.id = id;
        this.pagamento = pagamento;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.pizzas = pizzas;
        this.entrega = entrega;
        this.valor = String.valueOf(valor);
    }
    
    public TableViewComanda(String id, String pagamento, Funcionario funcionario, Cliente cliente, ArrayList<Pizza> pizzas, double valor, String entrega, String endereco) {
        this.id = id;
        this.pagamento = pagamento;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.pizzas = pizzas;
        this.entrega = entrega;
        this.valor = String.valueOf(valor);
        this.endereco = endereco;
    }
    
    public TableViewComanda(String id, String data, String horario, String pagamento, Funcionario funcionario, Cliente cliente, ArrayList<Pizza> pizzas, double valor, String endereco, String entrega) {
        this.id = id;
        this.pagamento = pagamento;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.pizzas = pizzas;
        this.entrega = entrega;
        this.valor = String.valueOf(valor);
        this.endereco = endereco;
        this.horario = horario;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getHorario() {
        return horario;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEntrega() {
        return entrega;
    }

    public String getId() {
        return id;
    }

    public String getPagamento() {
        return pagamento;
    }

    public String getFuncionario() {
        return funcionario.getNome();
    }

    public String getCliente() {
        return cliente.getNome();
    }

    public String getPizzas() {
        String pizzas = "";
        for(int i = 0; i < this.pizzas.size(); i++){
            if((i+1) == this.pizzas.size()){
                pizzas += this.pizzas.get(i).getNome();
            }
            else{
                pizzas += this.pizzas.get(i).getNome() + ", ";
            }
        }
        return pizzas;
    }
    
    public ArrayList getPizzasArray() {
        return pizzas;
    }

    public String getValor() {
        return valor;
    }
    
}
