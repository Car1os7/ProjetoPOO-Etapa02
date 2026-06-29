package models;

import exception.ConvenioNaoCobreException;

public class PagamentoConvenio extends Pagamento {
    
    private Convenio convenio;
    private String especialidade;
    
    public PagamentoConvenio(int indiceConsulta, double valorBase, Convenio convenio, String especialidade) {
        super(indiceConsulta, valorBase, "convenio");
        this.convenio = convenio;
        this.especialidade = especialidade;
        
        if (convenio != null && !convenio.cobreEspecialidade(especialidade)) {
            throw new ConvenioNaoCobreException(
                "Convênio " + convenio.getNome() + " não cobre a especialidade: " + especialidade
            );
        }
    }
    
    public Convenio getConvenio() { return convenio; }
    public String getEspecialidade() { return especialidade; }
    
    @Override
    public double calcularValorFinal() {
        if (convenio == null) {
            return valorBase;
        }
        
        double cobertura = convenio.getPercentualCobertura();
        double valorComDesconto = valorBase * (1 - cobertura);
        return Math.round(valorComDesconto * 100.0) / 100.0;
    }
    
    @Override
    public String exibirResumo() {
        String coberturaStr = (convenio != null) ? 
            String.format("%.0f%%", convenio.getPercentualCobertura() * 100) : "Nenhum";
        return super.exibirResumo() + 
               " | Convênio: " + (convenio != null ? convenio.getNome() : "Nenhum") +
               " | Cobertura: " + coberturaStr +
               " | Especialidade: " + especialidade +
               " | Valor Final: R$" + String.format("%.2f", calcularValorFinal());
    }
}
