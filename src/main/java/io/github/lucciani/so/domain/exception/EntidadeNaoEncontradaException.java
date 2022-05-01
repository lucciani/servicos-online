package io.github.lucciani.so.domain.exception;

public class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = 8617616315371574805L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

}
