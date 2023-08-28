package br.mil.eb.decex.sisaluno.repository.filter;

import java.time.LocalDate;
import java.util.List;

import br.mil.eb.decex.sisaluno.enumerated.Ano;
import br.mil.eb.decex.sisaluno.model.Curso;

public class MatriculaFilter {
	
	private Long codigo;
	private String cpf;
	private String matricula;
	private String nomeAluno;
	private LocalDate dataCriacao;	
	private Ano anoLetivo;
	private LocalDate dataInicioCurso;
	private LocalDate dataFinalCurso;
	
	private List<Curso> cursos;
	
//	private List<ItemMatricula> itens;
	
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}	
			
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
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
		
	public List<Curso> getCursos() {
		return cursos;
	}
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
//	
//	public List<ItemMatricula> getItens() {
//		return itens;
//	}
//	public void setItens(List<ItemMatricula> itens) {
//		this.itens = itens;
//	}	
	
	
	
	
	
	
	
}
