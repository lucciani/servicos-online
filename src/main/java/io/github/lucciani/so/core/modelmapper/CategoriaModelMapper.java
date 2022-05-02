package io.github.lucciani.so.core.modelmapper;

import org.modelmapper.ModelMapper;

import io.github.lucciani.so.api.model.CategoriaModel;
import io.github.lucciani.so.domain.model.Categoria;

public class CategoriaModelMapper {

	public static void modelCategoriaDepartamentoCompact(ModelMapper modelMapper) {
		var categoriaToCategoriaModelTypeMap = modelMapper.createTypeMap(Categoria.class, CategoriaModel.class);
		categoriaToCategoriaModelTypeMap.<String>addMapping(
				categoriaSrc -> categoriaSrc.getDepartamento().getDescricao(),
				(categoriaDest, value) -> categoriaDest.setDepartamento(value));

	}

}
