
package dados.repositorios.interfaces_repositorios;

import negocio.entidade.Pizza;
import java.util.ArrayList;

public interface IRepositorioPizza {
    void adicionar(Pizza pizza);
    void remover(Pizza pizza);
    void editar(Pizza pizza);  
    ArrayList<Pizza> getPizzas();
    ArrayList<Pizza> getPizzasAtiva();
    ArrayList<Pizza> buscarPizzaPorNome(String nome);
    ArrayList<Pizza> buscarPizzaPorId(int id);
    ArrayList<Pizza> buscarPizzaAtivaPorNome(String nome);
    ArrayList<Pizza> buscarPizzaAtivaPorId(int id);
}
