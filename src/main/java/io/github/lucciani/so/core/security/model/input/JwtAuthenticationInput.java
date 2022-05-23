package io.github.lucciani.so.core.security.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthenticationInput {

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String senha;

}
