/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.tela_principal;

import UI.tela_perfil.ExecutarTelaPerfil2;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import negocio.entidade.InformacoesUsuario;
import negocio.exptions.LoginException;
import negocio.fachada.Fachada;
import negocio.fachada.interfaces.IFachada;

/**
 * FXML Controller class
 *
 * @author m1147846
 */
public class ControlerTelaPrincipal implements Initializable {
    
    @FXML private Button vendas;
    @FXML private Button pizza;
    @FXML private Button funcionario;
    @FXML private Button cargo;
    @FXML private StackPane stack;
    @FXML private Label labelNome;
    @FXML private Button perfil;
    private IFachada fachada = Fachada.getFachada();
    private static ControlerTelaPrincipal telaPrincipal;
    
    public static ControlerTelaPrincipal getTelaPrincipal(){
        return telaPrincipal;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(!fachada.usuarioLogado().equals("administrador") && 
           !fachada.usuarioLogado().equals("gerente")){
            cargo.setVisible(false);
            funcionario.setVisible(false);
        }
        //if(!fachada.usuarioLogado().equals("administrador"))
        //perfil.setVisible(false);
        
        setarNomeUsuario();
        telaInicial();
        
        if(telaPrincipal == null)
            telaPrincipal = this;
    }  
    
    public void setarNomeUsuario(){
        String nome = fachada.nomeUsuarioLogado();
        labelNome.setText("Bem Vindo " + nome.substring(0,1).toUpperCase().concat(nome.substring(1))  +  " ............................................");
    }
    
    @FXML
    private void telaPerfil(){
        try{
            ExecutarTelaPerfil2 janela = new ExecutarTelaPerfil2();
            janela.start(new Stage());
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Perfil");
            alert.setContentText("Infelizmente ocorreu um erro, tente novamente em alguns instantes.");
            alert.show();
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @FXML
    void telaPizza(){
        vendas.setStyle("-fx-background-color: LightGray;");
        pizza.setStyle("-fx-background-color:  ffffff;");
        funcionario.setStyle("-fx-background-color:  LightGray;");
        cargo.setStyle("-fx-background-color:  LightGray;");
        
        stack.getChildren().clear();
        stack.getChildren().add(getNode("../tela_pizza/tela_principal/FXMLTelaPizza.fxml"));
    }

    @FXML
    public void telaFuncionario(){
        vendas.setStyle("-fx-background-color: LightGray;");
        pizza.setStyle("-fx-background-color:  LightGray;");
        funcionario.setStyle("-fx-background-color:  ffffff;");
        cargo.setStyle("-fx-background-color:  LightGray;");
        
        stack.getChildren().clear();
        stack.getChildren().add(getNode("../tela_funcionario/tela_principal/FXMLTelaFuncionario.fxml"));
    }
    @FXML
    void telaCargo(){
        vendas.setStyle("-fx-background-color: LightGray;");
        pizza.setStyle("-fx-background-color:  LightGray;");
        funcionario.setStyle("-fx-background-color:  LightGray;");
        cargo.setStyle("-fx-background-color:  ffffff;");
        
        stack.getChildren().clear();
        stack.getChildren().add(getNode("../tela_cargo/FXMLTelaCargo.fxml"));
    }
    @FXML
    void telaInicial(){
        vendas.setStyle("-fx-background-color: ffffff;");
        pizza.setStyle("-fx-background-color:  LightGray;");
        funcionario.setStyle("-fx-background-color:  LightGray;");
        cargo.setStyle("-fx-background-color:  LightGray;");
        
        stack.getChildren().clear();
        stack.getChildren().add(getNode("../tela_vendas/tela_principal/FXMLTelaVendas.fxml"));
    }
    
    public Node getNode(String node){
        Node no = null;
        try {
            no = FXMLLoader.load(getClass().getResource(node));
        } catch (Exception e) {
        }
        return no;
        
    }
    
}
