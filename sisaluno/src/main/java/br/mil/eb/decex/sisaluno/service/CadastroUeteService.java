package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Uete;
import br.mil.eb.decex.sisaluno.repository.Uetes;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.OMJaCadastradaException;

@Service
public class CadastroUeteService {
	
	@Autowired
	private Uetes uetes;
	
	@Transactional
	public void salvar(Uete uete) {
		Optional<Uete> ueteExistente = uetes.findBySigla(uete.getSigla());
		if(ueteExistente.isPresent() && !ueteExistente.get().equals(uete)) {
			throw new OMJaCadastradaException("Organização Militar já cadastrada!");
		}
		if(ueteExistente.isPresent() && !ueteExistente.get().equals(uete)) {
			uete.setRegiao(ueteExistente.get().getRegiao());
			uete.setEstado(ueteExistente.get().getEstado());
		}
		
		uetes.saveAndFlush(uete);
	}
	
	@Transactional
	public void excluir(Long codigo ) { //Código para excluir quando não há foto.
		try {
			uetes.delete(codigo);
			uetes.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar unidade. Já foi usada em alguma entidade.");
		}
	}

}
