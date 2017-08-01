package folhadepagamento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comissionado extends Empregado {
    private float percentualComissao;
    private List<Date> dataVendas = new ArrayList<>();
    private List<Double> vendas = new ArrayList<>();

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
    
     public List<Double> getVendas() {
        return vendas;
    }

    public void setVendas(double venda) {
        this.vendas.add(venda);
    }

    public List<Date> getDataVendas() {
        return dataVendas;
    }

    public void setDataVendas(Date dataVendas) {
        this.dataVendas.add(dataVendas);
    }

}
