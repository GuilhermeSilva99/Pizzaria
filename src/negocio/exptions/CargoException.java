
package negocio.exptions;

public class CargoException extends Exception{
    private boolean nome;
    private boolean descricao;
    private boolean salario;
    
    public CargoException(String menssagem) {
        super(menssagem);
        this.nome = false;
        this.descricao = false;
        this.salario = false;
    }

    public boolean getNome() {
        return nome;
    }

    public void setNome(boolean nome) {
        this.nome = nome;
    }

    public boolean getDescricao() {
        return descricao;
    }

    public void setDescricao(boolean descricao) {
        this.descricao = descricao;
    }

    public boolean getSalario() {
        return salario;
    }

    public void setSalario(boolean salario) {
        this.salario = salario;
    }
    
}
