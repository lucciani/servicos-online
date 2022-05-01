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

import io.github.lucciani.so.api.assembler.DepartamentoInputDiassembler;
import io.github.lucciani.so.api.assembler.DepartamentoModelAssembler;
import io.github.lucciani.so.api.model.DepartamentoModel;
import io.github.lucciani.so.api.model.input.DepartamentoInput;
import io.github.lucciani.so.domain.model.Departamento;
import io.github.lucciani.so.domain.repository.DepartamentoRepository;
import io.github.lucciani.so.domain.service.CadastroDepartamentoService;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private CadastroDepartamentoService cadastroDepartamento;

	@Autowired
	private DepartamentoModelAssembler departamentoModelAssembler;

	@Autowired
	private DepartamentoInputDiassembler departamentoInputDiassembler;

	@GetMapping
	public List<DepartamentoModel> listar(@RequestParam(value = "ativo", required = false) Boolean ativo) {
		return departamentoModelAssembler.toCollectionModel(departamentoRepository.find(ativo));
	}

	@GetMapping(value = "/{departamentoId}")
	public DepartamentoModel buscar(@PathVariable Long departamentoId) {
		return departamentoModelAssembler.toModel(cadastroDepartamento.buscarSeExistir(departamentoId));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public DepartamentoModel adicionar(@RequestBody @Valid DepartamentoInput departamentoInput) {
		Departamento departamento = departamentoInputDiassembler.toDomainObject(departamentoInput);
		return departamentoModelAssembler.toModel(cadastroDepartamento.salvar(departamento));
	}

	@PutMapping(value = "/{departamentoId}")
	public DepartamentoModel atualizar(@PathVariable Long departamentoId,
			@RequestBody @Valid DepartamentoInput departamentoInput) {

		Departamento departamentoAtual = cadastroDepartamento.buscarSeExistir(departamentoId);
		departamentoInputDiassembler.copyToDomainObject(departamentoInput, departamentoAtual);

		return departamentoModelAssembler.toModel(cadastroDepartamento.salvar(departamentoAtual));
	}

	@DeleteMapping(value = "/{departamentoId}/ativo")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long departamentoId) {
		cadastroDepartamento.inativar(departamentoId);
	}

	@PutMapping(value = "/{departamentoId}/ativo")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long departamentoId) {
		cadastroDepartamento.ativar(departamentoId);
	}

}
