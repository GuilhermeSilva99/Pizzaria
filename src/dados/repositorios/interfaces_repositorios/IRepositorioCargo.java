
package dados.repositorios.interfaces_repositorios;

import negocio.entidade.Cargo;
import negocio.exptions.CargoException;
import java.util.ArrayList;
import java.util.List;

public interface IRepositorioCargo {
    void adicionar(Cargo cargo) throws CargoException;
    void remover(Cargo cargo);
    void editar(Cargo cargo);
    ArrayList<Cargo> getCargos();
    ArrayList<Cargo> getCargosAtivo();
    ArrayList<Cargo> buscarCargoPorNome(String nome);
    ArrayList<Cargo> buscarCargoPorId(int id);
    ArrayList<Cargo> buscarCargoAtivoPorNome(String nome);
    ArrayList<Cargo> buscarCargoAtivoPorId(int id);
    ArrayList<Cargo> buscarCargoVinculadoFuncionario(int id);
    boolean verificarExisteCargoAdministrador();
}
