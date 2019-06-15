/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.tela_vendas.tela_gerenciamento;

import negocio.entidade.Cliente;
import negocio.entidade.Comanda;
import negocio.entidade.Funcionario;
import negocio.entidade.Pizza;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import negocio.entidade.InformacoesUsuario;
import negocio.entidade.RelatorioComanda;
import negocio.fachada.Fachada;
import negocio.fachada.interfaces.IFachada;
import sun.util.calendar.BaseCalendar;


public class ControlerTelaVendasGerenciamento implements Initializable {

    @FXML private TableView<TableViewComanda> tableViewComanda;
    @FXML private TableView<TableViewPizza> tableViewPizza;
    @FXML private Label labelVerificacao;
    @FXML private Label labelFuncionario;
    @FXML private Label labelPizzas;
    @FXML private Label labelValor;
    @FXML private Label labelCliente;
    @FXML private Label labelEntrega;
    @FXML private Label labelCidade;
    @FXML private Label labelRua;
    @FXML private Label labelNumero;
    @FXML private ChoiceBox entrega;
    @FXML private TextField cliente;
    @FXML private TextField cidade;
    @FXML private TextField rua;
    @FXML private TextField numero;
    @FXML private Label id;
    @FXML private Button adicionar;
    @FXML private Button remover;
    @FXML private Button limpar;
    @FXML private Button buscaPizza;
    @FXML private Button adicionarPizza;
    @FXML private ChoiceBox pagamento;
    @FXML private ChoiceBox funcionarios;
    @FXML private ChoiceBox pizzas;
    @FXML private TextField textoPizza;
    private IFachada fachada = Fachada.getFachada();
    private ArrayList pizzasComanda = new ArrayList();
    private TableViewPizza selectPizza;
    private TableViewComanda selectComanda;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tableViewPizza.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectPizza = (TableViewPizza) newValue;
                verificacaoButaoAtivar();
                return;
            }
        });
        
        tableViewComanda.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectComanda = (TableViewComanda) newValue;
                selectElementComanda();
                verificacaoButaoAtivar();
                return;
            }
        });
        
        entrega.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String resultado = newValue.toString();
                if(resultado.equals("Sim")){
                    cidade.setDisable(false);
                    rua.setDisable(false);
                    numero.setDisable(false);
                }
                else{
                    cidade.setText("");
                    numero.setText("");
                    rua.setText("");
                    cidade.setDisable(true);
                    rua.setDisable(true);
                    numero.setDisable(true);
                }
                return;
            }
        });
        
        ArrayList entreg = new ArrayList();
        entreg.add("Não");
        entreg.add("Sim"); 
        entrega.setItems(observableList(entreg));
        entrega.setValue(entrega.getItems().get(0));
        
        ArrayList pag = new ArrayList();
        pag.add("À Vista");
        pag.add("Cartão de Crédito");
        pag.add("Cheque");
        pagamento.setItems(observableList(pag));
        pagamento.setValue(pagamento.getItems().get(0));
        
        ArrayList<Funcionario> funcionario = fachada.listaFuncionariosComanda(); 
        ArrayList auxFuncionarios = new ArrayList();
        int indiceSelecionado = 0;
        for(int i = 0; i < funcionario.size(); i++){
            if(funcionario.get(i).getId() == InformacoesUsuario.getInstance().getFuncionario().getId())
                indiceSelecionado = i;
            auxFuncionarios.add(funcionario.get(i).getId() + "-" + funcionario.get(i).getNome());
        }
        funcionarios.setItems(observableList(auxFuncionarios));
        if(auxFuncionarios.size() > 0){
            funcionarios.setValue(this.funcionarios.getItems().get(indiceSelecionado));
        }
        
        ArrayList<Pizza> pizzas = fachada.getPizzasAtiva();
        tableViewPizza.getItems().clear();
        for(Pizza pizza : pizzas){
            if(pizza.getDisponivel()){
                ObservableList<TableViewPizza> data = tableViewPizza.getItems();
                    data.add(new TableViewPizza(Integer.toString(pizza.getId()), pizza.getNome(), pizza.getIngredientes(),
                            pizza.getValor()
                ));
            } 
        }
        
        verificacaoButaoAtivar();
        listaComandas();
    }  
    
    private void selectElementComanda(){
        if(selectComanda != null){
            pagamento.setValue(selectComanda.getPagamento());
            funcionarios.setValue(selectComanda.getFuncionario());
            labelValor.setText(selectComanda.getValor());
            ArrayList<Pizza> pizzas = selectComanda.getPizzasArray(); 
            ArrayList auxPizzas = new ArrayList();
            for(int i = 0; i < pizzas.size(); i++){
                auxPizzas.add(pizzas.get(i).getNome());
            }
            this.pizzas.setItems(observableList(auxPizzas));
            this.pizzas.setValue(this.pizzas.getItems().get(0));
            id.setText(selectComanda.getId());
        } else {
            
        }
    }
    
    @FXML
    private void verificacaoButaoAtivar(){
        if(selectPizza != null){
            adicionarPizza.setDisable(false);
            adicionarPizza.setTextFill(Color.DARKTURQUOISE);
        }
        else{
            adicionarPizza.setDisable(true);
            adicionarPizza.setTextFill(Color.RED);
        }
        if(id.getText().equals("Id --------")){
            remover.setTextFill(Color.RED);
            remover.setDisable(true);
            adicionar.setTextFill(Color.DARKTURQUOISE);
            adicionar.setDisable(false);
        } else{
            adicionar.setTextFill(Color.RED);
            adicionar.setDisable(true);
            remover.setTextFill(Color.DARKTURQUOISE);
            remover.setDisable(false);
        }
    }
    
    @FXML
    void adicionarPizza(){
        if(selectPizza != null){
            pizzasComanda.add(selectPizza.getId());
            pizzas.getItems().add(selectPizza.getNome());
            pizzas.setItems(pizzas.getItems());
            pizzas.setValue(pizzas.getItems().get(0));
            double valor = 0;
            if(!labelValor.getText().equals("")){
                valor = Double.parseDouble(labelValor.getText());
            }
            valor += Double.parseDouble(selectPizza.getValor());
            labelValor.setText(String.valueOf(valor));
        }
    }
    
    private boolean verificacaoCampoVazio(){
        boolean flag = true;
        labelVerificacao.setStyle(null);
        if(pizzasComanda.size() == 0){
            flag = false;
            labelPizzas.setTextFill(Color.RED);
        }else{ labelPizzas.setTextFill(Color.WHITE);}
        if(funcionarios.getItems().size() == 0){
            flag = false;
            labelFuncionario.setTextFill(Color.RED);
        }else{ labelFuncionario.setTextFill(Color.WHITE);}
        
        if(!flag){
            labelVerificacao.setText("Dados Insuficiente!");
            labelVerificacao.setTextFill(Color.RED);
        }else {labelVerificacao.setText(""); labelVerificacao.setTextFill(Color.WHITE);}
        return flag;
    }
     
    @FXML
    void limpar(){
        pagamento.setValue(pagamento.getItems().get(0));
        if(funcionarios.getItems().size() > 0){
            funcionarios.setValue(this.funcionarios.getItems().get(0));
        }
        labelValor.setText("");
        ArrayList auxPizzas = new ArrayList();
        this.pizzas.setItems(observableList(auxPizzas));
        id.setText("Id --------");
        labelPizzas.setTextFill(Color.WHITE);
        labelPizzas.setTextFill(Color.WHITE);
        labelVerificacao.setText(""); 
        labelVerificacao.setTextFill(Color.WHITE);
        labelCidade.setTextFill(Color.WHITE);
        labelRua.setTextFill(Color.WHITE);
        labelNumero.setTextFill(Color.WHITE);
        labelCliente.setTextFill(Color.WHITE);
        cidade.setText("");
        numero.setText("");
        rua.setText("");
        cliente.setText("");
        pizzasComanda.clear();
        verificacaoButaoAtivar();
    }
    
    @FXML
    void adicionar(){
        boolean result = verificacaoCampoVazio();
        if(result){
            String c = cliente.getText();
            if(c.equals(""))
                c = "Padão";
            String end = cidade.getText() + rua.getText() + numero.getText();
            if(end.trim().equals(""))
                end = "Pizzaria";
            else
                end = cidade.getText();
            Cliente client = new Cliente(cliente.getText(), end, rua.getText(), numero.getText());
            Calendar data = Calendar.getInstance();
            String hora = data.get(Calendar.HOUR_OF_DAY) + ""; 
            if(hora.length() == 1)
                hora = "0" + hora;
            String min = data.get(Calendar.MINUTE) + "";
            if(min.length() == 1)
                min = "0" + min;
            String sec = data.get(Calendar.SECOND) + "";
            if(sec.length() == 1)
                sec = "0" + sec;
            fachada.adicionarComanda(LocalDate.now().toString(), (hora+":"+min+":"+sec), pagamento.getValue().toString(), funcionarios.getValue().toString(), pizzasComanda, client, 
                    Double.parseDouble(labelValor.getText()), entrega.getValue().toString());
            limpar();
            listaComandas();
        }
    }
    
    @FXML
    void remover(){
        fachada.deletarComanda(selectComanda.getData() + selectComanda.getHorario());
        //fachada.removerComanda(Integer.parseInt(id.getText()));
        limpar();
        listaComandas();
        verificacaoButaoAtivar();
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

        tableViewComanda.getItems().clear();
        if(comandas != null){
            for(Comanda comanda : comandas){
                ObservableList<TableViewComanda> data = tableViewComanda.getItems();
                data.add(new TableViewComanda(Integer.toString(comanda.getId()), comanda.getDataCompra(), 
                        comanda.getHorario(), comanda.getFormaPagamento(), comanda.getFuncionario(), comanda.getCliente(),
                        comanda.getPizzas(), comanda.getValorComanda(), "", comanda.getEntrega()
            ));
            }
        }
    }
}
