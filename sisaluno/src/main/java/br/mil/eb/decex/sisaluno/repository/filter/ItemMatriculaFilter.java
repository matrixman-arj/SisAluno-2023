package br.mil.eb.decex.sisaluno.repository.filter;

public class ItemMatriculaFilter {
	private Long codigo;	
	private String matricula;
		
	private Integer curso;	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}			
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Integer getCurso() {
		return curso;
	}
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	
}
