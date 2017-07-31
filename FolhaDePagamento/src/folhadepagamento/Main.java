package folhadepagamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                return 2;
            default:
                limpaTela();
                System.out.println("Escolha um tipo válido!");
                return 1;
        }
        qtdEmpregados++;
        return 0;
    }
    
    public static int removerEmpregado(){
        limpaTela();
        System.out.println("Digite o tipo do empregado:\n1-Assalariado;\n2- Horista\n3-Comissionado:\n4-Cancelar");
        int tipo = leitor.nextInt();
        leitor.nextLine();
        System.out.println("Empregados listados nessa categoria:");
        int id;
        switch(tipo){
            case 1:
                for(int i = 0; i<assalariados.size(); i++){
                    System.out.println("Id: "+assalariados.get(i).getId()+" Nome: "+assalariados.get(i).getNome());
                }
                System.out.println("Digite o id do empregado a ser removido:");
                id = leitor.nextInt();
                leitor.nextLine();
                for(int i = 0; i<assalariados.size(); i++){
                    if(assalariados.get(i).getId() == id){
                        assalariados.remove(i);
                        return 0;
                    }
                }
                break;
            case 2:
                for(int i = 0; i<horistas.size(); i++){
                    System.out.println("Id: "+horistas.get(i).getId()+" Nome: "+horistas.get(i).getNome());
                }
                System.out.println("Digite o id do empregado a ser removido:");
                id = leitor.nextInt();
                leitor.nextLine();
                for(int i = 0; i<horistas.size(); i++){
                    if(horistas.get(i).getId() == id){
                        horistas.remove(i);
                        return 0;
                    }
                }
            case 3:
                for(int i = 0; i<comissionados.size(); i++){
                    System.out.println("Id: "+comissionados.get(i).getId()+" Nome: "+comissionados.get(i).getNome());
                }
                System.out.println("Digite o id do empregado a ser removido:");
                id = leitor.nextInt();
                leitor.nextLine();
                for(int i = 0; i<comissionados.size(); i++){
                    if(comissionados.get(i).getId() == id){
                        comissionados.remove(i);
                        return 0;
                    }
                }
                break;
            case 4:
                return 2;
            default:
                limpaTela();
                System.out.println("Escolha um tipo válido!");
                return 1;
        }
        return 0;
    }
    
    public static int lancarCartao(Horista horista){
        SimpleDateFormat conversor = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String horario;
        if(horista.isEntrou()){
            System.out.println("Digite o horario de saída: (DD/MM/AAAA HH:MM)");
            horario = leitor.nextLine();
            try {
                horista.setSaida(conversor.parse(horario));
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                return 1;
            }
        }else{
            System.out.println("Digite o horario de entrada: (DD/MM/AAAA HH:MM)");
            horario = leitor.nextLine();
            try {
                horista.setEntrada(conversor.parse(horario));
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                return 1;
            }
        }
        return 0;
    }
    
    public static int lancarVenda(){
        
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
        int resultado;
        switch(escolha){
            case 1:
                resultado = adcionarEmpregado();
                if(resultado== 0){
                    System.out.println("Empregado adcionado com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu();
                break;
            case 2:
                resultado = removerEmpregado();
                if(resultado== 0){
                    System.out.println("Empregado removido com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu();
                break;
            case 3:
                for(int i = 0; i<horistas.size(); i++){
                    System.out.println("Id (relativo): "+i+" id: "+horistas.get(i).getId()+" Nome: "+horistas.get(i).getNome());
                }
                System.out.println("Digite o id (relativo) do empregado a ser lancado o cartão:");
                int id = leitor.nextInt();
                leitor.nextLine();
                resultado = lancarCartao(horistas.get(id));
                if(resultado== 0){
                    System.out.println("Cartão lançado com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu();
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
