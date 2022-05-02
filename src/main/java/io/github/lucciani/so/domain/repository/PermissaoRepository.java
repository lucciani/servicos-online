package io.github.lucciani.so.domain.repository;

import org.springframework.stereotype.Repository;

import io.github.lucciani.so.domain.model.Permissao;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<Permissao, Long> {

}
