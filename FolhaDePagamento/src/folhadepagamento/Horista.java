package folhadepagamento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Horista extends Empregado {
    private List<Date> entrada = new ArrayList<>();
    private List<Date> saida = new ArrayList<>();
    private boolean entrou = false;
    
    public Horista(int id, String nome, String endereco, int metodoPagamento, boolean sindicato, String tipo, double salario) {
        super(id, nome, endereco, metodoPagamento, sindicato, tipo, salario);
    }
    
    public Horista(Horista horista){
        super(horista.getId(), horista.getNome(), horista.getEndereco(), horista.getMetodoPagamento(), horista.isSindicato(), horista.getTipo(), horista.getSalario());
    }

    public List<Date> getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada.add(entrada);
    }

    public List<Date> getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida.add(saida);
    }

   
    public boolean isEntrou() {
        return entrou;
    }

    public void setEntrou(boolean entrou) {
        this.entrou = entrou;
    }
    
}
