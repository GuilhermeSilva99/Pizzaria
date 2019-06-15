/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.tela_vendas.tela_principal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class ControlerTelaVendas implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        telaHome();
    }    
    
    @FXML private Button butaoHome;
    @FXML private Button butaoRelatorio;
    @FXML private Button butaoEntregas;
    @FXML private StackPane painel;
    
    @FXML
    void telaHome(){
        butaoHome.setStyle("-fx-background-color: ffffff;");
        butaoRelatorio.setStyle("-fx-background-color: LightGray;");
        butaoEntregas.setStyle("-fx-background-color: LightGray;");
        
        painel.getChildren().clear();
        painel.getChildren().add(getNode("../tela_gerenciamento/FXMLTelaVendasGerenciamento.fxml"));
    }
    @FXML
    void telaEntregas(){
        butaoHome.setStyle("-fx-background-color: LightGray;");
        butaoRelatorio.setStyle("-fx-background-color: LightGray;");
        butaoEntregas.setStyle("-fx-background-color: ffffff;");
        
        painel.getChildren().clear();
        painel.getChildren().add(getNode("../tela_entregas/FXMLTelaVendasEntregas.fxml"));
    }
    @FXML
    void telaRelatorio(){
        butaoHome.setStyle("-fx-background-color: LightGray;");
        butaoRelatorio.setStyle("-fx-background-color: ffffff;");
        butaoEntregas.setStyle("-fx-background-color: LightGray;");
        
        painel.getChildren().clear();
        painel.getChildren().add(getNode("../tela_relatorio/FXMLTelaVendasRelatorio.fxml"));
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
