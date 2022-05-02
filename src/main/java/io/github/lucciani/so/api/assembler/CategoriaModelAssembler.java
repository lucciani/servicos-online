package io.github.lucciani.so.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.lucciani.so.api.model.CategoriaModel;
import io.github.lucciani.so.domain.model.Categoria;

@Component
public class CategoriaModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CategoriaModel toModel(Categoria categoria) {
		return modelMapper.map(categoria, CategoriaModel.class);
	}

	public List<CategoriaModel> toCollectionModel(List<Categoria> categorias) {
		return categorias.stream().map(categoria -> toModel(categoria)).collect(Collectors.toList());
	}

}
