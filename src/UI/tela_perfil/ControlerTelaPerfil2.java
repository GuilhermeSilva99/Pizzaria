/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.tela_perfil;

import UI.tela_principal.ControlerTelaPrincipal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import negocio.entidade.Endereco;
import negocio.entidade.Funcionario;
import negocio.entidade.InformacoesUsuario;
import negocio.exptions.LoginException;
import negocio.exptions.PessoaException;
import negocio.fachada.Fachada;
import negocio.fachada.interfaces.IFachada;

/**
 * FXML Controller class
 *
 * @author m1147846
 */
public class ControlerTelaPerfil2 implements Initializable {
    
    private IFachada fachada = Fachada.getFachada();

    @FXML private Button atualizar;
    @FXML private TextField nome;
    @FXML private TextField email;
    @FXML private TextField senha;
    @FXML private TextField cpf;
    @FXML private TextField cidade;
    @FXML private TextField rua;
    @FXML private TextField numero;
    @FXML private Label labelVerificacao;
    @FXML private Label labelNome;
    @FXML private Label labelEmail;
    @FXML private Label labelSenha;
    @FXML private Label labelCpf;
    @FXML private Label labelCidade;
    @FXML private Label labelRua;
    @FXML private Label labelNumero;
    private Funcionario funcionario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        funcionario = InformacoesUsuario.getInstance().getFuncionario();
        nome.setText(funcionario.getNome());
        email.setText(funcionario.getNomeUsuario());
        senha.setText(funcionario.getSenha());
        cidade.setText(funcionario.getEndereco().getCidade());
        cpf.setText(funcionario.getCpf());
        rua.setText(funcionario.getEndereco().getRua());
        numero.setText(funcionario.getEndereco().getNumero());
    } 
    
    private boolean verificacaoCampoVazio(){
        boolean flag = true;
        labelVerificacao.setStyle(null);
        if(nome.getText().equals("")){
            flag = false;
            labelNome.setTextFill(Color.RED);
        }else{ labelNome.setTextFill(Color.WHITE);}
        if(email.getText().equals("")){
            flag = false;
            labelEmail.setTextFill(Color.RED);
        }else{ labelEmail.setTextFill(Color.WHITE);}
        if(senha.getText().equals("")){
            flag = false;
            labelSenha.setTextFill(Color.RED);
        }else{ labelSenha.setTextFill(Color.WHITE);}
        if(cpf.getText().equals("")){
            flag = false;
            labelCpf.setTextFill(Color.RED);
        }else{ labelCpf.setTextFill(Color.WHITE);}
        if(cidade.getText().equals("")){
            flag = false;
            labelCidade.setTextFill(Color.RED);
        }else{ labelCidade.setTextFill(Color.WHITE);}
        if(rua.getText().equals("")){
            flag = false;
            labelRua.setTextFill(Color.RED);
        }else{ labelRua.setTextFill(Color.WHITE);}
        if(numero.getText().equals("")){
            flag = false;
            labelNumero.setTextFill(Color.RED);
        }else{ labelNumero.setTextFill(Color.WHITE);}

        if(!flag){
            labelVerificacao.setText("Preencha todos os dados!");
            labelVerificacao.setTextFill(Color.RED);
        }else {labelVerificacao.setText(""); labelVerificacao.setTextFill(Color.WHITE);}
        
        return flag;
    }
    
    private void verificacaoDados(PessoaException e){
        if(e.getNome()){
            labelNome.setTextFill(Color.RED);
        }
        if(e.getCpf()){
            labelCpf.setTextFill(Color.RED);
        }
        if(e.getCidade()){
            labelCidade.setTextFill(Color.RED);
        }
        if(e.getRua()){
            labelRua.setTextFill(Color.RED);
        }
        if(e.getNumero()){
            labelNumero.setTextFill(Color.RED);
        }
  
        labelVerificacao.setStyle(null);
        labelVerificacao.setText("Dados Inválidos!");
        labelVerificacao.setTextFill(Color.RED);
    }
    
    private void telaErro(String menssagem){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Perfil");
        alert.setContentText(menssagem);
        alert.show();
    }
    
    @FXML
    public void atualizar(){
        if(verificacaoCampoVazio()){
            try{
                fachada.editarFuncionario(funcionario.getId(),cidade.getText(), rua.getText(),numero.getText(),
                email.getText(), senha.getText(), funcionario.getStatus(), funcionario.getCargo(), 
                
                funcionario.getDataAdimissao(), nome.getText(), cpf.getText());
                InformacoesUsuario.getInstance().setNome(nome.getText());
                funcionario.setNome(nome.getText());
                funcionario.setCpf(cpf.getText());
                funcionario.setLoginNomeUsuario(email.getText());
                funcionario.setLoginSenha(senha.getText());
                Endereco end = new Endereco(cidade.getText(), rua.getText(), numero.getText());
                funcionario.setEndereco(end);
                InformacoesUsuario.getInstance().setFuncionario(funcionario);
                
                ControlerTelaPrincipal.getTelaPrincipal().setarNomeUsuario();
                labelVerificacao.setStyle("-fx-background-color: black;");
                labelVerificacao.setTextFill(Color.WHITE);
                labelVerificacao.setText("Perfil Atualizado");
                
            }catch(PessoaException e){
                if(e.getMessage().equals("Dados Inválidos")){
                    verificacaoDados(e);
                    return;
                }
                telaErro(e.getMessage());
                labelVerificacao.setStyle(null);
                
            } catch(LoginException e){
                telaErro(e.getMessage());
                labelVerificacao.setStyle(null);
            }
        }
    }
}
