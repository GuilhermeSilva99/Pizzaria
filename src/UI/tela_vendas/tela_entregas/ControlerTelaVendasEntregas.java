/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.tela_vendas.tela_entregas;

import UI.tela_vendas.tela_relatorio.*;
import negocio.entidade.Comanda;
import negocio.entidade.RelatorioComanda;
import negocio.exptions.ComandaException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import negocio.fachada.Fachada;
import negocio.fachada.interfaces.IFachada;
import UI.tela_vendas.tela_gerenciamento.TableViewComanda;
import negocio.entidade.Cliente;


public class ControlerTelaVendasEntregas implements Initializable {

    @FXML private TableView<TableViewComanda> tableViewComanda;
    @FXML private Label id;
    @FXML private Button botaoEntrega;
    private TableViewComanda selectComanda;
    private IFachada fachada = Fachada.getFachada();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableViewComanda.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectComanda = (TableViewComanda) newValue;
                id.setText(selectComanda.getId());
                return;
            }
        });
        listaComandas();
    }    
    
    @FXML
    private void eventoEntrega(){
        if(selectComanda != null){
            fachada.mudarStatusComanda(selectComanda.getData() + selectComanda.getHorario(), "Entregue");
            listaComandas();
            id.setText("Id --------");
            selectComanda = null;
        }
    }
    
    @FXML
    private void listaComandas(){
        RelatorioComanda relatorio = null;
        try{
        relatorio = fachada.getRelatorioComandaDeHoje();
        }catch(Exception e){}
        
        ArrayList<Comanda> comandas = null;
        if(relatorio != null)
            comandas = relatorio.getComandas();

        try{tableViewComanda.getItems().clear();}catch(Exception e){}
        if(comandas != null){
            for(Comanda comanda : comandas){
                if(comanda.getEntrega().equals("Sim")){
                    ObservableList<TableViewComanda> data = tableViewComanda.getItems();
                    data.add(new TableViewComanda(Integer.toString(comanda.getId()), comanda.getDataCompra(), comanda.getHorario(), comanda.getFormaPagamento(),
                            comanda.getFuncionario(), comanda.getCliente(), comanda.getPizzas(), comanda.getValorComanda(),
                            comanda.getCliente().getEndereco().getCidade() + "/" + comanda.getCliente().getEndereco().getRua()
                            + "/" + comanda.getCliente().getEndereco().getNumero(), ""
                    ));
                }
            }
        }
    }
}
