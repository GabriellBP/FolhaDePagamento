package folhadepagamento;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Sistema {
    private List<Assalariado> assalariados = new ArrayList<>();
    private List<Horista> horistas = new ArrayList<>();
    private List<Comissionado> comissionados = new ArrayList<>();
    private int qtdEmpregados = 0;
    private int qtdEmpregadosSindicado = 0;
    private Scanner leitor = new Scanner(System.in);
    private SimpleDateFormat conversor = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private SimpleDateFormat conversor2 = new SimpleDateFormat("dd/MM/yyyy");
    private List<String> agendasPagamento = new ArrayList<>();
    
    private Stack<List<Assalariado>> undoAssalariado = new Stack<>();
    private Stack<List<Horista>> undoHorista = new Stack<>();
    private Stack<List<Comissionado>> undoComissionado = new Stack<>();
    private Stack<List<Assalariado>> redoAssalariado = new Stack<>();
    private Stack<List<Horista>> redoHorista = new Stack<>();
    private Stack<List<Comissionado>> redoComissionado = new Stack<>();

    public Sistema() {
        this.agendasPagamento.add("mensalmente");
        this.agendasPagamento.add("semanalmente");
        this.agendasPagamento.add("bi-semanalmente");
    }
    
    public void limpaTela() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    /*funções de busca nas listas*/
    public void mostrarAssalariados(){
        for(int i = 0; i<assalariados.size(); i++){
            System.out.println("Id: "+assalariados.get(i).getId()+" Nome: "+assalariados.get(i).getNome()+" Tipo:"+assalariados.get(i).getTipo());
        }
    }
    
    public int pegarPossicaoAssalariado(int id){
        for(int i = 0; i<assalariados.size(); i++){
            if(assalariados.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarHoristas(){
        for(int i = 0; i<horistas.size(); i++){
            System.out.println("Id: "+horistas.get(i).getId()+" Nome: "+horistas.get(i).getNome()+" Tipo:"+horistas.get(i).getTipo());
        }
    }
    
    public int pegarPossicaoHorista(int id){
        for(int i = 0; i<horistas.size(); i++){
            if(horistas.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarComissionados(){
        for(int i = 0; i<comissionados.size(); i++){
            System.out.println("Id: "+comissionados.get(i).getId()+" Nome: "+comissionados.get(i).getNome()+" Tipo:"+comissionados.get(i).getTipo());
        }
    }
    
    public int pegarPossicaoComissionado(int id){
        for(int i = 0; i<comissionados.size(); i++){
            if(comissionados.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }
    
    public void mostrarAgendasPagamento(){
        System.out.println("\nAgendas de pagamento cadastradas atualmente:\n");
        int i = 0;
        for(String agenda : agendasPagamento){
            System.out.println(i+": "+agenda);
            i++;
        }
    }
    
    /*--*/
    
    public int adcionarEmpregado() throws InputMismatchException {
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
        Date date = new Date();
        //date = conversor2.format(date);
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
                assalariados.get(assalariados.size() - 1).setUltimoPagamento(date);
                assalariados.get(assalariados.size() - 1).setAgendaPagamento("mensalmente");
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
                horistas.get(horistas.size() - 1).setUltimoPagamento(date);
                horistas.get(horistas.size() - 1).setAgendaPagamento("semanalmente");
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
                comissionados.get(comissionados.size() - 1).setUltimoPagamento(date);
                comissionados.get(comissionados.size() - 1).setAgendaPagamento("bi-semanalmente");
                break;
            case 4:
                return 2;
            default:
                limpaTela();
                System.out.println("Escolha um tipo válido!");
                return 1;
        }
        qtdEmpregados++;
        inserirUndo(assalariados, horistas, comissionados);
        return 0;
    }
    
    public int removerEmpregado() throws InputMismatchException {
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
                inserirUndo(assalariados, horistas, comissionados);
                return 0;
                
            case 4:
                return 2;
            default:
                limpaTela();
                System.out.println("Escolha um tipo válido!");
                return 1;
        }
    }
    
    public int lancarCartao(Horista horista) throws InputMismatchException {
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
                System.out.println("Erro quanto a data!");
                System.out.println(ex.getMessage());
                //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                return 1;
            }
        }
        inserirUndo(assalariados, horistas, comissionados);
        return 0;
    }
    
    public int lancarVenda(Comissionado comissionado) throws InputMismatchException {
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
            leitor.nextLine();
            inserirUndo(assalariados, horistas, comissionados);
            return 0;
        
    }
    public int lancarTaxaServico() throws InputMismatchException {
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
                    System.out.println("Id: "+assalariados.get(i).getId()+" Nome: "+assalariados.get(i).getNome()+" Tipo: "+ assalariados.get(i).getTipo());
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
        inserirUndo(assalariados, horistas, comissionados);
        return 0;
    }
    
    public int editarEmpregado() throws InputMismatchException {
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
                    int idd, metodoo;
                    double salarioo;
                    String nomee, enderecoo;
                    boolean sindicatoo;
                    
                    if(novo.startsWith("Assalariado")){            
                        if(tipo == 1){
                            idd = assalariados.get(id).getId();
                            metodoo = assalariados.get(id).getMetodoPagamento();
                            salarioo = assalariados.get(id).getSalario();
                            nomee = assalariados.get(id).getNome();
                            enderecoo = assalariados.get(id).getEndereco();
                            sindicatoo = assalariados.get(id).isSindicato();
                            assalariados.remove(id);
                        }
                        else if(tipo == 2){
                            idd = horistas.get(id).getId();
                            metodoo = horistas.get(id).getMetodoPagamento();
                            salarioo = horistas.get(id).getSalario();
                            nomee = horistas.get(id).getNome();
                            enderecoo = horistas.get(id).getEndereco();
                            sindicatoo = horistas.get(id).isSindicato();
                            horistas.remove(id);
                        }
                        else{
                            idd = comissionados.get(id).getId();
                            metodoo = comissionados.get(id).getMetodoPagamento();
                            salarioo = comissionados.get(id).getSalario();
                            nomee = comissionados.get(id).getNome();
                            enderecoo = comissionados.get(id).getEndereco();
                            sindicatoo = comissionados.get(id).isSindicato();
                            comissionados.remove(id);
                        }
                        Assalariado empregadoNovo = new Assalariado(idd, nomee, enderecoo, metodoo, sindicatoo, "Assalariado", salarioo);
                        assalariados.add(empregadoNovo);
                        
                    }else if(novo.startsWith("Horista")){
                         if(tipo == 1){
                            idd = assalariados.get(id).getId();
                            metodoo = assalariados.get(id).getMetodoPagamento();
                            salarioo = assalariados.get(id).getSalario();
                            nomee = assalariados.get(id).getNome();
                            enderecoo = assalariados.get(id).getEndereco();
                            sindicatoo = assalariados.get(id).isSindicato();
                            assalariados.remove(id);
                        }
                        else if(tipo == 2){
                            idd = horistas.get(id).getId();
                            metodoo = horistas.get(id).getMetodoPagamento();
                            salarioo = horistas.get(id).getSalario();
                            nomee = horistas.get(id).getNome();
                            enderecoo = horistas.get(id).getEndereco();
                            sindicatoo = horistas.get(id).isSindicato();
                            horistas.remove(id);
                        }
                        else{
                            idd = comissionados.get(id).getId();
                            metodoo = comissionados.get(id).getMetodoPagamento();
                            salarioo = comissionados.get(id).getSalario();
                            nomee = comissionados.get(id).getNome();
                            enderecoo = comissionados.get(id).getEndereco();
                            sindicatoo = comissionados.get(id).isSindicato();
                            comissionados.remove(id);
                        }
                        Horista empregadoNovo = new Horista(idd, nomee, enderecoo, metodoo, sindicatoo, "Horista", salarioo);
                        horistas.add(empregadoNovo);
                        
                    }else if(novo.startsWith("Comissionado")){
                         if(tipo == 1){
                            idd = assalariados.get(id).getId();
                            metodoo = assalariados.get(id).getMetodoPagamento();
                            salarioo = assalariados.get(id).getSalario();
                            nomee = assalariados.get(id).getNome();
                            enderecoo = assalariados.get(id).getEndereco();
                            sindicatoo = assalariados.get(id).isSindicato();
                            assalariados.remove(id);
                        }
                        else if(tipo == 2){
                            idd = horistas.get(id).getId();
                            metodoo = horistas.get(id).getMetodoPagamento();
                            salarioo = horistas.get(id).getSalario();
                            nomee = horistas.get(id).getNome();
                            enderecoo = horistas.get(id).getEndereco();
                            sindicatoo = horistas.get(id).isSindicato();
                            horistas.remove(id);
                        }
                        else{
                            idd = comissionados.get(id).getId();
                            metodoo = comissionados.get(id).getMetodoPagamento();
                            salarioo = comissionados.get(id).getSalario();
                            nomee = comissionados.get(id).getNome();
                            enderecoo = comissionados.get(id).getEndereco();
                            sindicatoo = comissionados.get(id).isSindicato();
                            comissionados.remove(id);
                        }
                        Comissionado empregadoNovo = new Comissionado(idd, nomee, enderecoo, metodoo, sindicatoo, "Comisssionado", salarioo, 0.1f);
                        comissionados.add(empregadoNovo);
                        
                    }
                    
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
        inserirUndo(assalariados, horistas, comissionados);
        return 0;
    }
    
    public int folhaPagamento() throws InputMismatchException {
        inserirUndo(assalariados, horistas, comissionados);
        System.out.println("Digite a data a ser rodada a folha de pagamento: (DD/MM/AAAA)");
        String data = leitor.nextLine();
        Date dataA = null;
        Date dataB = null;
        int mensalDia;
        try {
             dataA = conversor2.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataA);
        
        System.out.println("Empregados que devem ser pagos:");
        double salario = 0;
        /*Assalariado*/
        for(Assalariado a : assalariados){
            dataB = a.getUltimoPagamento();
            if(dataA.before(dataB)) continue;
            long diferencaDias = (dataA.getTime() - dataB.getTime()) / (1000*60*60*24);  
            if(a.getAgendaPagamento().startsWith("mensalmente")){
                if(cal.equals(cal.getActualMaximum(Calendar.DAY_OF_MONTH))){
                    salario = a.getSalario()*((int) (diferencaDias / 30));
                }
            }else if(a.getAgendaPagamento().startsWith("semanalmente")){
                if(cal.get(Calendar.DAY_OF_WEEK) == 6){
                    salario = (a.getSalario()/4)*((int) (diferencaDias / 7));
                }
            }else if(a.getAgendaPagamento().startsWith("bi-semanalmente")){
                if(cal.get(Calendar.DAY_OF_WEEK) == 6){
                    salario = (a.getSalario()/2)*((int) (diferencaDias / 14));
                }
            }else if(a.getAgendaPagamento().startsWith("mensal $")){
                if(cal.equals(cal.getActualMaximum(Calendar.DAY_OF_MONTH))){
                    salario = a.getSalario()*((int) (diferencaDias / 30));
                }
            }else if(a.getAgendaPagamento().startsWith("mensal ")){
                String array[] = new String[2];
                array = a.getAgendaPagamento().split(" ");
                int dia = Integer.parseInt(array[1]);
                if(cal.get(Calendar.DAY_OF_MONTH) == dia){
                    salario = a.getSalario()*((int) (diferencaDias / 30));
                }
            }else if(a.getAgendaPagamento().startsWith("semanal 1 ")){
                String array[] = new String[3];
                array = a.getAgendaPagamento().split(" ");
                int dia;
                if(array[2] == "segunda") dia = 2;
                else if(array[2] == "terca") dia = 3;
                else if(array[2] == "quarta") dia = 4;
                else if(array[2] == "quinta") dia = 5;
                else if(array[2] == "sexta") dia = 6;
                else if(array[2] == "sabado") dia = 7;
                else dia = 1;
                if(cal.get(Calendar.DAY_OF_MONTH) == dia){
                    salario = a.getSalario()*((int) (diferencaDias / 7));
                }
            }else if(a.getAgendaPagamento().startsWith("semanal 2 ")){
                String array[] = new String[3];
                array = a.getAgendaPagamento().split(" ");
                int dia;
                if(array[2] == "segunda") dia = 2;
                else if(array[2] == "terca") dia = 3;
                else if(array[2] == "quarta") dia = 4;
                else if(array[2] == "quinta") dia = 5;
                else if(array[2] == "sexta") dia = 6;
                else if(array[2] == "sabado") dia = 7;
                else dia = 1;
                if(cal.get(Calendar.DAY_OF_MONTH) == dia){
                    salario = a.getSalario()*((int) (diferencaDias / 14));
                }
            }
                
            System.out.println("Id: "+a.getId()+" Nome: "+a.getNome()+" Tipo: "+a.getTipo()+" Salario: "+salario+" Forma de pagamento: "+a.getMetodoPagamento()+" (1-Cheque pelos correios; 2-Cheque em mãos; 3-Depósito em conta bancária)");
            a.setUltimoPagamento(dataA);
        }
        
        /*Horista*/
        for(Horista a : horistas){
            dataB = a.getUltimoPagamento();
            if(dataA.before(dataB)) continue;
            long diferencaDias = (dataA.getTime() - dataB.getTime()) / (1000*60*60*24);  
            float totalHoras = 0, diaHoras = 0;
            if(a.getAgendaPagamento().startsWith("mensalmente")){
                if(cal.equals(cal.getActualMaximum(Calendar.DAY_OF_MONTH))){
                    int tamanho;
                    if(a.isEntrou()) tamanho = a.getEntrada().size()- 1;
                    else tamanho = tamanho = a.getEntrada().size() ;
                    for(int i = 0; i < tamanho; i++){
                        diaHoras = (a.getSaida().get(i).getTime() - a.getEntrada().get(i).getTime()) / (1000*60);
                        if(diaHoras > 8.0) diaHoras *= 1.5;
                        totalHoras += diaHoras;
                        diaHoras = 0;
                    }
                    salario = a.getSalario()*totalHoras;
                }
            }else if(a.getAgendaPagamento().startsWith("semanalmente")){
                if(cal.get(Calendar.DAY_OF_WEEK) == 6){
                    int tamanho;
                    if(a.isEntrou()) tamanho = a.getEntrada().size()- 1;
                    else tamanho = tamanho = a.getEntrada().size() ;
                    for(int i = 0; i < tamanho; i++){
                        diaHoras += (a.getSaida().get(i).getTime() - a.getEntrada().get(i).getTime()) / (1000*60);
                        if(diaHoras > 8.0) diaHoras *= 1.5;
                        totalHoras += diaHoras;
                        diaHoras = 0;
                    }
                    salario = a.getSalario()*totalHoras;
                }
            }else if(a.getAgendaPagamento().startsWith("bi-semanalmente")){
                if(cal.get(Calendar.DAY_OF_WEEK) == 6){
                    int tamanho;
                    if(a.isEntrou()) tamanho = a.getEntrada().size()- 1;
                    else tamanho = tamanho = a.getEntrada().size() ;
                    for(int i = 0; i < tamanho; i++){
                        diaHoras += (a.getSaida().get(i).getTime() - a.getEntrada().get(i).getTime()) / (1000*60);
                        if(diaHoras > 8.0) diaHoras *= 1.5;
                        totalHoras += diaHoras;
                        diaHoras = 0;
                    }
                    salario = a.getSalario()*totalHoras;
                }
            }else if(a.getAgendaPagamento().startsWith("mensal $")){
                if(cal.equals(cal.getActualMaximum(Calendar.DAY_OF_MONTH))){
                    int tamanho;
                    if(a.isEntrou()) tamanho = a.getEntrada().size()- 1;
                    else tamanho = tamanho = a.getEntrada().size() ;
                    for(int i = 0; i < tamanho; i++){
                        diaHoras = (a.getSaida().get(i).getTime() - a.getEntrada().get(i).getTime()) / (1000*60);
                        if(diaHoras > 8.0) diaHoras *= 1.5;
                        totalHoras += diaHoras;
                        diaHoras = 0;
                    }
                    salario = a.getSalario()*totalHoras;
                }
            }else if(a.getAgendaPagamento().startsWith("mensal ")){
                String array[] = new String[2];
                array = a.getAgendaPagamento().split(" ");
                int dia = Integer.parseInt(array[1]);
                if(cal.get(Calendar.DAY_OF_MONTH) == dia){
                    int tamanho;
                    if(a.isEntrou()) tamanho = a.getEntrada().size()- 1;
                    else tamanho = tamanho = a.getEntrada().size() ;
                    for(int i = 0; i < tamanho; i++){
                        diaHoras = (a.getSaida().get(i).getTime() - a.getEntrada().get(i).getTime()) / (1000*60);
                        if(diaHoras > 8.0) diaHoras *= 1.5;
                        totalHoras += diaHoras;
                        diaHoras = 0;
                    }
                    salario = a.getSalario()*totalHoras;
                }
            }else if(a.getAgendaPagamento().startsWith("semanal 1 ")){
                String array[] = new String[3];
                array = a.getAgendaPagamento().split(" ");
                int dia;
                if(array[2] == "segunda") dia = 2;
                else if(array[2] == "terca") dia = 3;
                else if(array[2] == "quarta") dia = 4;
                else if(array[2] == "quinta") dia = 5;
                else if(array[2] == "sexta") dia = 6;
                else if(array[2] == "sabado") dia = 7;
                else dia = 1;
                if(cal.get(Calendar.DAY_OF_MONTH) == dia){
                    int tamanho;
                    if(a.isEntrou()) tamanho = a.getEntrada().size()- 1;
                    else tamanho = tamanho = a.getEntrada().size() ;
                    for(int i = 0; i < tamanho; i++){
                        diaHoras += (a.getSaida().get(i).getTime() - a.getEntrada().get(i).getTime()) / (1000*60);
                        if(diaHoras > 8.0) diaHoras *= 1.5;
                        totalHoras += diaHoras;
                        diaHoras = 0;
                    }
                    salario = a.getSalario()*totalHoras;
                }
            }else if(a.getAgendaPagamento().startsWith("semanal 2 ")){
                String array[] = new String[3];
                array = a.getAgendaPagamento().split(" ");
                int dia;
                if(array[2] == "segunda") dia = 2;
                else if(array[2] == "terca") dia = 3;
                else if(array[2] == "quarta") dia = 4;
                else if(array[2] == "quinta") dia = 5;
                else if(array[2] == "sexta") dia = 6;
                else if(array[2] == "sabado") dia = 7;
                else dia = 1;
                if(cal.get(Calendar.DAY_OF_MONTH) == dia){
                    int tamanho;
                    if(a.isEntrou()) tamanho = a.getEntrada().size()- 1;
                    else tamanho = tamanho = a.getEntrada().size() ;
                    for(int i = 0; i < tamanho; i++){
                        diaHoras += (a.getSaida().get(i).getTime() - a.getEntrada().get(i).getTime()) / (1000*60);
                        if(diaHoras > 8.0) diaHoras *= 1.5;
                        totalHoras += diaHoras;
                        diaHoras = 0;
                    }
                    salario = a.getSalario()*totalHoras;
                }
            }
            System.out.println("Id: "+a.getId()+" Nome: "+a.getNome()+" Tipo: "+a.getTipo()+" Salario: "+salario+" Forma de pagamento: "+a.getMetodoPagamento()+" (1-Cheque pelos correios; 2-Cheque em mãos; 3-Depósito em conta bancária)");
            Date auxiliar = a.getEntrada().get(a.getEntrada().size() - 1);
            a.getSaida().clear();
            a.getEntrada().clear();
            if(a.isEntrou()) a.getEntrada().add(auxiliar);
            a.setUltimoPagamento(dataA);
        }  
        /*Comissionado*/
        for(Comissionado a : comissionados){
            dataB = a.getUltimoPagamento();
            if(dataA.before(dataB)) continue;
            long diferencaDias = (dataA.getTime() - dataB.getTime()) / (1000*60*60*24);  
            if(a.getAgendaPagamento().startsWith("mensalmente")){
                if(cal.equals(cal.getActualMaximum(Calendar.DAY_OF_MONTH))){
                    salario = a.getSalario()*((int) (diferencaDias / 30));
                    int tamanho = a.getVendas().size();
                    for(int i = 0; i<tamanho; i++){
                        salario += (a.getVendas().get(i) * a.getPercentualComissao());
                    }
                }
            }else if(a.getAgendaPagamento().startsWith("semanalmente")){
                if(cal.get(Calendar.DAY_OF_WEEK) == 6){
                    salario = (a.getSalario()/4)*((int) (diferencaDias / 7));
                    int tamanho = a.getVendas().size();
                    for(int i = 0; i<tamanho; i++){
                        salario += (a.getVendas().get(i) * a.getPercentualComissao());
                    }
                }
            }else if(a.getAgendaPagamento().startsWith("bi-semanalmente")){
                if(cal.get(Calendar.DAY_OF_WEEK) == 6){
                    salario = (a.getSalario()/2)*((int) (diferencaDias / 14));
                    int tamanho = a.getVendas().size();
                    for(int i = 0; i<tamanho; i++){
                        salario += (a.getVendas().get(i) * a.getPercentualComissao());
                    }
                }
            }else if(a.getAgendaPagamento().startsWith("mensal $")){
                 if(cal.equals(cal.getActualMaximum(Calendar.DAY_OF_MONTH))){
                    salario = a.getSalario()*((int) (diferencaDias / 30));
                    int tamanho = a.getVendas().size();
                    for(int i = 0; i<tamanho; i++){
                        salario += (a.getVendas().get(i) * a.getPercentualComissao());
                    }
                }
            }else if(a.getAgendaPagamento().startsWith("mensal ")){
                String array[] = new String[2];
                array = a.getAgendaPagamento().split(" ");
                int dia = Integer.parseInt(array[1]);
                if(cal.get(Calendar.DAY_OF_MONTH) == dia){
                    salario = a.getSalario()*((int) (diferencaDias / 30));
                    int tamanho = a.getVendas().size();
                    for(int i = 0; i<tamanho; i++){
                        salario += (a.getVendas().get(i) * a.getPercentualComissao());
                    }
                }
            }else if(a.getAgendaPagamento().startsWith("semanal 1 ")){
                String array[] = new String[3];
                array = a.getAgendaPagamento().split(" ");
                int dia;
                if(array[2] == "segunda") dia = 2;
                else if(array[2] == "terca") dia = 3;
                else if(array[2] == "quarta") dia = 4;
                else if(array[2] == "quinta") dia = 5;
                else if(array[2] == "sexta") dia = 6;
                else if(array[2] == "sabado") dia = 7;
                else dia = 1;
                if(cal.get(Calendar.DAY_OF_MONTH) == dia){
                    salario = (a.getSalario()/4)*((int) (diferencaDias / 7));
                    int tamanho = a.getVendas().size();
                    for(int i = 0; i<tamanho; i++){
                        salario += (a.getVendas().get(i) * a.getPercentualComissao());
                    }
                }
            }else if(a.getAgendaPagamento().startsWith("semanal 2 ")){
                String array[] = new String[3];
                array = a.getAgendaPagamento().split(" ");
                int dia;
                if(array[2] == "segunda") dia = 2;
                else if(array[2] == "terca") dia = 3;
                else if(array[2] == "quarta") dia = 4;
                else if(array[2] == "quinta") dia = 5;
                else if(array[2] == "sexta") dia = 6;
                else if(array[2] == "sabado") dia = 7;
                else dia = 1;
                if(cal.get(Calendar.DAY_OF_MONTH) == dia){
                    salario = (a.getSalario()/2)*((int) (diferencaDias / 14));
                    int tamanho = a.getVendas().size();
                    for(int i = 0; i<tamanho; i++){
                        salario += (a.getVendas().get(i) * a.getPercentualComissao());
                    }
                }
            }
            System.out.println("Id: "+a.getId()+" Nome: "+a.getNome()+" Tipo: "+a.getTipo()+" Salario: "+salario+" Forma de pagamento: "+a.getMetodoPagamento()+" (1-Cheque pelos correios; 2-Cheque em mãos; 3-Depósito em conta bancária)");
            a.getVendas().clear();
            a.getDataVendas().clear();
            a.setUltimoPagamento(dataA);
        }
        System.out.println("Aperte enter para continuar!");
        leitor.nextLine();
        return 0;
    }
    
    public void agendaDePagamento() throws InputMismatchException {
        System.out.println("Empregados:");
        mostrarAssalariados();
        mostrarHoristas();
        mostrarComissionados();
        System.out.println("\nEscolha o tipo de empregado: 1-Assalariado; 2-Horista; 3-Comissionado");
        int tipo = leitor.nextInt();
        leitor.nextLine();
        System.out.println("Escolha o id do empregado:");
        int id = leitor.nextInt();
        leitor.nextLine();
        mostrarAgendasPagamento();
        System.out.println("\nEscolha a nova agenda de pagamento a ser associada ao empregado:");
        int escolha = leitor.nextInt();
        leitor.nextLine();
        if(tipo == 1){
            id = pegarPossicaoAssalariado(id);
            assalariados.get(id).setAgendaPagamento(agendasPagamento.get(escolha));
        }
        else if(tipo == 2){
            id = pegarPossicaoHorista(id);
            horistas.get(id).setAgendaPagamento(agendasPagamento.get(escolha));
        }
        else if(tipo == 3){
            id = pegarPossicaoComissionado(id);
            comissionados.get(id).setAgendaPagamento(agendasPagamento.get(escolha));
        }
        
    }
    
    public void criarNovaAgenda() throws InputMismatchException {
        System.out.println("Digite a nova agenda de pagamento:");
        System.out.println("Formatos aceitos:");
        System.out.println("mensal x (x é um inteiro representando o dia do mes)");
        System.out.println("mensal $ (ultimo dia do mês)");
        System.out.println("semanal 1 x (x é uma string representando o dia da semana) - toda semana no dia x");
        System.out.println("semanal 2 x (x é uma string representando o dia da semana) - a cada duas semanas no dia x");
        String nAgenda = leitor.nextLine();
        agendasPagamento.add(nAgenda);
    }
    
    public int undo(){
        if(undoAssalariado.size()>0 || undoHorista.size()>0 || undoComissionado.size() > 0) {
            List<Assalariado> nListaAssalariado = new ArrayList<>();
            List<Horista> nListaAHorista = new ArrayList<>();
            List<Comissionado> nListaComissionado = new ArrayList<>();

            for(Assalariado a : assalariados){
                nListaAssalariado.add(a);
            }
            for(Comissionado a : comissionados){
                nListaComissionado.add(a);
            }
            for(Horista a : horistas){
                nListaAHorista.add(a);
            }
            
            redoAssalariado.push(nListaAssalariado);
            redoHorista.push(nListaAHorista);
            redoComissionado.push(nListaComissionado);


            assalariados = undoAssalariado.pop();
            horistas = undoHorista.pop();
            comissionados = undoComissionado.pop();
            
            return 0;
        } else return 1; 
    }

    public int redo(){
        if(redoAssalariado.size() > 0 || redoHorista.size() > 0 || redoComissionado.size() > 0) {
            List<Assalariado> nListaAssalariado = new ArrayList<>();
            List<Horista> nListaAHorista = new ArrayList<>();
            List<Comissionado> nListaComissionado = new ArrayList<>();

            for(Assalariado a : assalariados){
                nListaAssalariado.add(a);
            }
            for(Comissionado a : comissionados){
                nListaComissionado.add(a);
            }
            for(Horista a : horistas){
                nListaAHorista.add(a);
            }
            
            undoAssalariado.push(nListaAssalariado);
            undoComissionado.push(nListaComissionado);
            undoHorista.push(nListaAHorista);

            assalariados = redoAssalariado.pop();
            horistas = redoHorista.pop();
            comissionados = redoComissionado.pop();
            
            return 0;
        } else return 1;
        
    }
    
    public void inserirUndo(List<Assalariado> listaAssalariado, List<Horista> listaHorista, List<Comissionado> listaComissionado){
        List<Assalariado> nListaAssalariado = new ArrayList<>();
        List<Horista> nListaAHorista = new ArrayList<>();
        List<Comissionado> nListaComissionado = new ArrayList<>();
        
        for(Assalariado a : listaAssalariado){
            nListaAssalariado.add(a);
        }
        undoAssalariado.push(nListaAssalariado);
        for(Comissionado a : listaComissionado){
            nListaComissionado.add(a);
        }
        undoComissionado.push(nListaComissionado);
        for(Horista a : listaHorista){
            nListaAHorista.add(a);
        }
        undoHorista.push(nListaAHorista);
        
        redoAssalariado = new Stack<>();
        redoHorista = new Stack<>();
        redoComissionado = new Stack<>();
    }
    /**/
    /* GETS */

    public List<Assalariado> getAssalariados() {
        return assalariados;
    }

    public List<Horista> getHoristas() {
        return horistas;
    }

    public List<Comissionado> getComissionados() {
        return comissionados;
    }
    
}
