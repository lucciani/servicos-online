package io.github.lucciani.so.domain.repository;

import org.springframework.stereotype.Repository;

import io.github.lucciani.so.domain.model.Grupo;

@Repository
public interface GrupoRepository extends CustomJpaRepository<Grupo, Long> {

}
