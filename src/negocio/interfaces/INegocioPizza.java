
package negocio.interfaces;

import negocio.entidade.Pizza;
import negocio.exptions.PizzaException;
import java.util.ArrayList;

public interface INegocioPizza {
    void adicionar(String nome, String valor, String ingredientes, boolean disponivel, String tipoPizza) throws PizzaException;
    void remover(int id);
    void editar(int id, String nome, String valor, String ingredientes, boolean disponivel, String tipoPizza) throws PizzaException;
    Pizza buscarPizzaPorNome(String nome) throws PizzaException;
    Pizza buscarPizzaAtivaPorNome(String nome) throws PizzaException;
    Pizza buscarPizzaPorId(int id) throws PizzaException;
    Pizza buscarPizzaAtivaPorId(int id) throws PizzaException;
    ArrayList<Pizza> getPizzas();
    ArrayList<Pizza> getPizzasAtiva();
}
