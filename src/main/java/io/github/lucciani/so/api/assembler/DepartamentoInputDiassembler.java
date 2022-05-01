package io.github.lucciani.so.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.lucciani.so.api.model.input.DepartamentoInput;
import io.github.lucciani.so.domain.model.Departamento;

@Component
public class DepartamentoInputDiassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Departamento toDomainObject(DepartamentoInput departamentoInput) {
		return modelMapper.map(departamentoInput, Departamento.class);
	}

	public void copyToDomainObject(DepartamentoInput departamentoInput, Departamento departamento) {
		modelMapper.map(departamentoInput, departamento);
	}

}
