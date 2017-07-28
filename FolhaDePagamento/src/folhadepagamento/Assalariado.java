package folhadepagamento;


public class Assalariado extends Empregado {
    private double salario;
    public Assalariado(int id, String nome, String endereco, int metodoPagamento, boolean sindicato, String tipo, double salario) {
        super(id, nome, endereco, metodoPagamento, sindicato, tipo);
        this.salario = salario;
    }
    
}
