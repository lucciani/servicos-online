package io.github.lucciani.so.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.lucciani.so.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

	@Query("from Usuario u "
			+ "left join fetch u.grupos g "
			+ "left join fetch g.permissoes p "
			+ "where u.email = :email")
	Optional<Usuario> findByEmail(String email);

}
