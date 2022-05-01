package io.github.lucciani.so.api.assembler;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.lucciani.so.api.model.DepartamentoModel;
import io.github.lucciani.so.domain.model.Departamento;

@Component
public class DepartamentoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public DepartamentoModel toModel(Departamento departamento) {
		return modelMapper.map(departamento, DepartamentoModel.class);
	}

	public List<DepartamentoModel> toCollectionModel(Collection<Departamento> departamentos) {
		return departamentos.stream().map(departamento -> toModel(departamento)).collect(Collectors.toList());
	}

}
