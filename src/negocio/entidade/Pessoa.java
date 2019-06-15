
package negocio.entidade;

import java.io.Serializable;

public abstract class Pessoa implements Serializable{
    private String nome;
    private String cpf;
    private boolean inativo;
    private int id;
    private Endereco endereco;

    public Pessoa(String nome, String cpf, Endereco endereco) {
        this.endereco = endereco;
        this.inativo = false;
        this.nome = nome;
        this.cpf = cpf;
    }
    
    public Pessoa(String nome, String cpf, int id){
        this.nome = nome;
        this.cpf = cpf;
        this.id = id;
    }
   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean getInativo() {
        return inativo;
    }
    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pessoa){
            Pessoa pessoa = (Pessoa) obj;
            if(pessoa.getInativo()){
                return false;
            }
            if(pessoa.getCpf() != null && pessoa.getCpf().equals(this.cpf)){
                return true;
            }
            if(pessoa.getId() != -1 && pessoa.getId() == this.id){
                return true;
            }
        }
        return false;
    }
}
