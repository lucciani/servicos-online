package io.github.lucciani.so.domain.repository;

import java.util.List;

import io.github.lucciani.so.domain.model.Categoria;

public interface CategoriaRepositoryQueries {
	
	List<Categoria> findByDepartamentoId(Boolean ativo, Long departamentoId);

}
