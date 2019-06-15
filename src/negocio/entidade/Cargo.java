package negocio.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import negocio.NegocioCargo;
import negocio.interfaces.INegocioCargo;
/**
 * Essa é a classe cargo, o cargo tem um id, que para todos os cargos são ids diferentes, tem também um nome, uma descrição sobre
 * o cargo, um salário base e terá um atributo que diz, se esse cargo terá acesso como gerente ao sistema.
 * @author Felipe
 */
public class Cargo implements Serializable{
    private int id;
    private String nome;
    private boolean inativo;
    private String descricao;
    private double salarioBase;
    
    public Cargo(int id, String nome, String descricao, double salarioBase, boolean inativo) {
        this.nome = nome;
        this.inativo = false;
        this.descricao = descricao;
        this.salarioBase = salarioBase;
        this.id = id;
        this.inativo = inativo;
    }

    public Cargo(String nome, String descricao, double salarioBase) {
        this.nome = nome;
        this.inativo = false;
        this.descricao = descricao;
        this.salarioBase = salarioBase;
    }

    public Cargo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Cargo(String nome) {
        this.nome = nome;
    }
 
    public boolean getInativo() {
        return inativo;
    }
    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cargo){
            Cargo cargo = (Cargo) obj;
            if(cargo.getInativo()){
                return false;
            }
            if(cargo.getNome() != null && cargo.getNome().equals(this.nome)){
                return true;
            }
            if(cargo.getId() != -1 && cargo.getId() == this.id){
                return true;
            }
        }
        return false;
    }
}
