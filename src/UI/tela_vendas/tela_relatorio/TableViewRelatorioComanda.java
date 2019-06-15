
package UI.tela_vendas.tela_relatorio;

public class TableViewRelatorioComanda {
    private String id = "";
    private String data = "";
    private int quantidade;
    private double valor;

    public TableViewRelatorioComanda(String id, String data, int quantidade, double valor) {
        this.id = id;
        this.data = data;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    
    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getQuantidade() {
        return Integer.toString(quantidade);
    }

    public String getValor() {
        return Double.toString(valor);
    }
}
