

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * CLASSE ATENDIMENTO
 * 
 * Conceitos aplicados:
 * - COMPOSIÇÃO: contém Prontuario (prontuario não existe sem atendimento)
 * - ENCAPSULAMENTO: atributos privados
 * - SOBRECARGA: construtores sobrecarregados
 */
public class Atendimento {
    
    private int indiceConsulta;
    private Prontuario prontuario; // COMPOSIÇÃO: criado no construtor
    private boolean concluido;
    
    // ========== CONSTRUTORES SOBRECARREGADOS ==========
    
    // Registro básico - só observações
    public Atendimento(int indiceConsulta, String observacoes) {
        this.indiceConsulta = indiceConsulta;
        this.prontuario = new Prontuario(observacoes, getDataAtual());
        this.concluido = false;
    }
    
    // Com diagnóstico
    public Atendimento(int indiceConsulta, String observacoes, String diagnostico) {
        this(indiceConsulta, observacoes);
        this.prontuario.setDiagnostico(diagnostico);
    }
    
    // Com procedimentos
    public Atendimento(int indiceConsulta, String observacoes, String diagnostico, 
                       String[] procedimentosArray, int totalProcedimentos) {
        this(indiceConsulta, observacoes, diagnostico);
        for (int i = 0; i < totalProcedimentos && i < procedimentosArray.length; i++) {
            this.prontuario.adicionarProcedimento(procedimentosArray[i]);
        }
    }
    
    // ========== GETTERS E SETTERS ==========
    
    public int getIndiceConsulta() { return indiceConsulta; }
    public Prontuario getProntuario() { return prontuario; }
    public boolean isConcluido() { return concluido; }
    
    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
    
    // ========== MÉTODOS DE NEGÓCIO ==========
    
    public void adicionarProcedimento(String procedimento) {
        if (prontuario != null) {
            prontuario.adicionarProcedimento(procedimento);
        }
    }
    
    public void adicionarInfoEspecialidade(String info) {
        if (prontuario != null) {
            prontuario.adicionarInfoEspecialidade(info);
        }
    }
    
    public void concluir() {
        this.concluido = true;
    }
    
    public String exibirResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("ATENDIMENTO - Consulta: ").append(indiceConsulta);
        sb.append("\nStatus: ").append(concluido ? "CONCLUÍDO" : "EM ANDAMENTO");
        sb.append("\n").append(prontuario.exibirResumo());
        return sb.toString();
    }
    
    // ========== MÉTODO AUXILIAR PRIVADO ==========
    // MÉTODO PRIVADO: só usado internamente pela classe
    private String getDataAtual() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return now.format(formatter);
    }
}