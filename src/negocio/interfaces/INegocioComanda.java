
package negocio.interfaces;

import negocio.entidade.Comanda;
import negocio.entidade.RelatorioComanda;
import negocio.exptions.ComandaException;
import java.util.ArrayList;
import negocio.entidade.Cliente;

public interface INegocioComanda {    
    void adicionarComanda(String dataCompra, String horario, String formaPagamento, String funcionario, ArrayList pizzas, Cliente cliente, double valorComanda, String entrega);
    void editarComanda(String dataCompra, String horario, String formaPagamento, String funcionario, ArrayList pizzas, Cliente cliente, double valorComanda, String entrega);
    void mudarStatusComanda(String chave, String entrega);
    ArrayList<RelatorioComanda> getRelatorioComanda();
    RelatorioComanda getRelatorioComandaPorData(String data) throws ComandaException;
    RelatorioComanda getRelatorioComandaDeHoje();
    Comanda getComandaPorChavePrimaria(String chave);
    public void deletarComanda(String chave);
}
