package br.com.fabiofnc.apileilao.exception;

public class PropostaInvalida extends PropostaException {

    private static final long serialVersionUID = 1658764856846L;

    public PropostaInvalida(String message) {
        super(message);
    }

    public PropostaInvalida(Throwable t) {
        super(t);
    }

}
