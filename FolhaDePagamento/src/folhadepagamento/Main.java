package folhadepagamento;

import java.util.Scanner;

public class Main {
    private static Scanner leitor = new Scanner(System.in);
    public static void menu(Sistema sistema){
        System.out.println("MENU:");
        System.out.println("1- Adição de um empregado");
        System.out.println("2- Remoção de um empregado");
        System.out.println("3- Lançar um Cartão de Ponto");
        System.out.println("4- Lançar um Resultado Venda");
        System.out.println("5- Lançar uma taxa de serviço");
        System.out.println("6- Alterar detalhes de um empregado");
        System.out.println("7- Rodar a folha de pagamento para hoje");
        System.out.println("8- Undo");
        System.out.println("9- Redo");
        System.out.println("10- Agenda de Pagamento");
        System.out.println("11- Criação de Novas Agendas de Pagamento");
        System.out.println("12- Listar empregados cadastrados");
        System.out.println("0- Sair!");
        int escolha = leitor.nextInt();
        leitor.nextLine();
        int resultado;
        switch(escolha){
            case 1:
                sistema.limpaTela();
                resultado = sistema.adcionarEmpregado();
                sistema.limpaTela();
                if(resultado== 0){
                    System.out.println("Empregado adcionado com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu(sistema);
                break;
            case 2:
                sistema.limpaTela();
                resultado = sistema.removerEmpregado();
                sistema.limpaTela();
                if(resultado== 0){
                    System.out.println("Empregado removido com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu(sistema);
                break;
            case 3:
                sistema.limpaTela();
                if(sistema.getHoristas().size()!= 0){
                    sistema.mostrarHoristas();
                    System.out.println("Digite o id do empregado a ser lancado o cartão:");
                    int id = leitor.nextInt();
                    leitor.nextLine();
                    id = sistema.pegarPossicaoHorista(id);
                    resultado = sistema.lancarCartao(sistema.getHoristas().get(id));
                }else{
                    System.out.println("Não há horistas cadastrados!");
                    resultado = 2;
                }
                sistema.limpaTela();
                if(resultado== 0){
                    System.out.println("Cartão lançado com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu(sistema);
                break;
            case 4:
                sistema.limpaTela();
                if(sistema.getComissionados().size() != 0){
                    sistema.mostrarComissionados();
                    System.out.println("Digite o id do empregado no qual a venda será lançada:");
                    int id = leitor.nextInt();
                    leitor.nextLine();
                    id = sistema.pegarPossicaoComissionado(id);
                    resultado = sistema.lancarVenda(sistema.getComissionados().get(id));
                }else{
                    System.out.println("Não há comissionados cadastrados!");
                    resultado = 2;
                }
                sistema.limpaTela();
                if(resultado== 0){
                    System.out.println("Venda lançada com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu(sistema);
                break;
            case 5:
                sistema.limpaTela();
                resultado = sistema.lancarTaxaServico();
                sistema.limpaTela();
                if(resultado== 0){
                    System.out.println("Taxa lançada com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu(sistema);
                break;
            case 6:
                sistema.limpaTela();
                resultado = sistema.editarEmpregado();
                sistema.limpaTela();
                if(resultado== 0){
                    System.out.println("Empregado editado com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu(sistema);
                break;
            case 7:
                sistema.limpaTela();
                sistema.folhaPagamento();
                sistema.limpaTela();
                menu(sistema);
                break;
            case 8:
                sistema.limpaTela();
                resultado = sistema.undo();
                sistema.limpaTela();
                if(resultado== 0){
                    System.out.println("Undo realizado com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu(sistema);
                break;
            case 9:
                sistema.limpaTela();
                resultado = sistema.redo();
                sistema.limpaTela();
                if(resultado== 0){
                    System.out.println("Redo realizado com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu(sistema);
                break;
            case 10:
                sistema.limpaTela();
                sistema.agendaDePagamento();
                sistema.limpaTela();
                System.out.println("Agenda modificada com sucesso!");
                menu(sistema);
                break;
            case 11:
                sistema.limpaTela();
                sistema.criarNovaAgenda();
                sistema.limpaTela();
                System.out.println("Agenda adcionada com sucesso!");
                menu(sistema);
                break;
            case 12:
                sistema.limpaTela();
                sistema.mostrarAssalariados();
                sistema.mostrarHoristas();
                sistema.mostrarComissionados();
                System.out.println("Digite enter para continuar..");
                leitor.nextLine();
                sistema.limpaTela();
                menu(sistema);
            case 0:
                System.exit(0);
            default:
                sistema.limpaTela();
                System.out.println("Escolha uma opção válida!");
                menu(sistema);
                break;
        }
    }
    
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        System.out.println("Folha de Pagamento:");
        menu(sistema);
    }
    
}
