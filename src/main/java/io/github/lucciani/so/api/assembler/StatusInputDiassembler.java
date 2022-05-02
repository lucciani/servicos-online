package io.github.lucciani.so.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.lucciani.so.api.model.input.StatusInput;
import io.github.lucciani.so.domain.model.Status;

@Component
public class StatusInputDiassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Status toDomainObject(StatusInput statusInput) {
		return modelMapper.map(statusInput, Status.class);
	}

	public void copyToDomainObject(StatusInput statusInput, Status status) {
		modelMapper.map(statusInput, status);
	}

}
