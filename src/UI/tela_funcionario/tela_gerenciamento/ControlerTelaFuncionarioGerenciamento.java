/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.tela_funcionario.tela_gerenciamento;

import negocio.entidade.Cargo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import negocio.entidade.Endereco;
import negocio.entidade.Funcionario;
import negocio.exptions.CargoException;
import negocio.exptions.LoginException;
import negocio.exptions.PessoaException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import negocio.fachada.Fachada;
import negocio.fachada.interfaces.IFachada;


public class ControlerTelaFuncionarioGerenciamento implements Initializable {

    @FXML private TableView<TableViewFuncionario> tableViewFuncionario;
    @FXML private Button adicionar;
    @FXML private Button remover;
    @FXML private Button atualizar;
    @FXML private StackPane painel;
    @FXML private Label labelVerificacao;
    @FXML private Label labelNome;
    @FXML private Label labelCpf;
    @FXML private Label labelCidade;
    @FXML private Label labelRua;
    @FXML private Label labelNumero;
    @FXML private Label id;
    @FXML private Label labelEmail;
    @FXML private Label labelSenha;
    @FXML private Label labelStatus;
    @FXML private Label labelData;
    @FXML private Label labelCargo;
    @FXML private TextField nome;
    @FXML private TextField cpf;
    @FXML private TextField cidade;
    @FXML private TextField rua;
    @FXML private TextField numero;
    @FXML private TextField email;
    @FXML private TextField senha;
    @FXML private ChoiceBox cargo;
    @FXML private ChoiceBox status;
    @FXML private DatePicker data;
    private TableViewFuncionario select;
    private IFachada fachada = Fachada.getFachada();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableViewFuncionario.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                select = (TableViewFuncionario) newValue;
                selectElement();
                verificacaoButaoAtivar();
                return;
            }
        });
        
        ArrayList stat = new ArrayList();
        stat.add("Ativo");
        stat.add("Férias");
        stat.add("Licença Médica");
        status.setItems(observableList(stat));
        status.setValue(status.getItems().get(0));
        
        ArrayList<Cargo> cargos = fachada.getCargosAtivo(); 
        ArrayList auxCargo = new ArrayList();
        for(int i = 0; i < cargos.size(); i++){
            if(!cargos.get(i).getNome().equals("administrador"))
                auxCargo.add(cargos.get(i).getNome());
        }
        cargo.setItems(observableList(auxCargo));
        if(auxCargo.size() > 0){
            cargo.setValue(cargo.getItems().get(0));
        }
        
        data.setValue(LocalDate.now());
        
        listaFuncionarios();
        verificacaoButaoAtivar();
    }

    private void selectElement(){
        if(select != null){
            String[] data2 = select.getData().split("-");
            data.setValue(LocalDate.of(Integer.parseInt(data2[0]), Integer.parseInt(data2[1]), Integer.parseInt(data2[2])));
            status.setValue(select.getStatus());
            cargo.setValue(select.getCargo());
            nome.setText(select.getNome());
            cpf.setText(select.getCpf());
            cidade.setText(select.getCidade());
            rua.setText(select.getRua());
            numero.setText(select.getNumero());
            email.setText(select.getEmail());
            senha.setText(select.getSenha());
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
        labelCpf.setTextFill(Color.WHITE);
        labelCidade.setTextFill(Color.WHITE);
        labelRua.setTextFill(Color.WHITE);
        labelNumero.setTextFill(Color.WHITE);
        labelCargo.setTextFill(Color.WHITE);
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
        if(cargo.getItems().size() == 0){
            flag = false;
            labelCargo.setTextFill(Color.RED);
        }else{ labelCargo.setTextFill(Color.WHITE);}
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
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Funcionario");
        alert.setContentText(menssagem);
        alert.show();
    }
    
    private void limparCamposTexto(){
        status.setValue(status.getItems().get(0));
        if(cargo.getItems().size() > 0){
            cargo.setValue(cargo.getItems().get(0));
        }
        data.setValue(LocalDate.now());
        email.setText("");
        senha.setText("");
        nome.setText("");
        cpf.setText("");
        cidade.setText("");
        rua.setText("");
        numero.setText("");
        id.setText("Id --------");
        verificacaoButaoAtivar();
    }
        
    @FXML
    private void adicionar(){
        boolean resultado = verificacaoCampoVazio();
        if(resultado){
            try{
                Endereco end = new Endereco(cidade.getText(), rua.getText(),numero.getText());
                String c = cargo.getValue().toString();
                fachada.adicionarFuncionario(end, email.getText(), senha.getText(), status.getValue().toString(),
                fachada.buscarCargoAtivoPorNome(cargo.getValue().toString()), data.getValue().toString(), nome.getText(), cpf.getText());
                listaFuncionarios();
                limparCamposTexto();
                labelVerificacao.setStyle("-fx-background-color: black;");
                labelVerificacao.setTextFill(Color.WHITE);
                labelVerificacao.setText("Funcionario cadastrado com sucesso");
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
            catch(CargoException e){
                telaErro(e.getMessage());
                labelVerificacao.setStyle(null);
            }
        }
    }
    
    @FXML
    private void remover(){
       if(select != null){
           fachada.removerFuncionario(Integer.parseInt(select.getId()));
           limparCamposTexto();
           listaFuncionarios();
           labelVerificacao.setStyle("-fx-background-color: black;");
           labelVerificacao.setTextFill(Color.WHITE);
           labelVerificacao.setText("Funcionario Removido");
       }  
    }
    
    @FXML
    private void atualizar(){
        boolean resultado = verificacaoCampoVazio();
        if(resultado){
            try{
                Endereco end = new Endereco(cidade.getText(), rua.getText(),numero.getText());
                fachada.editarFuncionario(Integer.parseInt(id.getText()),cidade.getText(), rua.getText(),numero.getText(),
                email.getText(), senha.getText(), status.getValue().toString(),
                fachada.buscarCargoAtivoPorNome(cargo.getValue().toString()), data.getValue().toString(), nome.getText(), cpf.getText());
                listaFuncionarios();
                limparCamposTexto();
                labelVerificacao.setStyle("-fx-background-color: black;");
                labelVerificacao.setTextFill(Color.WHITE);
                labelVerificacao.setText("Funcionario Atualizado");
                //ControlerTelaPrincipal.getTelaPrincipal().telaFuncionario();
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
            catch(CargoException e){
                telaErro(e.getMessage());
                labelVerificacao.setStyle(null);
            }
        }
    }
    
    @FXML
    private void listaFuncionarios(){
        ArrayList<Funcionario> funcionarios = fachada.getFuncionariosAtivo();
        tableViewFuncionario.getItems().clear();
        for(Funcionario funcionario : funcionarios){
            if(!funcionario.getCargo().getNome().toLowerCase().trim().equals("administrador")){
                ObservableList<TableViewFuncionario> data = tableViewFuncionario.getItems();
                data.add(new TableViewFuncionario(Integer.toString(funcionario.getId()), funcionario.getNome(), 
                funcionario.getCpf(), funcionario.getCargo().getNome(), funcionario.getDataAdimissao(), funcionario.getStatus(),
                funcionario.getNomeUsuario(), funcionario.getSenha(), funcionario.getEndereco().getCidade(),
                funcionario.getEndereco().getRua(), funcionario.getEndereco().getNumero()
                ));
            }
        }
    }
}
