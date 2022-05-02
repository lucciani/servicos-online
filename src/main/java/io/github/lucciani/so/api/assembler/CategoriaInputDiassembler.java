package io.github.lucciani.so.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.lucciani.so.api.model.input.CategoriaInput;
import io.github.lucciani.so.domain.model.Categoria;
import io.github.lucciani.so.domain.model.Departamento;

@Component
public class CategoriaInputDiassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Categoria toDomainObject(CategoriaInput categoriaInput) {
		return modelMapper.map(categoriaInput, Categoria.class);
	}

	public void copyToDomainObject(CategoriaInput categoriaInput, Categoria categoria) {
		// Para evitar - org.hibernate.HibernateException: identifier of an instance of
		// io.github.lucciani.so.domain.model.Departamento was altered from 2 to 5
		categoria.setDepartamento(new Departamento());

		modelMapper.map(categoriaInput, categoria);
	}

}
