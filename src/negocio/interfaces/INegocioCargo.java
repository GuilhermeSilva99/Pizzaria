
package negocio.interfaces;

import negocio.entidade.Cargo;
import negocio.exptions.CargoException;
import java.util.ArrayList;

public interface INegocioCargo {
    void adicionar(String nome, String descricao, String salario) throws CargoException;
    void remover(int id) throws CargoException;
    void editar(int id, String nome, String descricao, String salario) throws CargoException;
    ArrayList<Cargo> getCargos();
    ArrayList<Cargo> getCargosAtivo();
    Cargo buscarCargoPorNome(String nome) throws CargoException;
    Cargo buscarCargoAtivoPorNome(String nome) throws CargoException;
    Cargo buscarCargoPorId(int id) throws CargoException;
    Cargo buscarCargoAtivoPorId(int id) throws CargoException;
    boolean verificarExisteCargoAdministrador();
}

