
package dados.repositorios.interfaces_repositorios;

import negocio.entidade.Comanda;
import negocio.entidade.RelatorioComanda;
import java.util.ArrayList;

public interface IRepositorioComanda {
    
    Comanda getComandaPorChavePrimaria(String chave);
    ArrayList<RelatorioComanda> getRelatorioComanda();
    RelatorioComanda getRelatorioComandaPorData(String data);
    boolean insertComanda(Comanda comanda);
    boolean updateComanda(Comanda comanda);
    boolean deleteComanda(String chave);
}
