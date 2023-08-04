package br.mil.eb.decex.sisaluno.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.repository.Matriculas;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;


@Service
public class CadastroMatriculaService {
		
	@Autowired
	private Matriculas matriculas;
	
	
	@Transactional
	public void salvar(Matricula matricula) {	
		
		matriculas.save(matricula);				
		
	}
		
	
	@Transactional
	public void excluir(Matricula matricula ) {
		try {	
			
			matriculas.delete(matricula);
			matriculas.flush();
			
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar matricula. Já foi usada em alguma entidade.");
		}
	}

}
