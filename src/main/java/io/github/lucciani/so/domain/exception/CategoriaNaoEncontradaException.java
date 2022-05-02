package io.github.lucciani.so.domain.exception;

public class CategoriaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -8560820316190664155L;

	public CategoriaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public CategoriaNaoEncontradaException(Long categoriaId) {
		this(String.format("Não existe um cadastro de categoria com o código %d", categoriaId));
	}

}