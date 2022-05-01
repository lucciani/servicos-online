package io.github.lucciani.so.infrastructure;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import io.github.lucciani.so.domain.model.Departamento;
import io.github.lucciani.so.domain.repository.DepartamentoRepositoryQueries;

@Repository
public class DepartamentoRepositoryImpl implements DepartamentoRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Departamento> find(Boolean ativo) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Departamento> criteria = builder.createQuery(Departamento.class);
		Root<Departamento> root = criteria.from(Departamento.class);

		var predicates = new ArrayList<Predicate>();

		if (ativo != null) {
			predicates.add(builder.equal(root.get("ativo"), ativo));
		}

		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Departamento> query = manager.createQuery(criteria);

		return query.getResultList();
	}

}
