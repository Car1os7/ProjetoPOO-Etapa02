// ARQUIVO: src/model/Agendavel.java
package models;

/**
 * INTERFACE AGENDAVEL
 * 
 * Conceitos aplicados:
 * - INTERFACE: define um contrato de comportamento
 * - HERANÇA MÚLTIPLA DE TIPOS: pode ser implementada junto com outras interfaces
 * 
 * Define operações para entidades que podem ser agendadas
 */
public interface Agendavel {
    
    /**
     * Agenda uma consulta/atendimento
     */
    void agendar();
    
    /**
     * Cancela uma consulta/atendimento
     * @return mensagem de confirmação
     */
    String cancelar();
    
    /**
     * Cancela com motivo específico
     * @param motivo razão do cancelamento
     * @return mensagem de confirmação
     */
    String cancelar(String motivo);
    
    /**
     * Remarca para nova data/horário
     * @param novaData nova data
     * @param novoHorario novo horário
     * @return mensagem de confirmação
     */
    String remarcar(String novaData, String novoHorario);
}