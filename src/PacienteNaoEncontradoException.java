// ARQUIVO: src/exception/PacienteNaoEncontradoException.java


public class PacienteNaoEncontradoException extends Exception {
    public PacienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
    public PacienteNaoEncontradoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}


