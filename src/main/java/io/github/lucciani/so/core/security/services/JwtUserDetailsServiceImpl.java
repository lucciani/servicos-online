package io.github.lucciani.so.core.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.github.lucciani.so.core.security.JwtUserFactory;
import io.github.lucciani.so.domain.model.Usuario;
import io.github.lucciani.so.domain.service.CadastroUsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = cadastroUsuario.buscarSeExistir(email);
		
		System.err.println(usuario);

		return JwtUserFactory.create(usuario);
	}

}
