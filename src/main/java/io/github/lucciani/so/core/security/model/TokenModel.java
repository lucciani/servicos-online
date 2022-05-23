package io.github.lucciani.so.core.security.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenModel {

	private String token;
	private List<String> erros = new ArrayList<>();

	public TokenModel() {
	}

	public TokenModel(String token) {
		this.token = token;
	}

}
