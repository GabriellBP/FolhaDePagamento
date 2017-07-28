package folhadepagamento;

public abstract class  Empregado {
    private int id;
    private String nome;
    private String endereco;
    private String tipo;
    private int metodoPagamento;/*1-Cheque pelos correios; 2-Cheque em mãos; 3-Depósito em conta bancária*/
    private boolean sindicato;/*true-Empregado pertence ao sindicato; false-Empregado não pertence ao sindicato*/

    public Empregado(int id, String nome, String endereco, int metodoPagamento, boolean sindicato, String tipo) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.metodoPagamento = metodoPagamento;
        this.sindicato = sindicato;
        this.tipo = tipo;
    }
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(int metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public boolean isSindicato() {
        return sindicato;
    }

    public void setSindicato(boolean sindicato) {
        this.sindicato = sindicato;
    }
    
}
