/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.tela_pizza.tela_gerenciamento;

import negocio.entidade.Pizza;
import negocio.exptions.PizzaException;
import static javafx.collections.FXCollections.observableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import negocio.fachada.Fachada;
import negocio.fachada.interfaces.IFachada;
import UI.tela_funcionario.tela_gerenciamento.TableViewFuncionario;


public class ControlerTelaPizzaGerenciamento implements Initializable {

    @FXML private TableView<TableViewPizza> tableViewPizza;
    @FXML private Button adicionar;
    @FXML private Button remover;
    @FXML private Button atualizar;
    @FXML private Label labelVerificacao;
    @FXML private Label labelNome;
    @FXML private Label labelIngredientes;
    @FXML private Label labelValor;
    @FXML private Label labelTipo;
    @FXML private Label labelDisponibilidade;
    @FXML private Label id;
    @FXML private TextField nome;
    @FXML private TextField valor;
    @FXML private ChoiceBox tipo;
    @FXML private ChoiceBox disponibilidade;
    @FXML private TextArea ingredientes;
    private TableViewPizza select;
    private IFachada fachada = Fachada.getFachada();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableViewPizza.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                select = (TableViewPizza) newValue;
                selectElement();
                verificacaoButaoAtivar();
                return;
            }
        });
        
        ArrayList tipoPizza = new ArrayList();
        tipoPizza.add("Salgada");
        tipoPizza.add("Doçe");
        tipoPizza.add("Vegetariana");
        tipoPizza.add("Fit");
        tipo.setItems(observableList(tipoPizza));
        tipo.setValue(tipo.getItems().get(0));
        
        ArrayList disponibilidadePizza = new ArrayList();
        disponibilidadePizza.add("Sim");
        disponibilidadePizza.add("Não");
        disponibilidade.setItems(observableList(disponibilidadePizza));
        disponibilidade.setValue(disponibilidade.getItems().get(0));
        
        listaPizzas();
        verificacaoButaoAtivar();
    }    
    
    private  void selectElement(){
        if(select != null){
            nome.setText(select.getNome());
            ingredientes.setText(select.getIngredientes());
            valor.setText(select.getValor());
            tipo.setValue(select.getTipo());
            disponibilidade.setValue(select.getDisponibilidade());
            id.setText(select.getId());
        } else {
            limpar();
        }
    }
    
    @FXML
    private void verificacaoButaoAtivar(){
        if(id.getText().equals("Id --------")){
            remover.setTextFill(Color.RED);
            atualizar.setTextFill(Color.RED);
            remover.setDisable(true);
            atualizar.setDisable(true);
            adicionar.setTextFill(Color.DARKTURQUOISE);
            adicionar.setDisable(false);
        } else{
            adicionar.setTextFill(Color.RED);
            adicionar.setDisable(true);
            remover.setTextFill(Color.DARKTURQUOISE);
            atualizar.setTextFill(Color.DARKTURQUOISE);
            remover.setDisable(false);
            atualizar.setDisable(false);
        }
    }
        
    @FXML
    private void limpar(){
        labelVerificacao.setStyle(null);
        labelNome.setTextFill(Color.WHITE);
        labelIngredientes.setTextFill(Color.WHITE);
        labelValor.setTextFill(Color.WHITE);
        labelTipo.setTextFill(Color.WHITE);
        labelDisponibilidade.setTextFill(Color.WHITE);
        limparCamposTexto();
        labelVerificacao.setText("");
        verificacaoButaoAtivar();
    }
    
    private boolean verificacaoCampoVazio(){
        boolean flag = true;
        labelVerificacao.setStyle(null);
        if(nome.getText().equals("")){
            flag = false;
            labelNome.setTextFill(Color.RED);
        }else{ labelNome.setTextFill(Color.WHITE);}
        if(ingredientes.getText().equals("")){
            flag = false;
            labelIngredientes.setTextFill(Color.RED);
        }else{ labelIngredientes.setTextFill(Color.WHITE);}
        if(valor.getText().equals("")){
            flag = false;
            labelValor.setTextFill(Color.RED);
        }else{ labelValor.setTextFill(Color.WHITE);}
        
        if(!flag){
            labelVerificacao.setText("Preencha todos os dados!");
            labelVerificacao.setTextFill(Color.RED);
        }else {labelVerificacao.setText(""); labelVerificacao.setTextFill(Color.WHITE);}
        return flag;
    }
    
    private void verificacaoDados(PizzaException e){
        if(e.getNome()){
            labelNome.setTextFill(Color.RED);
        }
        if(e.getIngredientes()){
            labelIngredientes.setTextFill(Color.RED);
        }
        if(e.getValor()){
            labelValor.setTextFill(Color.RED);
        } 
        labelVerificacao.setStyle(null);
        labelVerificacao.setText("Dados Inválidos!");
        labelVerificacao.setTextFill(Color.RED);
    }
    
    private void telaErro(String menssagem){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Pizza");
        alert.setContentText(menssagem);
        alert.show();
    }
    
    private void limparCamposTexto(){
        nome.setText("");
        ingredientes.setText("");
        valor.setText("");
        tipo.setValue(tipo.getItems().get(0));
        disponibilidade.setValue(disponibilidade.getItems().get(0));
        id.setText("Id --------");
        verificacaoButaoAtivar();
    }
        
    @FXML
    private void adicionar(){
        boolean resultado = verificacaoCampoVazio();
        if(resultado){
            try{
                fachada.adicionarPizza(nome.getText(), valor.getText(), ingredientes.getText(), disponibilidade.getValue().toString(), tipo.getValue().toString());
                listaPizzas();
                limparCamposTexto();
                labelVerificacao.setStyle("-fx-background-color: black;");
                labelVerificacao.setTextFill(Color.WHITE);
                labelVerificacao.setText("Pizza cadastrado com sucesso");
            }catch(PizzaException e){
                if(e.getMessage().equals("Dados Inválidos")){
                    verificacaoDados(e);
                    return;
                }
                telaErro(e.getMessage());
                labelVerificacao.setStyle(null);
            }
        }
    }
    
    @FXML
    private void remover(){
       if(select != null){
           fachada.removerPizza(Integer.parseInt(select.getId()));
           limparCamposTexto();
           listaPizzas();
           labelVerificacao.setStyle("-fx-background-color: black;");
           labelVerificacao.setTextFill(Color.WHITE);
           labelVerificacao.setText("Pizza Removido");
       } 
    }
    
    @FXML
    private void atualizar(){
        boolean resultado = verificacaoCampoVazio();
        if(resultado){
            try{
                fachada.editarPizza(Integer.parseInt(id.getText()), nome.getText(), valor.getText(), ingredientes.getText(), disponibilidade.getValue().toString(), tipo.getValue().toString());
                listaPizzas();
                limparCamposTexto();
                labelVerificacao.setStyle("-fx-background-color: black;");
                labelVerificacao.setTextFill(Color.WHITE);
                labelVerificacao.setText("Pizza atualizado");
            }catch(PizzaException e){
                if(e.getMessage().equals("Dados Inválidos")){
                    verificacaoDados(e);
                    return;
                }
                telaErro(e.getMessage());
                labelVerificacao.setStyle(null);
            }
        }
    }
        
    private void listaPizzas(){
        ArrayList<Pizza> pizzas = fachada.getPizzasAtiva();
        tableViewPizza.getItems().clear();
        for(Pizza pizza : pizzas){
            ObservableList<TableViewPizza> data = tableViewPizza.getItems();
            data.add(new TableViewPizza(Integer.toString(pizza.getId()), pizza.getNome(), pizza.getIngredientes(), 
            pizza.getValor(), pizza.getTipoPizza(), pizza.getDisponivel()
        ));
        }
    }    
}
