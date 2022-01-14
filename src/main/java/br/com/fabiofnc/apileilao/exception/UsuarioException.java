package br.com.fabiofnc.apileilao.exception;

public class UsuarioException extends RuntimeException {

    private static final long serialVersionUID = 4928599035264976611L;

    public UsuarioException (String message) {
        super(message);
    }

    public UsuarioException (Throwable t) {
        super(t);
    }

}
