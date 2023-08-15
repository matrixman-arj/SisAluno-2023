package br.mil.eb.decex.sisaluno.repository.filter;

import java.time.LocalDate;

import br.mil.eb.decex.sisaluno.enumerated.Ano;
import br.mil.eb.decex.sisaluno.model.Aluno;
import br.mil.eb.decex.sisaluno.model.Matricula;

public class MatriculaFilter {
	
	private String cpf;
	private Matricula matricula;
	private Aluno aluno;
	private LocalDate dataCriacao;	
	private Ano anoLetivo;
	private LocalDate dataInicioCurso;
	private LocalDate dataFinalCurso;
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}		
	
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public Ano getAnoLetivo() {
		return anoLetivo;
	}
	public void setAnoLetivo(Ano anoLetivo) {
		this.anoLetivo = anoLetivo;
	}
	
	public LocalDate getDataInicioCurso() {
		return dataInicioCurso;
	}
	public void setDataInicioCurso(LocalDate dataInicioCurso) {
		this.dataInicioCurso = dataInicioCurso;
	}
	
	public LocalDate getDataFinalCurso() {
		return dataFinalCurso;
	}
	public void setDataFinalCurso(LocalDate dataFinalCurso) {
		this.dataFinalCurso = dataFinalCurso;
	}	
					
}
