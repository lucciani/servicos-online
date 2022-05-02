package io.github.lucciani.so.domain.exception;

public class PrioridadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 6857030081682776077L;

	public PrioridadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public PrioridadeNaoEncontradaException(Long prioridadeId) {
		this(String.format("Não existe um cadastro de prioridade com o código %d", prioridadeId));
	}

}
