package br.com.fabiofnc.apileilao.exception;

public class ProdutoNaoEncontradoException extends PropostaException {

	private static final long serialVersionUID = 7333436430049684922L;

	public ProdutoNaoEncontradoException(String message) {
		super(message);
	}

}
