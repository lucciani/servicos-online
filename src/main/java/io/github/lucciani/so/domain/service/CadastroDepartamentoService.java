package io.github.lucciani.so.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.lucciani.so.domain.exception.DepartamentoNaoEncontradaException;
import io.github.lucciani.so.domain.exception.EntidadeEmUsoException;
import io.github.lucciani.so.domain.model.Departamento;
import io.github.lucciani.so.domain.repository.DepartamentoRepository;

@Service
public class CadastroDepartamentoService {

	private static final String MSG_DEPARTAMENTO_EM_USO = "Departamento de código %d não pode ser removida, pois está em uso.";

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Transactional
	public Departamento salvar(Departamento departamento) {
		departamento.setDescricao(departamento.getDescricao().substring(0, 1).toUpperCase()
				+ departamento.getDescricao().substring(1).trim());
		return departamentoRepository.save(departamento);
	}

	@Transactional
	private void remover(Long departamentoId) {
		try {
			departamentoRepository.deleteById(departamentoId);
			departamentoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new DepartamentoNaoEncontradaException(departamentoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_DEPARTAMENTO_EM_USO, departamentoId));
		}

	}

	@Transactional
	public void ativar(Long departamentoId) {
		Departamento departamento = buscarSeExistir(departamentoId);
		departamento.ativar();
	}

	@Transactional
	public void inativar(Long departamentoId) {
		Departamento departamento = buscarSeExistir(departamentoId);
		departamento.inativar();
	}

	public Departamento buscarSeExistir(Long departamentoId) {
		return departamentoRepository.findById(departamentoId)
				.orElseThrow(() -> new DepartamentoNaoEncontradaException(departamentoId));
	}

}
