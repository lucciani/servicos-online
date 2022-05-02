package io.github.lucciani.so.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.lucciani.so.domain.exception.EntidadeEmUsoException;
import io.github.lucciani.so.domain.exception.PrioridadeNaoEncontradaException;
import io.github.lucciani.so.domain.model.Prioridade;
import io.github.lucciani.so.domain.repository.PrioridadeRepository;

@Service
public class CadastroPrioridadeService {

	@Autowired
	private PrioridadeRepository prioridadeRepository;

	@Transactional
	public Prioridade salvar(Prioridade prioridade) {
		return prioridadeRepository.save(prioridade);
	}

	@Transactional
	public void remover(Long prioridadeId) {
		try {
			prioridadeRepository.deleteById(prioridadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new PrioridadeNaoEncontradaException(prioridadeId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Prioridade de código %d não pode ser removida, pois está em uso.", prioridadeId));
		}
	}

	public Prioridade buscarSeExistir(Long prioridadeId) {
		return prioridadeRepository.findById(prioridadeId)
				.orElseThrow(() -> new PrioridadeNaoEncontradaException(prioridadeId));
	}

}
