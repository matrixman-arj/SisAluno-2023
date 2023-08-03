package br.mil.eb.decex.sisaluno.model;

import java.util.Objects;

public class ItemMatricula {
	
	private Long codigo;
	private Curso curso;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemMatricula other = (ItemMatricula) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
	
}
