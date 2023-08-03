package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Ano;
import br.mil.eb.decex.sisaluno.repository.Anos;
import br.mil.eb.decex.sisaluno.service.exception.AnoJaCadastradoException;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroAnoService {
	
	@Autowired
	private Anos anos;
	
	@Transactional
	public void salvar(Ano ano) {
		Optional<Ano> anoExistente = anos.findByAno(ano.getAno());
		if(anoExistente.isPresent() && !anoExistente.get().equals(ano)) {
			throw new AnoJaCadastradoException("Ano já cadastrado!");
		}		
		anos.saveAndFlush(ano);
	}
	
	@Transactional
	public void excluir(Long codigo ) { //Código para excluir quando não há foto.
		try {
			anos.delete(codigo);
			anos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar unidade. Já foi usada em alguma entidade.");
		}
	}

}
