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
    private static int qtdEmpregadosSindicado = 0;
    private static Scanner leitor = new Scanner(System.in);
    private static SimpleDateFormat conversor = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    
    public static void limpaTela() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    /*funções de busca nas listas*/
    public static void mostrarAssalariados(){
        for(int i = 0; i<assalariados.size(); i++){
            System.out.println("Id: "+assalariados.get(i).getId()+" Nome: "+assalariados.get(i).getNome()+" Tipo:"+assalariados.get(i).getTipo());
        }
    }
    
    public static int pegarPossicaoAssalariado(int id){
        for(int i = 0; i<assalariados.size(); i++){
            if(assalariados.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
    
    public static void mostrarHoristas(){
        for(int i = 0; i<horistas.size(); i++){
            System.out.println("Id: "+horistas.get(i).getId()+" Nome: "+horistas.get(i).getNome()+" Tipo:"+horistas.get(i).getTipo());
        }
    }
    
    public static int pegarPossicaoHorista(int id){
        for(int i = 0; i<horistas.size(); i++){
            if(horistas.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
    
    public static void mostrarComissionados(){
        for(int i = 0; i<comissionados.size(); i++){
            System.out.println("Id: "+comissionados.get(i).getId()+" Nome: "+comissionados.get(i).getNome()+" Tipo:"+comissionados.get(i).getTipo());
        }
    }
    
    public static int pegarPossicaoComissionado(int id){
        for(int i = 0; i<comissionados.size(); i++){
            if(comissionados.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
    
    /*--*/
    
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
                if(sindicato){
                    assalariados.get(assalariados.size() - 1).setIdSindicato(qtdEmpregadosSindicado);
                    qtdEmpregadosSindicado++;
                }
                break;
            case 2:
                System.out.println("Digite o salario por hora do empregado :");
                salario = leitor.nextDouble();
                leitor.nextLine();
                horistas.add(new Horista(qtdEmpregados, nome, endereco, pagamento, sindicato, "Horista", salario));
                if(sindicato){
                    horistas.get(horistas.size() - 1).setIdSindicato(qtdEmpregadosSindicado);
                    qtdEmpregadosSindicado++;
                }
                break;
            case 3:
                System.out.println("Digite o salario mensal do empregado:");
                salario = leitor.nextDouble();
                leitor.nextLine();
                System.out.println("Digite o percentual da comissão do empregado:");
                float comissao = leitor.nextFloat();
                comissionados.add(new Comissionado(qtdEmpregados, nome, endereco, pagamento, sindicato, "Comissionado", salario, comissao));
                if(sindicato){
                    comissionados.get(comissionados.size() - 1).setIdSindicato(qtdEmpregadosSindicado);
                    qtdEmpregadosSindicado++;
                }
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
                if(assalariados.size() == 0){
                    System.out.println("Não há esse tipo de empregado!");
                    return 2;            
                }
                mostrarAssalariados();
                System.out.println("Digite o id do empregado a ser removido:");
                id = leitor.nextInt();
                leitor.nextLine();
                id = pegarPossicaoAssalariado(id);
                if(id == -1){
                    System.out.println("Id incorreto!");
                    return 2;
                }
                assalariados.remove(id);
                return 0;
            case 2:
                if(horistas.size() == 0){
                    System.out.println("Não há esse tipo de empregado!");
                    return 2;            
                }
                mostrarHoristas();
                System.out.println("Digite o id do empregado a ser removido:");
                id = leitor.nextInt();
                leitor.nextLine();
                id = pegarPossicaoHorista(id);
                if(id == -1){
                     System.out.println("Id incorreto!");
                    return 2;
                }
                horistas.remove(id);
                return 0;               
            case 3:
                if(comissionados.size() == 0){
                    System.out.println("Não há esse tipo de empregado!");
                    return 2;            
                }
                mostrarComissionados();
                System.out.println("Digite o id do empregado a ser removido:");
                id = leitor.nextInt();
                leitor.nextLine();
                id = pegarPossicaoComissionado(id);
                if(id == -1){
                     System.out.println("Id incorreto!");
                    return 2;
                }
                comissionados.remove(id);
                return 0;
                
            case 4:
                return 2;
            default:
                limpaTela();
                System.out.println("Escolha um tipo válido!");
                return 1;
        }
    }
    
    public static int lancarCartao(Horista horista){
        String horario;
        if(horista.isEntrou()){
            horista.setEntrou(false);
            System.out.println("Digite o horario de saída: (DD/MM/AAAA HH:MM)");
            horario = leitor.nextLine();
            try {
                horista.setSaida(conversor.parse(horario));
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                return 1;
            }
        }else{
            horista.setEntrou(true);
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
    
    public static int lancarVenda(Comissionado comissionado){
            System.out.println("Digite a data da venda: (DD/MM/AAAA HH:MM)");
            String dataVenda = leitor.nextLine();
            
            try {
                comissionado.setDataVendas(conversor.parse(dataVenda));
            } catch (ParseException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                return 1;
            }
            
            System.out.println("Digite o valor da venda:");
            double valor = leitor.nextDouble();
            
            return 0;
        
    }
    public static int lancarTaxaServico(){
        if(horistas.isEmpty() && assalariados.isEmpty() && comissionados.isEmpty()){
            System.out.println("Nenhum empregado cadastrado!");
            return 2;
        }
        System.out.println("Empregados disponíveis:");
        int flag = 0;
        if(!assalariados.isEmpty()){
            for(int i = 0; i<assalariados.size(); i++){
                if(assalariados.get(i).isSindicato()){
                    System.out.println("Id do Sindicato: "+assalariados.get(i).getIdSindicato());
                    System.out.println("Id: "+assalariados.get(i).getId()+" Nome: "+assalariados.get(i).getNome()+"Tipo: "+ assalariados.get(i).getTipo());
                    flag = 1;
                }
            }
        }    
        if(!horistas.isEmpty()){
            for(int i = 0; i<horistas.size(); i++){
                if(horistas.get(i).isSindicato()){
                    System.out.println("Id do Sindicato: "+horistas.get(i).getIdSindicato());
                    System.out.println("Id: "+horistas.get(i).getId()+" Nome: "+horistas.get(i).getNome()+" Tipo: "+ horistas.get(i).getTipo());
                    flag = 1;
                }
            }
        }
        if(!comissionados.isEmpty()){
            for(int i = 0; i<comissionados.size(); i++){
                if(comissionados.get(i).isSindicato()){
                    System.out.println("Id do Sindicato: "+comissionados.get(i).getIdSindicato());
                    System.out.println("Id: "+comissionados.get(i).getId()+" Nome: "+comissionados.get(i).getNome()+" Tipo: "+ comissionados.get(i).getTipo());
                    flag = 1;
                }
            }
        }
        if(flag == 0){
            System.out.println("Nenhum");
            return 2;
        }
        System.out.println("Digite o tipo do usuario a inserir a taxa de serviço: 1-Assalariado; 2-Horista; 3-Comissionado:");
        int tipo = leitor.nextInt();
        leitor.nextLine();
        System.out.println("Digite o id do usuario a inserir a taxa de serviço:");
        int id = leitor.nextInt();
        leitor.nextLine();
        System.out.println("Digite a taxa de serviço: ");
        double taxa = leitor.nextDouble();
        switch(tipo){
            case 1:
                id = pegarPossicaoAssalariado(id);
                if(id == -1){
                    System.out.println("Id incorreto!");
                    return 2;
                }
                assalariados.get(id).setTaxaSindicato(taxa);
                break;
            case 2:
                id = pegarPossicaoHorista(id);
                if(id == -1){
                    System.out.println("Id incorreto!");
                    return 2;
                }
                horistas.get(id).setTaxaSindicato(taxa);
                break;
            case 3:
                id = pegarPossicaoComissionado(id);
                if(id == -1){
                    System.out.println("Id incorreto!");
                    return 2;
                }
                comissionados.get(id).setTaxaSindicato(taxa);
                break;
            default:
                return 1;
        }
        
        return 0;
    }
    
    public static int editarEmpregado(){
        if(horistas.isEmpty() && assalariados.isEmpty() && comissionados.isEmpty()){
            System.out.println("Nenhum Empregado cadastrado!");
            return 2;
        }
        System.out.println("Empregados disponíveis:");
        mostrarAssalariados();
        mostrarHoristas();
        mostrarComissionados();
        System.out.println("Digite o tipo do empregado que deseja editar: 1-Assalariado; 2-Horista; 3-Comissionado");
        int tipo = leitor.nextInt();
        System.out.println("Digite o id do empregado que deseja editar:");
        int id = leitor.nextInt();
        if(tipo == 1) id = pegarPossicaoAssalariado(id);
        else if(tipo == 2) id = pegarPossicaoHorista(id);
        else if(tipo == 3) id = pegarPossicaoComissionado(id);
        leitor.nextLine();
        
        int flag = 1, opcao, inovo;
        String novo;
        do{
            System.out.println("O que desejas editar:");
            System.out.println("1-Nome\n2-Endereço\n3-tipo\n4-Método de Pagamento\n5-Sindicato\n6-Identificação no sindicato(disponível apenas para empregados que participam do sindicato)\n7-Taxa sindical(disponível apenas para empregados que participam do sindicato)");
            opcao = leitor.nextInt();
            leitor.nextLine();
            switch(opcao){
                case 1:
                    System.out.println("Digite o novo nome do empregado:");
                    novo = leitor.nextLine();
                    if(tipo == 1) assalariados.get(id).setNome(novo);
                    else if(tipo == 2) horistas.get(id).setNome(novo);
                    else if(tipo == 3) comissionados.get(id).setNome(novo);
                    break;
                case 2:
                    System.out.println("Digite o novo endereço do empregado:");
                    novo = leitor.nextLine();
                    if(tipo == 1) assalariados.get(id).setEndereco(novo);
                    else if(tipo == 2) horistas.get(id).setEndereco(novo);
                    else if(tipo == 3) comissionados.get(id).setEndereco(novo);
                    break;
                case 3:
                    System.out.println("Digite o novo tipo do empregado:('Assalariado', 'Horista', 'Comissionado')");
                    novo = leitor.nextLine();
                    leitor.nextLine();
                    if(tipo == 1) assalariados.get(id).setTipo(novo);
                    else if(tipo == 2) horistas.get(id).setTipo(novo);
                    else if(tipo == 3) comissionados.get(id).setTipo(novo);
                    break;
                case 4:
                    System.out.println("Escolha o novo método de pagamento: (1-Cheque pelos correios; 2-Cheque em mãos; 3-Depósito em conta bancária)");//tratar erros
                    inovo = leitor.nextInt();
                    leitor.nextLine();
                    if(tipo == 1) assalariados.get(id).setMetodoPagamento(inovo);
                    else if(tipo == 2) horistas.get(id).setMetodoPagamento(inovo);
                    else if(tipo == 3) comissionados.get(id).setMetodoPagamento(inovo); 
                    break;
                case 5:
                    System.out.println("Situação do empregado no sindicato mudada!");
                    if(tipo == 1)assalariados.get(id).setSindicato();
                    else if(tipo == 2) horistas.get(id).setSindicato();
                    else if(tipo == 3) comissionados.get(id).setSindicato(); 
                    break;
                case 6:
                    if(tipo == 1){
                        if(assalariados.get(id).isSindicato()){
                            System.out.println("Digite a nova identificação do usuario no sindicato:");
                            inovo = leitor.nextInt();
                            leitor.nextLine();
                            assalariados.get(id).setIdSindicato(inovo);
                        }else System.out.println("Empregado não pertence ao sindicato");
                    }
                    else if(tipo == 2){
                        if(horistas.get(id).isSindicato()){
                            System.out.println("Digite a nova identificação do usuario no sindicato:");
                            inovo = leitor.nextInt();
                            leitor.nextLine();
                            horistas.get(id).setIdSindicato(inovo);
                        }else System.out.println("Empregado não pertence ao sindicato");
                    }
                    else if(tipo == 3){
                        if(comissionados.get(id).isSindicato()){
                            System.out.println("Digite a nova identificação do usuario no sindicato:");
                            inovo = leitor.nextInt();
                            leitor.nextLine();
                            comissionados.get(id).setIdSindicato(inovo);
                        }else System.out.println("Empregado não pertence ao sindicato");
                    } 
                    break;
                case 7:
                    double dnovo;
                    if(tipo == 1){
                        if(assalariados.get(id).isSindicato()){
                            System.out.println("Digite a nova taxa sindical do usuario no sindicato:");
                            dnovo = leitor.nextDouble();
                            leitor.nextLine();
                            assalariados.get(id).setTaxaSindical(dnovo);
                        }else System.out.println("Empregado não pertence ao sindicato");
                    }
                    else if(tipo == 2){
                        if(horistas.get(id).isSindicato()){
                            System.out.println("Digite a nova taxa sindical do usuario no sindicato:");
                            dnovo = leitor.nextDouble();
                            leitor.nextLine();
                            horistas.get(id).setTaxaSindical(dnovo);
                        }else System.out.println("Empregado não pertence ao sindicato");
                    }
                    else if(tipo == 3){
                        if(comissionados.get(id).isSindicato()){
                            System.out.println("Digite a nova taxa sindical do usuario no sindicato:");
                            dnovo = leitor.nextDouble();
                            leitor.nextLine();
                            comissionados.get(id).setTaxaSindical(dnovo);
                        }else System.out.println("Empregado não pertence ao sindicato");
                    } 
                    break;
                default:
                    System.out.println("Opção inválida!");
                    return 2;
            }
            System.out.println("Deseja editar outro detalhe do empregado? 1-Sim 2-Não");
            flag = leitor.nextInt();
            leitor.nextLine();
        }while(flag == 1);
        
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
                limpaTela();
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
                limpaTela();
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
                if(horistas.size()!= 0){
                    mostrarHoristas();
                    System.out.println("Digite o id do empregado a ser lancado o cartão:");
                    int id = leitor.nextInt();
                    leitor.nextLine();
                    id = pegarPossicaoHorista(id);
                    resultado = lancarCartao(horistas.get(id));
                }else{
                    System.out.println("Não há horistas cadastrados!");
                    resultado = 2;
                }
                limpaTela();
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
                if(comissionados.size() != 0){
                    mostrarComissionados();
                    System.out.println("Digite o id do empregado no qual a venda será lançada:");
                    int id = leitor.nextInt();
                    leitor.nextLine();
                    pegarPossicaoComissionado(id);
                    resultado = lancarVenda(comissionados.get(id));
                }else{
                    System.out.println("Não há comissionados cadastrados!");
                    resultado = 2;
                }
                limpaTela();
                if(resultado== 0){
                    System.out.println("Venda lançada com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu();
                break;
            case 5:
                resultado = lancarTaxaServico();
                limpaTela();
                if(resultado== 0){
                    System.out.println("Taxa lançada com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu();
                break;
            case 6:
                resultado = editarEmpregado();
                limpaTela();
                if(resultado== 0){
                    System.out.println("Empregado editado com sucesso!");
                }else if(resultado == 2){
                    System.out.println("Cancelado!");
                }else{
                    System.out.println("Houve algum erro tente novamente!");
                }
                menu();
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
