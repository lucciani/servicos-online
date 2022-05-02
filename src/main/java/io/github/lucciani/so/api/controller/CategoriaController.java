package io.github.lucciani.so.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.lucciani.so.api.assembler.CategoriaInputDiassembler;
import io.github.lucciani.so.api.assembler.CategoriaModelAssembler;
import io.github.lucciani.so.api.model.CategoriaModel;
import io.github.lucciani.so.api.model.input.CategoriaInput;
import io.github.lucciani.so.domain.exception.CategoriaNaoEncontradaException;
import io.github.lucciani.so.domain.exception.DepartamentoNaoEncontradaException;
import io.github.lucciani.so.domain.exception.NegocioException;
import io.github.lucciani.so.domain.model.Categoria;
import io.github.lucciani.so.domain.repository.CategoriaRepository;
import io.github.lucciani.so.domain.service.CadastroCategoriaService;
import io.github.lucciani.so.domain.service.CadastroDepartamentoService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CadastroCategoriaService cadastroCategoria;

	@Autowired
	private CadastroDepartamentoService cadastroDepartamento;

	@Autowired
	private CategoriaModelAssembler categoriaModelAssembler;

	@Autowired
	private CategoriaInputDiassembler categoriaInputDiassembler;

	@GetMapping
	public List<CategoriaModel> listar(@RequestParam(value = "ativo", required = false) Boolean ativo,
			@RequestParam(value = "departamentoId", required = true) Long departamentoId) {

		cadastroDepartamento.buscarSeExistir(departamentoId);
		return categoriaModelAssembler
				.toCollectionModel(categoriaRepository.findByDepartamentoId(ativo, departamentoId));
	}

	@GetMapping(value = "/{categoriaId}")
	public CategoriaModel buscar(@PathVariable Long categoriaId) {
		return categoriaModelAssembler.toModel(cadastroCategoria.buscarSeExistir(categoriaId));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CategoriaModel adicionar(@RequestBody @Valid CategoriaInput categoriaInput) {
		try {
			Categoria categoria = categoriaInputDiassembler.toDomainObject(categoriaInput);

			return categoriaModelAssembler.toModel(cadastroCategoria.salvar(categoria));
		} catch (CategoriaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		} catch (DepartamentoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping(value = "/{categoriaId}")
	public CategoriaModel atualizar(@PathVariable Long categoriaId, @RequestBody @Valid CategoriaInput categoriaInput) {
		Categoria categoriaAtual = cadastroCategoria.buscarSeExistir(categoriaId);

		categoriaInputDiassembler.copyToDomainObject(categoriaInput, categoriaAtual);

		return categoriaModelAssembler.toModel(cadastroCategoria.salvar(categoriaAtual));
	}

	@DeleteMapping(value = "/{categoriaId}/ativo")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long categoriaId) {
		cadastroCategoria.inativar(categoriaId);
	}

	@PutMapping(value = "/{categoriaId}/ativo")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long categoriaId) {
		cadastroCategoria.ativar(categoriaId);
	}

}
