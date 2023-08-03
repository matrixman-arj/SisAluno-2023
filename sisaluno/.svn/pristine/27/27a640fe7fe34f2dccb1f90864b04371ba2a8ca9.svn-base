package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.OrganizacaoMilitar;
import br.mil.eb.decex.sisaluno.repository.OMs;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.OMJaCadastradaException;

@Service
public class CadastroOMService {
	
	@Autowired
	private OMs oms;
	
	@Transactional
	public void salvar(OrganizacaoMilitar om) {
		Optional<OrganizacaoMilitar> omExistente = oms.findBySigla(om.getSigla());
		if(omExistente.isPresent() && !omExistente.get().equals(om)) {
			throw new OMJaCadastradaException("Organização Militar já cadastrada!");
		}
		if(omExistente.isPresent() && !omExistente.get().equals(om)) {
			om.setRegiao(omExistente.get().getRegiao());
		}
		
		oms.saveAndFlush(om);
	}
	
	@Transactional
	public void excluir(Long codigo ) { //Código para excluir quando não há foto.
		try {
			oms.delete(codigo);
			oms.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar unidade. Já foi usada em alguma entidade.");
		}
	}

}
