/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.tela_cargo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import negocio.entidade.Cargo;
import negocio.exptions.CargoException;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import negocio.entidade.InformacoesUsuario;
import negocio.fachada.Fachada;
import negocio.fachada.interfaces.IFachada;


public class ControlerTelaCargo implements Initializable {

    @FXML private TableView<TableViewCargo> tableViewCargo;
    @FXML private Button adicionar;
    @FXML private Button remover;
    @FXML private Button atualizar;
    @FXML private Label labelVerificacao;
    @FXML private Label labelNome;
    @FXML private Label labelDescricao;
    @FXML private Label labelSalario;
    @FXML private Label labelGerencia;
    @FXML private Label id;
    @FXML private TextField nome;
    @FXML private TextField descricao;
    @FXML private TextField salario;
    private TableViewCargo select;
    private IFachada fachada = Fachada.getFachada();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tableViewCargo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                select = (TableViewCargo) newValue;
                selectElement();
                verificacaoButaoAtivar();
                return;
            }
        });
        listaCargos();
        verificacaoButaoAtivar();
    }
    
    private  void selectElement(){
        if(select != null){
            nome.setText(select.getNome());
            descricao.setText(select.getDescricao());
            salario.setText(select.getSalario());
            id.setText(select.getId());
        } else {
            nome.setText("");
            descricao.setText("");
            salario.setText("");
            id.setText("Id --------");
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
        labelDescricao.setTextFill(Color.WHITE);
        labelSalario.setTextFill(Color.WHITE);
        labelGerencia.setTextFill(Color.WHITE);
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
        if(descricao.getText().equals("")){
            flag = false;
            labelDescricao.setTextFill(Color.RED);
        }else{ labelDescricao.setTextFill(Color.WHITE);}
        if(labelSalario.getText().equals("")){
            flag = false;
            labelSalario.setTextFill(Color.RED);
        }else{ labelSalario.setTextFill(Color.WHITE);}
        if(!flag){
            labelVerificacao.setText("Preencha todos os dados!");
            labelVerificacao.setTextFill(Color.RED);
        }else {labelVerificacao.setText(""); labelVerificacao.setTextFill(Color.WHITE);}
        return flag;
    }
    
    private void verificacaoDados(CargoException e){
        if(e.getNome()){
            labelNome.setTextFill(Color.RED);
        }
        if(e.getDescricao()){
            labelDescricao.setTextFill(Color.RED);
        }
        if(e.getSalario()){
            labelSalario.setTextFill(Color.RED);
        } 
        labelVerificacao.setStyle(null);
        labelVerificacao.setText("Dados Inválidos!");
        labelVerificacao.setTextFill(Color.RED);
    }
    
    private void telaErro(String menssagem){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Cargo");
        alert.setContentText(menssagem);
        alert.show();
    }
    
    private void limparCamposTexto(){
        nome.setText("");
        descricao.setText("");
        salario.setText("");
        id.setText("Id --------");
        verificacaoButaoAtivar();
    }
        
    @FXML
    private void adicionar(){
        boolean resultado = verificacaoCampoVazio();
        if(resultado){
            try{
                if(nome.getText().toLowerCase().trim().equals("administrador")){
                    telaErro("O sistema tem apenas 1 administrador!");
                }
                else if(!fachada.usuarioLogado().equals("administrador") && 
                        nome.getText().toLowerCase().trim().equals("gerente"))
                    telaErro("Só o administrador pode cadastrar gerentes!");
                else{
                    fachada.adicionarCargo(nome.getText(), descricao.getText(), salario.getText());
                    listaCargos();
                    limparCamposTexto();
                    labelVerificacao.setStyle("-fx-background-color: black;");
                    labelVerificacao.setTextFill(Color.WHITE);
                    labelVerificacao.setText("Cargo cadastrado com sucesso");
                }
            }catch(CargoException e){
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
           try{
            fachada.removerCargo(Integer.parseInt(select.getId()));
            limparCamposTexto();
            listaCargos();
            labelVerificacao.setStyle("-fx-background-color: black;");
            labelVerificacao.setTextFill(Color.WHITE);
            labelVerificacao.setText("Cargo Removido");
           }
           catch(CargoException e){
                telaErro(e.getMessage());
                labelVerificacao.setStyle(null);
            }
       } 
    }
    
    @FXML
    private void atualizar(){
        boolean resultado = verificacaoCampoVazio();
        if(resultado){
            try{
                if(nome.getText().toLowerCase().trim().equals("administrador")){
                    telaErro("O sistema tem apenas 1 administrador!");
                }
                else if(!fachada.usuarioLogado().equals("administrador") && 
                        nome.getText().toLowerCase().trim().equals("gerente"))
                    telaErro("Só o administrador pode cadastrar gerentes!");
                else{
                    fachada.editarCargo(Integer.parseInt(id.getText()),nome.getText(), descricao.getText(), salario.getText());
                    listaCargos();
                    limparCamposTexto();
                    labelVerificacao.setStyle("-fx-background-color: black;");
                    labelVerificacao.setTextFill(Color.WHITE);
                    labelVerificacao.setText("Cargo atualizado");
                }
            }catch(CargoException e){
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
    private void listaCargos(){
        ArrayList<Cargo> cargos = fachada.getCargosAtivo();
        tableViewCargo.getItems().clear();
        for(Cargo carg : cargos){
            if(!carg.getNome().toLowerCase().trim().equals("administrador")){
                ObservableList<TableViewCargo> data = tableViewCargo.getItems();
                data.add(new TableViewCargo(Integer.toString(carg.getId()), carg.getNome(), carg.getDescricao(), 
                carg.getSalarioBase()
                ));
            }
        }
    }    
    
}
