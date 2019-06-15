/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.tela_vendas.tela_relatorio;

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


public class ControlerTelaVendasRelatorio implements Initializable {

    @FXML private TableView<TableViewRelatorioComanda> tableViewRelatorioComanda;
    @FXML private TableView<TableViewComanda> tableViewComanda;
    @FXML private Label data;
    @FXML private Label quantidade;
    @FXML private Label valor;
    @FXML private Button busca;
    @FXML private Button reset;
    @FXML private DatePicker dataBusca;
    private IFachada fachada = Fachada.getFachada();
    private TableViewRelatorioComanda selectComanda;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableViewRelatorioComanda.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectComanda = (TableViewRelatorioComanda) newValue;
                comandas();
                return;
            }
        });
        listaRelatorioComanda();
    }    
    
    @FXML 
    public void reset(){
        listaRelatorioComanda();
        data.setText("");
        quantidade.setText("");
        valor.setText("");
        tableViewComanda.getItems().clear();
        dataBusca.setValue(null);
    }
    
    @FXML
    public void buscarRelatorio(){
        try{
            if(dataBusca.getValue() == null){
                listaRelatorioComanda();
                return;
            }
            RelatorioComanda comanda = fachada.getRelatorioComandaPorData(dataBusca.getValue().toString());
            tableViewRelatorioComanda.getItems().clear();
            ObservableList<TableViewRelatorioComanda> data = tableViewRelatorioComanda.getItems();
                data.add(new TableViewRelatorioComanda(Integer.toString(comanda.getId()), comanda.getNomeData(), comanda.getQuantidadeComandas(),
                         comanda.getValorTotal()
            ));
        }catch(ComandaException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Relatorio de Comanda");
            alert.setContentText(e.getMessage());
            alert.show();
        }  
    }
    
    @FXML
    public void comandas(){
        if(selectComanda != null){
            data.setText(selectComanda.getData());
            quantidade.setText(selectComanda.getQuantidade());
            valor.setText(selectComanda.getValor());
            listaComandas();
        }
    }
    
    @FXML
    private void listaComandas(){
        RelatorioComanda relatorio = null;
        try{
        relatorio = fachada.getRelatorioComandaPorData(selectComanda.getData());
        }catch(Exception e){}
        
        ArrayList<Comanda> comandas = null;
        if(relatorio != null)
            comandas = relatorio.getComandas();
        
        tableViewComanda.getItems().clear();
        if(comandas != null){
            for(Comanda comanda : comandas){
                ObservableList<TableViewComanda> data = tableViewComanda.getItems();
                data.add(new TableViewComanda(Integer.toString(comanda.getId()), comanda.getFormaPagamento(), comanda.getFuncionario(), comanda.getCliente(),
                        comanda.getPizzas(), comanda.getValorComanda(), comanda.getEntrega(), 
                        comanda.getCliente().getEndereco().getCidade() + "/" + comanda.getCliente().getEndereco().getRua()
                        + "/" + comanda.getCliente().getEndereco().getNumero()
            ));
            }
        }
    }
    
    @FXML
    private void listaRelatorioComanda(){
        ArrayList<RelatorioComanda> comandas = fachada.getRelatorioComanda();
        tableViewRelatorioComanda.getItems().clear();
        if(comandas != null){
            for(int i = comandas.size() - 1; i >= 0; i--){
                RelatorioComanda comanda = comandas.get(i);
                ObservableList<TableViewRelatorioComanda> data = tableViewRelatorioComanda.getItems();
                data.add(new TableViewRelatorioComanda(Integer.toString(comanda.getId()), comanda.getNomeData(), comanda.getQuantidadeComandas(),
                        comanda.getValorTotal()
            ));
            }
        }
    }
}
