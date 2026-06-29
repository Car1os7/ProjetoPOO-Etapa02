

import java.util.ArrayList;
import java.util.List;

/**
 * CLASSE PRONTUARIO
 * 
 * Conceitos aplicados:
 * - COMPOSIÇÃO: Prontuario só existe dentro de Atendimento
 * - ENCAPSULAMENTO: atributos privados
 * 
 * O Prontuario é criado apenas no construtor de Atendimento
 * e não pode existir independentemente.
 */
public class Prontuario {
    
    private String observacoes;
    private String diagnostico;
    private List<String> procedimentos;
    private String dataRegistro;
    private List<String> infoEspecialidade; // Informações específicas da especialidade
    
    // Construtor package-private - só pode ser criado dentro do pacote
    // COMPOSIÇÃO: só Atendimento pode criar Prontuario
    Prontuario(String observacoes, String dataRegistro) {
        this.observacoes = observacoes;
        this.diagnostico = "";
        this.procedimentos = new ArrayList<>();
        this.dataRegistro = dataRegistro;
        this.infoEspecialidade = new ArrayList<>();
    }
    
    // ========== MÉTODOS PÚBLICOS ==========
    
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    public void adicionarProcedimento(String procedimento) {
        if (procedimento != null && !procedimento.trim().isEmpty()) {
            this.procedimentos.add(procedimento);
        }
    }
    
    public void adicionarProcedimentos(List<String> procedimentos) {
        if (procedimentos != null) {
            this.procedimentos.addAll(procedimentos);
        }
    }
    
    public void adicionarInfoEspecialidade(String info) {
        if (info != null && !info.trim().isEmpty()) {
            this.infoEspecialidade.add(info);
        }
    }
    
    public String getObservacoes() { return observacoes; }
    public String getDiagnostico() { return diagnostico; }
    public List<String> getProcedimentos() { return new ArrayList<>(procedimentos); }
    public String getDataRegistro() { return dataRegistro; }
    public List<String> getInfoEspecialidade() { return new ArrayList<>(infoEspecialidade); }
    
    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PRONTUARIO ===\n");
        sb.append("Data: ").append(dataRegistro).append("\n");
        sb.append("Observações: ").append(observacoes).append("\n");
        
        if (!diagnostico.isEmpty()) {
            sb.append("Diagnóstico: ").append(diagnostico).append("\n");
        }
        
        if (!procedimentos.isEmpty()) {
            sb.append("Procedimentos: ").append(String.join(", ", procedimentos)).append("\n");
        }
        
        if (!infoEspecialidade.isEmpty()) {
            sb.append("Informações Especialidade: ").append(String.join(" | ", infoEspecialidade)).append("\n");
        }
        
        return sb.toString();
    }
}

