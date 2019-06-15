
package negocio.entidade;

import java.util.ArrayList;

public class Cliente extends Pessoa{

    public Cliente(String nome, String cidade, String rua, String numero) {       
        super(nome, null, new Endereco(cidade, rua, numero));
    }
    
    
}
