package br.mil.eb.decex.sisaluno.service.event.curso;

import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Curso;

public class CursoSalvoEvent {
	
	private Curso curso;
	
	public CursoSalvoEvent(Curso curso) {		
		this.curso = curso;
	}

	public Curso getCurso() {
		return curso;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(curso.getFoto());
	}
	
	public boolean isNovaFoto() {
		return curso.isNovaFoto();
	}
}
