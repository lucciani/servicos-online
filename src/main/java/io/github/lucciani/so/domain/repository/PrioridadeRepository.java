package io.github.lucciani.so.domain.repository;

import org.springframework.stereotype.Repository;

import io.github.lucciani.so.domain.model.Prioridade;

@Repository
public interface PrioridadeRepository extends CustomJpaRepository<Prioridade, Long> {

}
