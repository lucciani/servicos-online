package io.github.lucciani.so.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.lucciani.so.domain.exception.CategoriaNaoEncontradaException;
import io.github.lucciani.so.domain.exception.EntidadeEmUsoException;
import io.github.lucciani.so.domain.model.Categoria;
import io.github.lucciani.so.domain.model.Departamento;
import io.github.lucciani.so.domain.repository.CategoriaRepository;

@Service
public class CadastroCategoriaService {

	private static final String MSG_CATEGORIA_EM_USO = "Categoria de código %d não pode ser removida, pois está em uso.";

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CadastroDepartamentoService cadastroDepartamento;

	@Transactional
	public Categoria salvar(Categoria categoria) {
		Long departamentoId = categoria.getDepartamento().getId();

		Departamento departamento = cadastroDepartamento.buscarSeExistir(departamentoId);

		categoria.setDepartamento(departamento);

		return categoriaRepository.save(categoria);
	}

	@Transactional
	public void remover(Long categoriaId) {
		try {
			categoriaRepository.deleteById(categoriaId);
			categoriaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CategoriaNaoEncontradaException(categoriaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CATEGORIA_EM_USO, categoriaId));
		}

	}

	@Transactional
	public void ativar(Long categoriaId) {
		Categoria categoria = buscarSeExistir(categoriaId);
		categoria.ativar();
	}

	@Transactional
	public void inativar(Long categoriaId) {
		Categoria categoria = buscarSeExistir(categoriaId);
		categoria.inativar();
	}

	public Categoria buscarSeExistir(Long categoriaId) {
		return categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new CategoriaNaoEncontradaException(categoriaId));
	}

}
