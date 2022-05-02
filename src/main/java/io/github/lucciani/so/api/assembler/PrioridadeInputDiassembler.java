package io.github.lucciani.so.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.lucciani.so.api.model.input.PrioridadeInput;
import io.github.lucciani.so.domain.model.Prioridade;

@Component
public class PrioridadeInputDiassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Prioridade toDomainObject(PrioridadeInput prioridadeInput) {
		return modelMapper.map(prioridadeInput, Prioridade.class);
	}

	public void copyToDomainObject(PrioridadeInput prioridadeInput, Prioridade prioridade) {
		modelMapper.map(prioridadeInput, prioridade);
	}

}
