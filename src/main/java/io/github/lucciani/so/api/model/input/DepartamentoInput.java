package io.github.lucciani.so.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoInput {

	@NotBlank
	private String descricao;

}
