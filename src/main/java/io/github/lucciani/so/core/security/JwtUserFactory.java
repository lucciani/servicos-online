package io.github.lucciani.so.core.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.github.lucciani.so.domain.model.Usuario;

public class JwtUserFactory {

	private JwtUserFactory() {
	}

	/**
	 * Converte e gera um JwtUser com base nos dados de um funcionário.
	 * 
	 * @param usuario
	 * @return JwtUser
	 */
	public static JwtUser create(Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getEmail(), usuario.getSenha(),
				mapToGrantedAuthorities(usuario.getPermissoes()));
	}

	/**
	 * Converte as permissões do usuário para o formato utilizado pelo Spring
	 * Security.
	 * 
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */
	private static Collection<GrantedAuthority> mapToGrantedAuthorities(Set<String> permissoes) {
		return permissoes.stream()
				.map(permissao -> new SimpleGrantedAuthority(permissao))
				.collect(Collectors.toList());
	}

}
