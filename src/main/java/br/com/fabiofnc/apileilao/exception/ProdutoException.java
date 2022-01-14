package br.com.fabiofnc.apileilao.exception;

public class ProdutoException extends RuntimeException {

    private static final long serialVersionUID = 4928599035264976611L;

    public ProdutoException (String message) {
        super(message);
    }

    public ProdutoException (Throwable t) {
        super(t);
    }

}
