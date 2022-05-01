package io.github.lucciani.so.domain.exception;

public class DepartamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -8560820316190664155L;

	public DepartamentoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public DepartamentoNaoEncontradaException(Long departamentoId) {
		this(String.format("Não existe um cadastro de departamento com o código %d", departamentoId));
	}

}
