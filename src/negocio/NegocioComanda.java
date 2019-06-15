
package negocio;

import negocio.entidade.Cliente;
import negocio.entidade.Comanda;
import negocio.entidade.Funcionario;
import negocio.entidade.Pizza;
import negocio.entidade.RelatorioComanda;
import negocio.exptions.ComandaException;
import dados.repositorios.interfaces_repositorios.IRepositorioComanda;
import dados.repositorios.interfaces_repositorios.IRepositorioPizza;
import java.time.LocalDate;
import java.util.ArrayList;
import negocio.interfaces.INegocioComanda;
import dados.repositorios.RepositorioComanda;
import dados.repositorios.RepositorioFuncionario;
import dados.repositorios.RepositorioPizza;
import dados.repositorios.interfaces_repositorios.IRepositorioFuncionario;

public class NegocioComanda  implements INegocioComanda{
    private IRepositorioComanda repComanda;

    public NegocioComanda(){
        this.repComanda = new RepositorioComanda();
    }
    
    @Override
    public void adicionarComanda(String dataCompra, String horario, String formaPagamento, String funcionario, ArrayList pizzas, Cliente cliente, double valorComanda, String entrega){
        Comanda comanda = criarComanda(dataCompra, horario, formaPagamento, funcionario, pizzas, cliente, valorComanda, entrega);
        repComanda.insertComanda(comanda);
    }
    
    @Override
    public void editarComanda(String dataCompra, String horario, String formaPagamento, String funcionario, ArrayList pizzas, Cliente cliente, double valorComanda, String entrega){
        Comanda comanda = criarComanda(dataCompra, horario, formaPagamento, funcionario, pizzas, cliente, valorComanda, entrega);
        repComanda.updateComanda(comanda);
    }
    
    public void deletarComanda(String chave){
        repComanda.deleteComanda(chave);
    }
    
    @Override
    public void mudarStatusComanda(String chave, String entrega){
        Comanda comanda = repComanda.getComandaPorChavePrimaria(chave);
        comanda.setEntrega(entrega);
        repComanda.updateComanda(comanda);
    }
    
    private Comanda criarComanda(String dataCompra, String horario, String formaPagamento, String funcionario, ArrayList pizzas, Cliente cliente, double valorComanda, String entrega){
        String[] fun = funcionario.split("-");
        IRepositorioFuncionario repFun = new RepositorioFuncionario();
        IRepositorioPizza repPizza = new RepositorioPizza();
        ArrayList<Pizza> pizzasComanda = new ArrayList();
        for(int i = 0; i < pizzas.size(); i++){
            pizzasComanda.add(repPizza.buscarPizzaAtivaPorId(Integer.parseInt(pizzas.get(i).toString())).get(0));
        }
        
        Comanda comanda = new Comanda(dataCompra, horario, formaPagamento, repFun.buscarFuncionarioAtivoPorId(Integer.parseInt(fun[0].toString())).get(0), pizzasComanda,cliente, valorComanda, entrega);
        return comanda;
    }
    
    @Override
    public ArrayList<RelatorioComanda> getRelatorioComanda(){
        return repComanda.getRelatorioComanda();
    }
    
    @Override
    public RelatorioComanda getRelatorioComandaPorData(String data) throws ComandaException{
        RelatorioComanda relatorio = repComanda.getRelatorioComandaPorData(data);
        if(relatorio == null)
            throw new ComandaException("Nenhum Relatorio de Comanda foi encontrado com essa data");
        return relatorio;
    }
    
    @Override
    public RelatorioComanda getRelatorioComandaDeHoje(){
        return repComanda.getRelatorioComandaPorData(LocalDate.now().toString());
    }
    
    @Override
    public Comanda getComandaPorChavePrimaria(String chave){
        return repComanda.getComandaPorChavePrimaria(chave);
    }
}
