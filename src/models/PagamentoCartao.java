package model;

import exception.PagamentoInvalidoException;

public class PagamentoCartao extends Pagamento {
    
    private int parcelas;
    private static final int MAX_PARCELAS = 6;
    private static final int PARCELAS_SEM_JUROS = 3;
    private static final double TAXA_PARCELA_EXTRA = 0.025;
    
    public PagamentoCartao(int indiceConsulta, double valorBase, int parcelas) {
        super(indiceConsulta, valorBase, "cartao");
        setParcelas(parcelas);
    }
    
    public int getParcelas() { return parcelas; }
    
    public void setParcelas(int parcelas) {
        if (parcelas < 1) {
            throw new PagamentoInvalidoException("Número de parcelas deve ser maior que 0!");
        }
        if (parcelas > MAX_PARCELAS) {
            throw new PagamentoInvalidoException("Máximo de " + MAX_PARCELAS + " parcelas permitido!");
        }
        this.parcelas = parcelas;
    }
    
    @Override
    public double calcularValorFinal() {
        double valorFinal = valorBase;
        
        if (parcelas > PARCELAS_SEM_JUROS) {
            int parcelasExtras = parcelas - PARCELAS_SEM_JUROS;
            double taxa = TAXA_PARCELA_EXTRA * parcelasExtras;
            valorFinal = valorBase * (1 + taxa);
        }
        
        return Math.round(valorFinal * 100.0) / 100.0;
    }
    
    public double getValorParcela() {
        double valorFinal = calcularValorFinal();
        return Math.round((valorFinal / parcelas) * 100.0) / 100.0;
    }
    
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + 
               " | Parcelas: " + parcelas + 
               " | Valor parcela: R$" + String.format("%.2f", getValorParcela()) +
               " | Valor Final: R$" + String.format("%.2f", calcularValorFinal());
    }
}
