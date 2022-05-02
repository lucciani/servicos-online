package io.github.lucciani.so.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String descricao;

	@Column(nullable = false, columnDefinition = "MEDIUMTEXT")
	private String ajuda;

	private Boolean ativo = Boolean.TRUE;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Departamento departamento;

	public void ativar() {
		setAtivo(true);
	}

	public void inativar() {
		setAtivo(false);
	}

}
