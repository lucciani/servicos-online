package io.github.lucciani.so.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaInput {

	@NotBlank
	private String descricao;
	
	@NotBlank
	private String ajuda;

	@Valid
	@NotNull
	private DepartamentoIdInput departamento;

}
