package io.github.lucciani.so.infrastructure;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import io.github.lucciani.so.domain.model.Categoria;
import io.github.lucciani.so.domain.repository.CategoriaRepositoryQueries;

@Repository
public class CategoriaRepositoryImpl implements CategoriaRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Categoria> findByDepartamentoId(Boolean ativo, Long departamentoId) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
		Root<Categoria> root = criteria.from(Categoria.class);
		root.fetch("departamento", JoinType.INNER);

		var predicates = new ArrayList<Predicate>();

		if (ativo != null) {
			predicates.add(builder.equal(root.get("ativo"), ativo));
		}

		if (departamentoId != null) {
			predicates.add(builder.equal(root.get("departamento").get("id"), departamentoId));
		}

		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Categoria> query = manager.createQuery(criteria);
		return query.getResultList();
	}

}
