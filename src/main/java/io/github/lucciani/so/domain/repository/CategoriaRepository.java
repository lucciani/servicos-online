package io.github.lucciani.so.domain.repository;

import org.springframework.stereotype.Repository;

import io.github.lucciani.so.domain.model.Categoria;

@Repository
public interface CategoriaRepository extends CustomJpaRepository<Categoria, Long>, CategoriaRepositoryQueries {

}
