
package negocio.exptions;

public class PessoaException extends Exception{
    private boolean nome;
    private boolean cpf;
    private boolean cidade;
    private boolean rua;
    private boolean numero;
    
    public PessoaException(String menssagem) {
        super(menssagem);
        nome = false;
        cpf = false;
        cidade = false;
        rua = false;
        numero = false;
    }

    public boolean getNome() {
        return nome;
    }

    public void setNome(boolean nome) {
        this.nome = nome;
    }

    public boolean getCpf() {
        return cpf;
    }

    public void setCpf(boolean cpf) {
        this.cpf = cpf;
    }

    public boolean getCidade() {
        return cidade;
    }

    public void setCidade(boolean cidade) {
        this.cidade = cidade;
    }

    public boolean getRua() {
        return rua;
    }

    public void setRua(boolean rua) {
        this.rua = rua;
    }

    public boolean getNumero() {
        return numero;
    }

    public void setNumero(boolean numero) {
        this.numero = numero;
    }
    
    
}
