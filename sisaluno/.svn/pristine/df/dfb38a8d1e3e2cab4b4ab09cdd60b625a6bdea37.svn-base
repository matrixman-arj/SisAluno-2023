package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.SituacaoNoCurso;
import br.mil.eb.decex.sisaluno.repository.Situacoes;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.SituacaoJaCadastradoException;

@Service
public class CadastroSituacaoService {
	
	@Autowired
	private Situacoes situacoes;
	
	@Transactional
	public void salvar(SituacaoNoCurso situacao) {
		Optional<SituacaoNoCurso> situacaoExistente = situacoes.findByNome(situacao.getNome());
		if(situacaoExistente.isPresent() && !situacaoExistente.get().equals(situacao)) {
			throw new SituacaoJaCadastradoException("Situacao já cadastrada!");
		}		
		situacoes.saveAndFlush(situacao);
	}
	
	@Transactional
	public void excluir(Long codigo ) { //Código para excluir quando não há foto.
		try {
			situacoes.delete(codigo);
			situacoes.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar situação. Já foi usada em alguma entidade.");
		}
	}

}
