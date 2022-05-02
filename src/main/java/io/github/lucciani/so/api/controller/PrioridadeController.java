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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.lucciani.so.api.assembler.PrioridadeInputDiassembler;
import io.github.lucciani.so.api.assembler.PrioridadeModelAssembler;
import io.github.lucciani.so.api.model.PrioridadeModel;
import io.github.lucciani.so.api.model.input.PrioridadeInput;
import io.github.lucciani.so.domain.model.Prioridade;
import io.github.lucciani.so.domain.repository.PrioridadeRepository;
import io.github.lucciani.so.domain.service.CadastroPrioridadeService;

@RestController
@RequestMapping(value = "/prioridades")
public class PrioridadeController {

	@Autowired
	private PrioridadeRepository prioridadeRepository;

	@Autowired
	private CadastroPrioridadeService cadastroPrioridade;

	@Autowired
	private PrioridadeModelAssembler prioridadeModelAssembler;

	@Autowired
	private PrioridadeInputDiassembler prioridadeInputDiassembler;

	@GetMapping
	public List<PrioridadeModel> listar() {
		return prioridadeModelAssembler.toCollectionModel(prioridadeRepository.findAll());
	}

	@GetMapping(value = "/{prioridadeId}")
	public PrioridadeModel buscar(@PathVariable Long prioridadeId) {
		return prioridadeModelAssembler.toModel(cadastroPrioridade.buscarSeExistir(prioridadeId));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public PrioridadeModel adicionar(@RequestBody @Valid PrioridadeInput prioridadeInput) {
		Prioridade prioridade = prioridadeInputDiassembler.toDomainObject(prioridadeInput);
		return prioridadeModelAssembler.toModel(cadastroPrioridade.salvar(prioridade));
	}

	@PutMapping(value = "/{prioridadeId}")
	public PrioridadeModel atualizar(@PathVariable Long prioridadeId,
			@RequestBody @Valid PrioridadeInput prioridadeInput) {

		Prioridade prioridadeAtual = cadastroPrioridade.buscarSeExistir(prioridadeId);
		prioridadeInputDiassembler.copyToDomainObject(prioridadeInput, prioridadeAtual);

		return prioridadeModelAssembler.toModel(cadastroPrioridade.salvar(prioridadeAtual));
	}

	@DeleteMapping(value = "/{prioridadeId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long prioridadeId) {
		cadastroPrioridade.remover(prioridadeId);
	}

}
