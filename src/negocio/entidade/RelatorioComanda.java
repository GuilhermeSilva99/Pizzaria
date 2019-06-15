
package negocio.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import negocio.NegocioComanda;
import negocio.interfaces.INegocioComanda;

public class RelatorioComanda implements Serializable{
    private String nomeData;
    private ArrayList<Comanda> comandas;
    private Double valorTotal;
    private int id;
    private int quantidadeComandas;

    public RelatorioComanda(String nomeData, int id) {
        this.nomeData = nomeData;
        this.comandas = new ArrayList();
        this.id= id;
    }
    
    public RelatorioComanda(String nomeData) {
        this.nomeData = nomeData;
        this.comandas = new ArrayList();
        this.id= id;
    }

    public int getQuantidadeComandas() {
        return quantidadeComandas;
    }

    public void setQuantidadeComandas(int quantidadeComandas) {
        this.quantidadeComandas = quantidadeComandas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeData() {
        return nomeData;
    }

    public void setNomeData(String nomeData) {
        this.nomeData = nomeData;
    }

    public ArrayList<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(ArrayList<Comanda> comandas) {
        this.comandas = comandas;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RelatorioComanda){
            RelatorioComanda comanda = (RelatorioComanda) obj;
            if(comanda.getNomeData() != null && comanda.getNomeData().equals(this.nomeData)){
                return true;
            }
            if(comanda.getId() != -1 && comanda.getId() == this.id){
                return true;
            }
        }
        return false;
    }
}
