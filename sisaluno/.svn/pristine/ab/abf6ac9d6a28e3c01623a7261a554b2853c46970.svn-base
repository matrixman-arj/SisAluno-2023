package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.repository.Cursos;
import br.mil.eb.decex.sisaluno.service.event.curso.CursoSalvoEvent;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.SkuCursoJaCadastradoException;

@Service
public class CadastroCursoService {
	
	@Autowired
	private Cursos cursos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Curso curso) {
		Optional<Curso> cursoOptional = cursos.findBySku(curso.getSku());
		if (cursoOptional.isPresent() && !cursoOptional.get().equals(curso)) {
			throw new SkuCursoJaCadastradoException("Já existe um curso cadastrado com o código informado");
		}	
		
		cursos.saveAndFlush(curso);
		
		publisher.publishEvent(new CursoSalvoEvent(curso));
	}
	
	@Transactional
	public void excluir(Long codigo ) { //Código para excluir quando não há foto.
		try {
			cursos.delete(codigo);
			cursos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar unidade. Já foi usada em alguma entidade.");
		}
	}

}
