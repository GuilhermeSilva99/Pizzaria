
package UI.tela_cargo;

public class TableViewCargo {
    private String id = "";
    private String nome = "";
    private String descricao = "";
    private String salario = "";
    private String gerencia = "";

    public TableViewCargo(String id, String nome, String descricao, double salario) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.salario = String.valueOf(salario);
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSalario() {
        return salario;
    }

    public String getGerencia() {
        return gerencia;
    }
    
}
