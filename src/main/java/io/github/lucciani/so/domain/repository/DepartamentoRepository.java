package io.github.lucciani.so.domain.repository;

import org.springframework.stereotype.Repository;

import io.github.lucciani.so.domain.model.Departamento;

@Repository
public interface DepartamentoRepository extends CustomJpaRepository<Departamento, Long>, DepartamentoRepositoryQueries {

}
