package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Grupo;
import br.mil.eb.decex.sisaluno.repository.Grupos;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.NomeJaCadastradoException;

@Service
public class CadastroGrupoService {
	
	@Autowired
	private Grupos grupos;
	
	
	@Transactional
	public void salvar(Grupo grupo) {
		Optional<Grupo> grupoOptional = grupos.findByNome(grupo.getNome());
		if(grupoOptional.isPresent() && !grupoOptional.get().equals(grupo)) {
			throw new NomeJaCadastradoException("Grupo já cadastrado!");
		}
		
		grupos.saveAndFlush(grupo);
	}
	
	@Transactional
	public void excluir(Long codigo ) { 
		try {
			grupos.delete(codigo);
			grupos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar grupo. Já foi usado em alguma entidade.");
		}
	}
}
