package io.github.lucciani.so.domain.repository;

import java.util.List;

import io.github.lucciani.so.domain.model.Departamento;

public interface DepartamentoRepositoryQueries {
	
	List<Departamento> find(Boolean ativo);

}
