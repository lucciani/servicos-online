package io.github.lucciani.so.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoIdInput {
	
	@NotNull
	private Long id;

}
