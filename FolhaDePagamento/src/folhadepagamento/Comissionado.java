package folhadepagamento;

public class Comissionado extends Empregado {
    private double salario;
    private float percentualComissao;
    public Comissionado(int id, String nome, String endereco, int metodoPagamento, boolean sindicato, String tipo, double salario, float percentualComissao) {
        super(id, nome, endereco, metodoPagamento, sindicato, tipo);
        this.salario = salario;
        this.percentualComissao = percentualComissao;
    }
    
     public void funcao(){
        
    }
}
