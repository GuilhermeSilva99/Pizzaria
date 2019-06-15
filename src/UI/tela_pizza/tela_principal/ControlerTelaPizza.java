/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.tela_pizza.tela_principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class ControlerTelaPizza implements Initializable {

    @FXML private Button butaoHome;
    @FXML private StackPane painel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        telaHome();
    }    
    
    @FXML
    void telaHome(){
        butaoHome.setStyle("-fx-background-color: ffffff;");
        
        painel.getChildren().clear();
        painel.getChildren().add(getNode("../tela_gerenciamento/FXMLTelaPizzaGerenciamento.fxml"));
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
