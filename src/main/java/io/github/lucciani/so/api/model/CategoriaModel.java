package io.github.lucciani.so.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaModel {

	private Long id;
	private String descricao;
	private String ajuda;
	private Boolean ativo;
	private String departamento;

}
