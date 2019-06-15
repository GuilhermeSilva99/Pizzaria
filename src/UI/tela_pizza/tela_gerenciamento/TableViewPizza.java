
package UI.tela_pizza.tela_gerenciamento;

public class TableViewPizza {
    private String id = "";
    private String nome = "";
    private String ingredientes = "";
    private String valor = "";
    private String tipo = "";
    private String disponibilidade = "";

    public TableViewPizza(String id, String nome, String ingredientes, double valor, String tipo, boolean disponibilidade) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.valor = String.valueOf(valor);
        this.tipo = tipo;
        if(disponibilidade){
            this.disponibilidade = "Sim";
        }else{
            this.disponibilidade = "Não";
        }
        
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getValor() {
        return valor;
    }
    
    public String getTipo() {
        return tipo;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }
}
