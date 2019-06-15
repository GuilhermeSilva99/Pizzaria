
package negocio.exptions;

public class AdministradorException extends Exception{
    private boolean nome;
    private boolean email;
    private boolean senha;
    private boolean cpf;
    private boolean cidade;
    private boolean rua;
    private boolean numero;

    public AdministradorException(String menssagem) {
        super(menssagem);
        nome = false;
        email = false;
        senha = false;
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

    public boolean getNome() {
        return nome;
    }

    public void setNome(boolean nome) {
        this.nome = nome;
    }

    public boolean getEmail() {
        return email;
    }

    public void setEmail(boolean email) {
        this.email = email;
    }

    public boolean getSenha() {
        return senha;
    }

    public void setSenha(boolean senha) {
        this.senha = senha;
    }
    
}
