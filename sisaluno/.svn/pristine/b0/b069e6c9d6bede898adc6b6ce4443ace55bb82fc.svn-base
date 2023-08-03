package br.mil.eb.decex.sisaluno.service.event.aluno;

import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Aluno;

public class AlunoSalvoEvent {
	
	private Aluno aluno;
	
	public AlunoSalvoEvent(Aluno aluno) {
		
		this.aluno = aluno;
	}

	public Aluno getAluno() {
		return aluno;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(aluno.getFoto());
	}
	
	public boolean isNovaFoto() {
		return aluno.isNovaFoto();
	}
}
