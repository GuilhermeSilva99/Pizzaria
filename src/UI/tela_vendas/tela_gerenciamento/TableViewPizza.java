
package UI.tela_vendas.tela_gerenciamento;

public class TableViewPizza {
    private String id = "";
    private String nome = "";
    private String ingredientes = "";
    private String valor = "";

    public TableViewPizza(String id, String nome, String ingredientes, double valor) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.valor = String.valueOf(valor);
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    public String getValor() {
        return valor;
    }
}
