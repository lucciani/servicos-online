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

import io.github.lucciani.so.api.assembler.StatusInputDiassembler;
import io.github.lucciani.so.api.assembler.StatusModelAssembler;
import io.github.lucciani.so.api.model.StatusModel;
import io.github.lucciani.so.api.model.input.StatusInput;
import io.github.lucciani.so.domain.model.Status;
import io.github.lucciani.so.domain.repository.StatusRepository;
import io.github.lucciani.so.domain.service.CadastroStatusService;

@RestController
@RequestMapping(value = "/status")
public class StatusController {

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private CadastroStatusService cadastroStatus;

	@Autowired
	private StatusModelAssembler statusModelAssembler;

	@Autowired
	private StatusInputDiassembler statusInputDiassembler;

	@GetMapping
	public List<StatusModel> listar() {
		return statusModelAssembler.toCollectionModel(statusRepository.findAll());
	}

	@GetMapping(value = "/{statusId}")
	public StatusModel buscar(@PathVariable Long statusId) {
		return statusModelAssembler.toModel(cadastroStatus.buscarSeExistir(statusId));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public StatusModel adicionar(@RequestBody @Valid StatusInput statusInput) {
		Status status = statusInputDiassembler.toDomainObject(statusInput);
		return statusModelAssembler.toModel(cadastroStatus.salvar(status));
	}

	@PutMapping(value = "/{statusId}")
	public StatusModel atualizar(@PathVariable Long statusId, @RequestBody @Valid StatusInput statusInput) {

		Status statusAtual = cadastroStatus.buscarSeExistir(statusId);
		statusInputDiassembler.copyToDomainObject(statusInput, statusAtual);

		return statusModelAssembler.toModel(cadastroStatus.salvar(statusAtual));
	}

	@DeleteMapping(value = "/{statusId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long statusId) {
		cadastroStatus.remover(statusId);
	}

}
