
package UI.tela_funcionario.tela_gerenciamento;

public class TableViewFuncionario {
    private String id = "";
    private String nome = "";
    private String cpf = "";
    private String cidade = "";
    private String rua = "";
    private String numero = "";
    private String cargo = "";
    private String email = "";
    private String senha = "";
    private String data = "";
    private String status = "";

    public TableViewFuncionario(String id, String nome, String cpf, String cargo, String data, String status,
        String email, String senha, String cidade, String rua, String numero) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cidade = cidade;
        this.rua = rua;
        this.numero = numero;
        this.cargo = cargo;
        this.status = status;
        this.data = data;
        this.email = email;
        this.senha = senha;
    }
    public String getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }
}
