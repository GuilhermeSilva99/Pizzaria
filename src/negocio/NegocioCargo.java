
package negocio;

import negocio.entidade.Cargo;
import negocio.entidade.InformacoesUsuario;
import negocio.exptions.CargoException;
import dados.repositorios.interfaces_repositorios.IRepositorioCargo;
import java.util.ArrayList;
import negocio.interfaces.INegocioCargo;
import dados.repositorios.RepositorioCargo;
import java.util.List;

public class NegocioCargo implements INegocioCargo{
    private IRepositorioCargo repCargo;

    public NegocioCargo() {
        this.repCargo = new RepositorioCargo();
    }
    
    private void verificacaoDados(String nome, String descricao, String salario) throws CargoException{
        CargoException erro = new CargoException("Dados Inválidos");
        boolean flag = false;
        try{
            double a = Double.parseDouble(nome.replace(",", "."));
            flag = true;
            erro.setNome(true);
        }catch(Exception e){}
        try{
            double a = Double.parseDouble(descricao.replace(",", "."));
            flag = true;
            erro.setDescricao(true);
        }catch(Exception e){}
        try{
            double a = Double.parseDouble(salario.replace(",", "."));
        }catch(Exception e)
        {
            flag = true;
            erro.setSalario(true);
        }
   
        if(flag){
            throw erro;
        }
    }

    @Override
    public void adicionar(String nome, String descricao, String salario) throws CargoException{
        nome = nome.toLowerCase().replace("  ", " ").trim();
        verificacaoDados(nome, descricao, salario);
        Cargo cargo = new Cargo(nome, descricao, Double.parseDouble(salario.replace(",", ".")));
        if(repCargo.buscarCargoAtivoPorNome(nome).size() == 0){
            repCargo.adicionar(cargo);
            return;
        }
        throw new CargoException("Esse cargo já existe!");
    }

    @Override
    public void remover(int id) throws CargoException{
        List<Cargo> cargoRemove = repCargo.buscarCargoAtivoPorId(id);
        if(repCargo.buscarCargoVinculadoFuncionario(cargoRemove.get(0).getId()).size() > 0)
            throw new CargoException("Há funcionários vinculado a esse cargo");
        repCargo.remover(cargoRemove.get(0));
    }

    @Override
    public void editar(int id, String nome, String descricao, String salario) throws CargoException{
        nome = nome.toLowerCase().replace("  ", " ").trim();
        Cargo cargo = repCargo.buscarCargoAtivoPorId(id).get(0);
        if(!cargo.getNome().equals(nome) && repCargo.buscarCargoAtivoPorNome(nome).size() > 0){
            throw new CargoException("Esse cargo já existe!");
        }
        cargo.setNome(nome);
        cargo.setDescricao(descricao);
        cargo.setSalarioBase(Double.parseDouble(salario.replace(",", ".")));
        repCargo.editar(cargo);
    }
    
    @Override
    public Cargo buscarCargoPorNome(String nome) throws CargoException{
        nome = nome.toLowerCase().replace("  ", " ").trim();
        ArrayList<Cargo> cargos = repCargo.buscarCargoPorNome(nome);
        if(cargos.size() == 0)
            throw new CargoException("Nenhum cargo foi encontrado com essas informações");
        return cargos.get(0);
    }
    
    @Override
    public Cargo buscarCargoAtivoPorNome(String nome) throws CargoException{
        nome = nome.toLowerCase().replace("  ", " ").trim();
        ArrayList<Cargo> cargos = repCargo.buscarCargoAtivoPorNome(nome);
        if(cargos.size() == 0)
            throw new CargoException("Nenhum cargo foi encontrado com essas informações");
        return cargos.get(0);
    }
    
    @Override
    public Cargo buscarCargoPorId(int id) throws CargoException{
        ArrayList<Cargo> cargos = repCargo.buscarCargoPorId(id);
        if(cargos.size() == 0)
            throw new CargoException("Nenhum cargo foi encontrado com essas informações");
        return cargos.get(0);
    }
    
    @Override
    public Cargo buscarCargoAtivoPorId(int id) throws CargoException{
        ArrayList<Cargo> cargos = repCargo.buscarCargoPorId(id);
        if(cargos.size() == 0)
            throw new CargoException("Nenhum cargo foi encontrado com essas informações");
        return cargos.get(0);
    }
    
    @Override
    public ArrayList<Cargo> getCargos() {
        ArrayList<Cargo> cargos = null;
        try{
            cargos = repCargo.getCargos();  
        }catch(Exception e){}
        return cargos;
    }
    
    @Override
    public ArrayList<Cargo> getCargosAtivo() {
        ArrayList<Cargo> cargos = null;
        try{
            cargos = repCargo.getCargosAtivo();  
        }catch(Exception e){}
        return cargos;
    }
    
    @Override
    public boolean verificarExisteCargoAdministrador(){
        return repCargo.verificarExisteCargoAdministrador();
    }
}
