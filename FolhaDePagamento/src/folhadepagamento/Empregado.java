package folhadepagamento;

import java.util.Date;

public abstract class  Empregado {
    private int id;
    private String nome;
    private String endereco;
    private String tipo;
    private int metodoPagamento;/*1-Cheque pelos correios; 2-Cheque em mãos; 3-Depósito em conta bancária*/
    
    private boolean sindicato;/*true-Empregado pertence ao sindicato; false-Empregado não pertence ao sindicato*/
    private int idSindicato;
    private double taxaSindical = 0;//fixa por empregado
    private double taxaSindicato = 0;//variavel todo mês
    
    private double salario;
    private Date ultimoPagamento;
    private String agendaPagamento;
    

    public Empregado(int id, String nome, String endereco, int metodoPagamento, boolean sindicato, String tipo, double salario) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.metodoPagamento = metodoPagamento;
        this.sindicato = sindicato;
        this.tipo = tipo;
        this.salario = salario;
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

    public void setSindicato() {
        if(this.sindicato) this.sindicato = false;
        else this.sindicato = true;
    }
    
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getTaxaSindicato() {
        return taxaSindicato;
    }

    public void setTaxaSindicato(double taxaSindicato) {
        this.taxaSindicato = taxaSindicato;
    }

    public int getIdSindicato() {
        return idSindicato;
    }

    public void setIdSindicato(int idSindicato) {
        this.idSindicato = idSindicato;
    }

    public double getTaxaSindical() {
        return taxaSindical;
    }

    public void setTaxaSindical(double taxaSindical) {
        this.taxaSindical = taxaSindical;
    }

    public Date getUltimoPagamento() {
        return ultimoPagamento;
    }

    public void setUltimoPagamento(Date ultimoPagamento) {
        this.ultimoPagamento = ultimoPagamento;
    }

    public String getAgendaPagamento() {
        return agendaPagamento;
    }

    public void setAgendaPagamento(String agendaPagamento) {
        this.agendaPagamento = agendaPagamento;
    }

}
