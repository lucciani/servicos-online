package io.github.lucciani.so.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.lucciani.so.domain.exception.EntidadeEmUsoException;
import io.github.lucciani.so.domain.exception.StatusNaoEncontradaException;
import io.github.lucciani.so.domain.model.Status;
import io.github.lucciani.so.domain.repository.StatusRepository;

@Service
public class CadastroStatusService {

	@Autowired
	private StatusRepository statusRepository;

	@Transactional
	public Status salvar(Status status) {
		return statusRepository.save(status);
	}

	@Transactional
	public void remover(Long statusId) {
		try {
			statusRepository.deleteById(statusId);
			statusRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new StatusNaoEncontradaException(statusId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Status de código %d não pode ser removido, pois está em uso.", statusId));
		}
	}

	public Status buscarSeExistir(Long statusId) {
		return statusRepository.findById(statusId).orElseThrow(() -> new StatusNaoEncontradaException(statusId));
	}

}
