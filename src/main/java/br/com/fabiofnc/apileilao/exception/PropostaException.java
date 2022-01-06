package br.com.fabiofnc.apileilao.exception;

public class PropostaException extends RuntimeException {

    private static final long serialVersionUID = 4928599035264976611L;

    public PropostaException (String message) {
        super(message);
    }

    public PropostaException (Throwable t) {
        super(t);
    }

}
