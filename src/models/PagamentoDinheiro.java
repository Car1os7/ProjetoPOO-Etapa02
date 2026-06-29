package models;

public class PagamentoDinheiro extends Pagamento {
    
    private static final double DESCONTO = 0.05;
    
    public PagamentoDinheiro(int indiceConsulta, double valorBase) {
        super(indiceConsulta, valorBase, "dinheiro");
    }
    
    @Override
    public double calcularValorFinal() {
        double valorComDesconto = valorBase * (1 - DESCONTO);
        return Math.round(valorComDesconto * 100.0) / 100.0;
    }
    
    @Override
    public String exibirResumo() {
        return super.exibirResumo() + 
               " | Desconto: 5% | Valor Final: R$" + String.format("%.2f", calcularValorFinal());
    }
}
