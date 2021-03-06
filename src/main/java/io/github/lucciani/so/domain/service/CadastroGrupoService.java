package io.github.lucciani.so.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.lucciani.so.domain.exception.EntidadeEmUsoException;
import io.github.lucciani.so.domain.exception.GrupoNaoEncontradoException;
import io.github.lucciani.so.domain.model.Grupo;
import io.github.lucciani.so.domain.model.Permissao;
import io.github.lucciani.so.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private CadastroPermissaoService cadastroPermissao;

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	@Transactional
	public void excluir(Long grupoId) {
		try {
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, grupoId));
		}
	}

	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarSeExistir(grupoId);
		Permissao permissao = cadastroPermissao.buscarSeExistir(permissaoId);

		grupo.removerPermissao(permissao);
	}

	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarSeExistir(grupoId);
		Permissao permissao = cadastroPermissao.buscarSeExistir(permissaoId);

		grupo.adicionarPermissao(permissao);
	}

	public Grupo buscarSeExistir(Long grupoId) {
		return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}

}
