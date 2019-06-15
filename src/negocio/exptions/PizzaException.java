
package negocio.exptions;

public class PizzaException extends Exception{
    private boolean nome;
    private boolean valor;
    private boolean ingredientes;
    
    public PizzaException(String mensagem) {
        super(mensagem);
    }

    public boolean getNome() {
        return nome;
    }

    public void setNome(boolean nome) {
        this.nome = nome;
    }

    public boolean getValor() {
        return valor;
    }

    public void setValor(boolean valor) {
        this.valor = valor;
    }

    public boolean getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(boolean ingredientes) {
        this.ingredientes = ingredientes;
    }
    
}
