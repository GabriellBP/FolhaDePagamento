package folhadepagamento;

public class Horista extends Empregado {
    private double salario;
    public Horista(int id, String nome, String endereco, int metodoPagamento, boolean sindicato, String tipo, double salario) {
        super(id, nome, endereco, metodoPagamento, sindicato, tipo);
        this.salario = salario;
    }
    public void susto(){
        
    }
}
