package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Npor;
import br.mil.eb.decex.sisaluno.repository.Npors;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.NPORJaCadastradoException;


@Service
public class CadastroNporService {
	
	@Autowired
	private Npors npors;
	
	@Transactional
	public void salvar(Npor npor) {
		Optional<Npor> nporExistente = npors.findBySigla(npor.getSigla());
		if(nporExistente.isPresent() && !nporExistente.get().equals(npor)) {
			throw new NPORJaCadastradoException("NPOR já cadastrado!");
		}
		if(nporExistente.isPresent() && !nporExistente.get().equals(npor)) {
			npor.setRegiao(nporExistente.get().getRegiao());
		}
		
		npors.saveAndFlush(npor);
	}
	
	@Transactional
	public void excluir(Long codigo ) { //Código para excluir quando não há foto.
		try {
			npors.delete(codigo);
			npors.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar unidade. Já foi usada em alguma entidade.");
		}
	}

}
