package models;

import exception.PagamentoInvalidoException;

public abstract class Pagamento {
    
    protected int indiceConsulta;
    protected double valorBase;
    protected String tipoPagamento;
    protected boolean processado;
    
    public Pagamento(int indiceConsulta, double valorBase, String tipoPagamento) {
        this.indiceConsulta = indiceConsulta;
        setValorBase(valorBase);
        setTipoPagamento(tipoPagamento);
        this.processado = false;
    }
    
    public int getIndiceConsulta() { return indiceConsulta; }
    public double getValorBase() { return valorBase; }
    public String getTipoPagamento() { return tipoPagamento; }
    public boolean isProcessado() { return processado; }
    
    public void setValorBase(double valorBase) {
        if (valorBase < 0) {
            throw new PagamentoInvalidoException("Valor base não pode ser negativo!");
        }
        this.valorBase = valorBase;
    }
    
    public void setTipoPagamento(String tipoPagamento) {
        String tipo = tipoPagamento.toLowerCase();
        if (!tipo.equals("dinheiro") && !tipo.equals("pix") && 
            !tipo.equals("cartao") && !tipo.equals("convenio")) {
            throw new PagamentoInvalidoException("Tipo de pagamento inválido! Opções: dinheiro, pix, cartao, convenio");
        }
        this.tipoPagamento = tipo;
    }
    
    public abstract double calcularValorFinal();
    
    public void processar() {
        if (processado) {
            System.out.println("Pagamento já foi processado.");
            return;
        }
        double valorFinal = calcularValorFinal();
        this.processado = true;
        System.out.println("Pagamento processado! Valor final: R$" + String.format("%.2f", valorFinal));
    }
    
    public String exibirResumo() {
        return "Consulta: " + indiceConsulta + 
               " | Tipo: " + tipoPagamento + 
               " | Valor Base: R$" + String.format("%.2f", valorBase) +
               " | Processado: " + (processado ? "Sim" : "Não");
    }
    
    @Override
    public String toString() {
        return exibirResumo();
    }
}
