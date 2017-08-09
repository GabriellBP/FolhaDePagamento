package folhadepagamento;


public class Assalariado extends Empregado {
    public Assalariado(int id, String nome, String endereco, int metodoPagamento, boolean sindicato, String tipo, double salario) {
        super(id, nome, endereco, metodoPagamento, sindicato, tipo, salario);
    }
    
    public Assalariado(Assalariado assalariado){
        super(assalariado.getId(), assalariado.getNome(), assalariado.getEndereco(), assalariado.getMetodoPagamento(), assalariado.isSindicato(), assalariado.getTipo(), assalariado.getSalario());
    }
    
}
