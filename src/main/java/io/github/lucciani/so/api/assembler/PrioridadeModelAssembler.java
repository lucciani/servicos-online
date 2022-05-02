package io.github.lucciani.so.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.lucciani.so.api.model.PrioridadeModel;
import io.github.lucciani.so.domain.model.Prioridade;

@Component
public class PrioridadeModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PrioridadeModel toModel(Prioridade prioridade) {
		return modelMapper.map(prioridade, PrioridadeModel.class);
	}

	public List<PrioridadeModel> toCollectionModel(Collection<Prioridade> prioridades) {
		return prioridades.stream().map(prioridade -> toModel(prioridade)).collect(Collectors.toList());
	}

}
