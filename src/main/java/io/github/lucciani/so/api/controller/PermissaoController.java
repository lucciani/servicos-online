package io.github.lucciani.so.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.lucciani.so.api.assembler.PermissaoModelAssembler;
import io.github.lucciani.so.api.model.PermissaoModel;
import io.github.lucciani.so.domain.model.Permissao;
import io.github.lucciani.so.domain.repository.PermissaoRepository;

@RestController
@RequestMapping(path = "/permissoes")
public class PermissaoController {

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;

	@GetMapping
	public List<PermissaoModel> listar() {
		List<Permissao> todasPermissoes = permissaoRepository.findAll();

		return permissaoModelAssembler.toCollectionModel(todasPermissoes);
	}

}
