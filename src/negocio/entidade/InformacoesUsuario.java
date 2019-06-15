
package negocio.entidade;

public class InformacoesUsuario {
    private boolean administrador = false;
    private Funcionario funcionario;
    private String nome;
    private static InformacoesUsuario usuario;

    private InformacoesUsuario() {
    }

    public boolean getAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    
    public static InformacoesUsuario getInstance(){
        if(usuario == null){
            usuario =  new InformacoesUsuario();
        }
        return usuario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
