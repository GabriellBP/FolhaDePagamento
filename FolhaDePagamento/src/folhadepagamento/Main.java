package folhadepagamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    
    private static List<Assalariado> assalariados = new ArrayList<>();
    private static List<Horista> horistas = new ArrayList<>();
    private static List<Comissionado> comissionados = new ArrayList<>();
    private static int qtdEmpregados = 0;
    private static Scanner leitor = new Scanner(System.in);
    
    
    public static void limpaTela() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    public static int adcionarEmpregado(){
        limpaTela();
        System.out.println("Digite o nome do empregado:");
        String nome = leitor.nextLine();
        System.out.println("Digite o endereço do empregado:");
        String endereco = leitor.nextLine();
        System.out.println("Escolha o método de pagamento: (1-Cheque pelos correios; 2-Cheque em mãos; 3-Depósito em conta bancária)");//tratar erros
        int pagamento = leitor.nextInt();
        leitor.nextLine();
        System.out.println("O empregado participa do sindicato? (1-Sim; 2-Não)");//tratar erros
        int aux = leitor.nextInt();
        boolean sindicato;
        leitor.nextLine();
        sindicato = aux == 1;
        System.out.println("Escolha o tipo de empregado:\n1-Assalariado\n2-Horista\n3-Comissionado:\n4-Cancelar");//tratar erros
        int tipo = leitor.nextInt();
        leitor.nextLine();
        double salario;
        switch(tipo){
            case 1:
                System.out.println("Digite o salario mensal do empregado:");
                salario = leitor.nextDouble();
                leitor.nextLine();
                assalariados.add(new Assalariado(qtdEmpregados, nome, endereco, pagamento, sindicato, "Assalariado", salario));
                break;
            case 2:
                System.out.println("Digite o salario por hora do empregado :");
                salario = leitor.nextDouble();
                leitor.nextLine();
                horistas.add(new Horista(qtdEmpregados, nome, endereco, pagamento, sindicato, "Horista", salario));
                break;
            case 3:
                System.out.println("Digite o salario mensal do empregado:");
                salario = leitor.nextDouble();
                leitor.nextLine();
                System.out.println("Digite o percentual da comissão do empregado:");
                float comissao = leitor.nextFloat();
                comissionados.add(new Comissionado(qtdEmpregados, nome, endereco, pagamento, sindicato, "Comissionado", salario, comissao));
                break;
            case 4:
                return 0;
            default:
                limpaTela();
                System.out.println("Escolha um tipo válido!");
                return 1;
        }
        qtdEmpregados++;
        return 0;
    }
    
    public static void menu(){
        System.out.println("MENU:");
        System.out.println("1- Adição de um empregado");
        System.out.println("2- Remoção de um empregado");
        System.out.println("3- Lançar um Cartão de Ponto");
        System.out.println("4- Lançar um Resultado Venda");
        System.out.println("5- Lançar uma taxa de serviço");
        System.out.println("6- Alterar detalhes de um empregado");
        System.out.println("7- Rodar a folha de pagamento para hoje");
        System.out.println("8- Undo/redo");
        System.out.println("9- Agenda de Pagamento");
        System.out.println("10- Criação de Novas Agendas de Pagamento");
        int escolha = leitor.nextInt();
        leitor.nextLine();
        switch(escolha){
            case 1:
                adcionarEmpregado();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                break;
            default:
                limpaTela();
                System.out.println("Escolha uma opção válida!");
                menu();
                break;
        }
        
    }
    
    public static void main(String[] args) {
        System.out.println("Folha de Pagamento:");
        menu();
    }
    
}
