package io.github.lucciani.so.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.lucciani.so.api.model.PermissaoModel;
import io.github.lucciani.so.domain.model.Permissao;

@Component
public class PermissaoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public PermissaoModel toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoModel.class);
	}

	public List<PermissaoModel> toCollectionModel(List<Permissao> permissao) {
		return permissao.stream().map(per -> toModel(per)).collect(Collectors.toList());
	}

}
