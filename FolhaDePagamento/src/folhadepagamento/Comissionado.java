package folhadepagamento;

public class Comissionado extends Empregado {
    private float percentualComissao;

    
    public Comissionado(int id, String nome, String endereco, int metodoPagamento, boolean sindicato, String tipo, double salario, float percentualComissao) {
        super(id, nome, endereco, metodoPagamento, sindicato, tipo, salario);
        this.percentualComissao = percentualComissao;
    }
    
    public float getPercentualComissao() {
        return percentualComissao;
    }

    public void setPercentualComissao(float percentualComissao) {
        this.percentualComissao = percentualComissao;
    }
}
