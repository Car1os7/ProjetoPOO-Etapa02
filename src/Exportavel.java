// ARQUIVO: src/model/Exportavel.java


/**
 * INTERFACE EXPORTAVEL
 * 
 * Conceitos aplicados:
 * - INTERFACE: define um contrato de comportamento
 * - HERANÇA MÚLTIPLA DE TIPOS: pode ser implementada junto com outras interfaces
 * 
 * Define operações para entidades que podem ter seus dados exportados
 */
public interface Exportavel {
    
    /**
     * Exporta os dados da entidade como String formatada
     * @return dados formatados para exportação
     */
    String exportarDados();
}

