

public class ConvenioNaoCobreException extends RuntimeException {
    public ConvenioNaoCobreException(String mensagem) {
        super(mensagem);
    }
    public ConvenioNaoCobreException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}