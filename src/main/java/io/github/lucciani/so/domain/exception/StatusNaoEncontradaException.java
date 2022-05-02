package io.github.lucciani.so.domain.exception;

public class StatusNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 6857030081682776077L;

	public StatusNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public StatusNaoEncontradaException(Long statusId) {
		this(String.format("Não existe um cadastro de status com o código %d", statusId));
	}

}
