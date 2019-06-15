
package negocio;

import negocio.entidade.Cargo;
import negocio.entidade.Endereco;
import negocio.entidade.Funcionario;
import negocio.entidade.InformacoesUsuario;
import negocio.entidade.Pessoa;
import negocio.exptions.LoginException;
import negocio.exptions.PessoaException;
import java.util.ArrayList;
import negocio.interfaces.INegocioFuncionario;
import dados.repositorios.RepositorioFuncionario;
import dados.repositorios.interfaces_repositorios.IRepositorioFuncionario;

public class NegocioFuncionario implements INegocioFuncionario{
    private IRepositorioFuncionario repFuncionario;

    public NegocioFuncionario() {
        this.repFuncionario = new RepositorioFuncionario();
    }
    
    private void verificacaoDados(String nome, String cpf, String cidade, String rua, String numero) throws PessoaException{
        PessoaException erro = new PessoaException("Dados Inválidos");
        boolean flag = false;
        try{
            double a = Double.parseDouble(nome.replace(",", "."));
            flag = true;
            erro.setNome(true);
        }catch(Exception e){}
        try{
            long a = Long.parseLong(cpf);
            if(cpf.replace(" ", "").length() != 11){
                flag = true;
                erro.setCpf(true);
            }
        }catch(Exception e){
            flag = true;
            erro.setCpf(true);
        }
        try{
            double a = Double.parseDouble(cidade.replace(",", "."));
            flag = true;
            erro.setCidade(true);
        }catch(Exception e){}
        try{
            double a = Double.parseDouble(rua.replace(",", "."));
            flag = true;
            erro.setRua(true);
        }catch(Exception e){}
        try{
            numero = numero.toLowerCase();
            if(!numero.equals("sn")){
                int a = Integer.parseInt(numero);
            }
        }catch(Exception e){
            erro.setNumero(true);
            flag = true;
        }
        
        if(flag){
            throw erro;
        }
    }

    @Override
    public void adicionar(Endereco endereco, String nomeUsuario, String senha, String status, 
    Cargo cargo, String dataAdimissao, String nome, String cpf) throws PessoaException, LoginException {
        verificacaoDados(nome, cpf, endereco.getCidade(), endereco.getRua(), endereco.getNumero());
        verificacaoCadastroLogin(nomeUsuario, senha);
        endereco.setNumero(endereco.getNumero().toUpperCase());
        Funcionario funcionario = new Funcionario(endereco,nomeUsuario,senha,status,cargo,dataAdimissao,nome,cpf);
        if(repFuncionario.buscarFuncionarioAtivoPorCpf(funcionario.getCpf()).size() == 0){
            repFuncionario.adicionar(funcionario);
            return;
        }
        throw new PessoaException("Já existe um funcionario com esse cpf!");
    }

    @Override
    public void remover(int id) {
        Funcionario funcionario = repFuncionario.buscarFuncionarioAtivoPorId(id).get(0);
        repFuncionario.remover(funcionario);
    }

    @Override
    public void editar(int id, String cidade, String rua, String numero, String email, 
    String senha, String status, Cargo cargo, String dataAdimissao, String nome, String cpf) throws LoginException, PessoaException {
        verificacaoDados(nome, cpf, cidade, rua, numero);
        Funcionario funcionario = (Funcionario) repFuncionario.buscarFuncionarioAtivoPorId(id).get(0);
        if(!funcionario.getNomeUsuario().equals(email)){
            verificacaoCadastroLogin(email, null);
        }
        if(!funcionario.getSenha().equals(senha)){
            verificacaoCadastroLogin(null, senha);
        }
        if(!funcionario.getCpf().equals(cpf) && repFuncionario.buscarFuncionarioAtivoPorCpf(cpf).size() > 0){
            throw new PessoaException("Já existe um funcionario com esse cpf!");
        }
        funcionario.setDataAdimissao(dataAdimissao);
        funcionario.setStatus(status);
        funcionario.setCpf(cpf);
        funcionario.setNome(nome);
        funcionario.getEndereco().setCidade(cidade);
        funcionario.getEndereco().setNumero(numero);
        funcionario.getEndereco().setRua(rua);
        funcionario.setCargo(cargo);
        funcionario.setLoginNomeUsuario(email);
        funcionario.setLoginSenha(senha);
        repFuncionario.editar(funcionario);
    }

    @Override
    public ArrayList<Funcionario> buscarFuncionarioPorNome(String nome) throws PessoaException{
        nome = nome.toLowerCase().replace("  ", " ").trim();
        ArrayList<Funcionario> funcionarios = repFuncionario.buscarFuncionarioPorNome(nome);
        if(funcionarios.size() == 0)
            throw new PessoaException("Nenhum funcionario foi encontrado com essas informações");
        return funcionarios;
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioAtivoPorNome(String nome) throws PessoaException{
        nome = nome.toLowerCase().replace("  ", " ").trim();
        ArrayList<Funcionario> funcionarios = repFuncionario.buscarFuncionarioAtivoPorNome(nome);
        if(funcionarios.size() == 0)
            throw new PessoaException("Nenhum funcionario foi encontrado com essas informações");
        return funcionarios;
    }
    
    @Override
    public Funcionario buscarFuncionarioPorId(int id) throws PessoaException{
        ArrayList<Funcionario> funcionarios = repFuncionario.buscarFuncionarioPorId(id);
        if(funcionarios.size() == 0)
            throw new PessoaException("Nenhum funcionario foi encontrado com essas informações");
        return funcionarios.get(0);
    }
    
    @Override
    public Funcionario buscarFuncionarioAtivoPorId(int id) throws PessoaException{
        ArrayList<Funcionario> funcionarios = repFuncionario.buscarFuncionarioAtivoPorId(id);
        if(funcionarios.size() == 0)
            throw new PessoaException("Nenhum funcionario foi encontrado com essas informações");
        return funcionarios.get(0);
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioPorCpf(String cpf) throws PessoaException{
        cpf = cpf.replace("  ", " ").trim();
        ArrayList<Funcionario> funcionarios = repFuncionario.buscarFuncionarioPorCpf(cpf);
        if(funcionarios.size() == 0)
            throw new PessoaException("Nenhum funcionario foi encontrado com essas informações");
        return funcionarios;
    }
    
    @Override
    public ArrayList<Funcionario> buscarFuncionarioAtivoPorCpf(String cpf) throws PessoaException{
        cpf = cpf.replace("  ", " ").trim();
        ArrayList<Funcionario> funcionarios = repFuncionario.buscarFuncionarioAtivoPorCpf(cpf);
        if(funcionarios.size() == 0)
            throw new PessoaException("Nenhum funcionario foi encontrado com essas informações");
        return funcionarios;
    }
    
    @Override
    public ArrayList<Funcionario> getFuncionarios() {
        ArrayList<Funcionario> funcionarios = null;
        try{
            funcionarios = repFuncionario.getFuncionarios();  
        }catch(Exception e){}
        return funcionarios;
    }
    
    @Override
    public ArrayList<Funcionario> getFuncionariosAtivo() {
        ArrayList<Funcionario> funcionarios = null;
        try{
            funcionarios = repFuncionario.getFuncionariosAtivo();  
        }catch(Exception e){}
        return funcionarios;
    }
    
    @Override
    public boolean VerificarExisteAdministrador(){
        return repFuncionario.verificarExisteAdministrador();
    }
    
    @Override
    public void verificacaoCadastroLogin(String nomeUsuario, String senha) throws LoginException{
        if(nomeUsuario != null && nomeUsuario.length() < 5){
            throw new LoginException("O nome de usuario devi ter pelo menos 5 caracteres!");
        }
        if(senha != null && senha.length() < 5){
            throw new LoginException("A senha devi ter pelo menos 5 caracteres!");
        }  

        if(repFuncionario.buscarEmail(nomeUsuario) != null)
            throw new LoginException("Esse nome de usuario já existe!");
    }
 
    @Override
    public Funcionario buscarLogin(String nomeUsuario, String senha) throws LoginException{
        Funcionario funcionario = repFuncionario.buscarLogin(nomeUsuario, senha);
        if(funcionario != null)
            return funcionario;
        throw new LoginException("Esse login não existe");
    }
}
