// ARQUIVO: src/exception/PacienteInativoException.java


/**
 * EXCEÇÃO PERSONALIZADA
 * 
 * Conceitos aplicados:
 * - EXCEÇÃO PERSONALIZADA: extends Exception (verificada)
 * - SOBRECARGA DE CONSTRUTORES: mensagem + causa
 * 
 * Lançada quando tenta-se agendar para paciente inativo
 */
public class PacienteInativoException extends Exception {
    
    // Construtor com mensagem
    public PacienteInativoException(String mensagem) {
        super(mensagem);
    }
    
    // Construtor com mensagem + causa (SOBRECARGA)
    public PacienteInativoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
