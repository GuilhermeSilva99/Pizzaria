/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.tela_login;

import negocio.exptions.LoginException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import negocio.fachada.Fachada;
import negocio.fachada.interfaces.IFachada;
import UI.tela_principal.ExecutarTelaPrincipal;

public class ControlerTelaLogin implements Initializable {  
    private IFachada fachada = Fachada.getFachada();
    @FXML private Button login;
    @FXML private TextField email;
    @FXML private TextField senha;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void login() throws Exception{
        try{
            fachada.login(email.getText(), senha.getText());
            ExecutarTelaPrincipal janela = new ExecutarTelaPrincipal();
            janela.start(new Stage());
            ExecutarTelaLogin.getStage().close();
        }catch(LoginException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }    
}
