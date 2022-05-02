package io.github.lucciani.so.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.lucciani.so.api.model.StatusModel;
import io.github.lucciani.so.domain.model.Status;

@Component
public class StatusModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public StatusModel toModel(Status status) {
		return modelMapper.map(status, StatusModel.class);
	}

	public List<StatusModel> toCollectionModel(Collection<Status> status) {
		return status.stream().map(st -> toModel(st)).collect(Collectors.toList());
	}

}
