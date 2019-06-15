package main;

import UI.tela_login.ExecutarTelaLogin;
import negocio.entidade.Funcionario;

/**
 * Nós fizemos as classes testes e montamos um menu no Main que servirá para chamar cada claase teste com seus métodos.
 * Só é executa para testar...
 * @author Felipe, Alex
 */
public class Main {
    public static String[] args2;
    
    public static void main(String[] args) {   
        Funcionario.verificacaoAdm();
        ExecutarTelaLogin.main(args);
       //NewFXMainTeste2.main(args);
    }   
    
    
    public static void main2(){
        /*
        Administrador.verificacaoAdm();
        String sair = "1";
        Scanner teclado = new Scanner(System.in);
        while (!sair.equals("0")) {
            System.out.println("------- Sistema de Pizzaria -------\n");
            System.out.println("Digite 1 para Logar");
            System.out.println("Digite 0 para Sair");
            System.out.print("Digite sua opção: ");
            sair = teclado.nextLine();
            if (sair.equals("1")) {
                try{
                    int resultado = TesteLogin.altenticarLogin();
                    if (resultado == 0) {
                        System.out.println("\n---- Bem vindo " + InformacoesUsuario.getInstance().getNome() + " --------\n");
                        String sair2 = "1";
                        System.out.println("----------- Menu Principal -------------");
                        while (!sair2.equals("0")) {
                            if (InformacoesUsuario.getInstance().getFuncionario().getCargo().getGerencia()) {
                                System.out.println("Digite 1 para ir pro Gerenciamento de Funcionario");
                                System.out.println("Digite 3 para ir pro Gerenciamento de Cargos");
                            }
                            System.out.println("Digite 2 para ir pro Gerenciamento de Clientes");
                            System.out.println("Digite 4 para ir para o Gerenciamento de Pizzas ");
                            System.out.println("Digite 5 para ir para o Gerenciamento de Pedidos ");

                            // ------------------------------
                            System.out.println("Digite 0 para sair do Login\n");
                            System.out.print("Digite sua opção: ");
                            sair2 = teclado.nextLine();
                            boolean flag = false;
                            if (InformacoesUsuario.getInstance().getFuncionario().getCargo().getGerencia()) {
                                if (sair2.equals("1")) {
                                    TesteFuncionario.executarFuncionario();
                                    flag = true;
                                } else if (sair2.equals("3")) {
                                    TesteCargo.executarCargo();
                                }
                            }
                            if (!flag) {
                                if (sair2.equals("2")) {
                                    TesteCliente.executarCliente();
                                } else if (sair2.equals("0")) {
                                } else if (sair2.equals("4")) {
                                    TestePizza.testPizza();
                                } else if (sair2.equals("5")) {
                                    TesteComanda.testComanda();
                                } else {
                                    System.out.println("\nDigite uma opção válida!\n");
                                }
                            }

                        }
                    } else {
                        System.out.println("\n----- Bem vindo Adiministrador " + InformacoesUsuario.getInstance().getNome() + " ---------\n");
                        String sair2 = "1";
                        while (!sair2.equals("0")) {
                            System.out.println("----------- Menu Principal -------------");
                            System.out.println("Digite 1 para ir pro Gerenciamento de Funcionario");
                            System.out.println("Digite 2 para ir pro Gerenciamento de Clientes");
                            System.out.println("Digite 3 para ir pro Gerenciamento de Cargos");
                            System.out.println("Digite 4 para ir para o Gerenciamento de Pizzas ");
                            System.out.println("Digite 5 para ir para o Gerenciamento de Pedidos ");

                            // -------------------------------------------
                            System.out.println("Digite 0 para sair do Login\n");
                            System.out.print("Digite sua opção: ");
                            sair2 = teclado.nextLine();
                            if (sair2.equals("1")) {
                                TesteFuncionario.executarFuncionario();
                            } else if (sair2.equals("2")) {
                                TesteCliente.executarCliente();
                            } else if (sair2.equals("3")) {
                                TesteCargo.executarCargo();
                            } else if (sair2.equals("4")) {
                                TestePizza.testPizza();
                            } else if (sair2.equals("5")) {
                                TesteComanda.testComanda();
                            } else if (sair2.equals("0")) {

                            } else {
                                System.out.println("\nDigite uma opção válida!\n");
                            }
                        }
                    }
                }catch(LoginException e){
                    System.out.println(e.getMessage());
                }
            }
        }
*/
    }
}
