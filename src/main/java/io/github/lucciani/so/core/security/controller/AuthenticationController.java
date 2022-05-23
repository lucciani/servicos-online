package io.github.lucciani.so.core.security.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.lucciani.so.core.security.model.TokenModel;
import io.github.lucciani.so.core.security.model.input.JwtAuthenticationInput;
import io.github.lucciani.so.core.security.utils.JwtTokenUtil;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
	private static final String TOKEN_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Gera e retorna um novo token JWT.
	 * 
	 * @param authenticationInput
	 * @param result
	 * @return ResponseEntity<Response<TokenDto>>
	 * @throws AuthenticationException
	 */
	@PostMapping
	public TokenModel gerarTokenJwt(@Valid @RequestBody JwtAuthenticationInput authenticationInput) {

		log.info("Gerando token para o email {}.", authenticationInput.getEmail());
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationInput.getEmail(), authenticationInput.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationInput.getEmail());
		String token = jwtTokenUtil.obterToken(userDetails);

		return new TokenModel(token);
	}

	/**
	 * Gera um novo token com uma nova data de expiração.
	 * 
	 * @param request
	 * @return ResponseEntity<Response<TokenDto>>
	 */
	@PostMapping(value = "/refresh")
	public TokenModel gerarRefreshTokenJwt(HttpServletRequest request) {
		log.info("Gerando refresh token JWT.");

		TokenModel tokenModel = new TokenModel();
		List<String> problemFields = new ArrayList<>();

		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));

		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
		}

		if (!token.isPresent() || token.isEmpty()) {
			problemFields.add("Token não informado.");
			tokenModel.setErros(problemFields);
			return tokenModel;
		} else if (!jwtTokenUtil.tokenValido(token.get())) {
			problemFields.add("Token inválido ou expirado.");
			tokenModel.setErros(problemFields);
			return tokenModel;
		}

		String refreshedToken = jwtTokenUtil.refreshToken(token.get());
		return new TokenModel(refreshedToken);
	}

}
