package negocio.entidade;

import java.io.Serializable;

/**
 * Essa é classe Endereço, que tem como atributos, o numero da casa, a rua e a cidade.
 * Essa classe servirá como atributo da classe Pessoa.
 * @author Felipe
 */
public class Endereco implements Serializable{
    private String numero;
    private String rua;
    private String cidade;

    public Endereco(String cidade, String rua, String numero) {
        this.numero = numero;
        this.rua = rua;
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
