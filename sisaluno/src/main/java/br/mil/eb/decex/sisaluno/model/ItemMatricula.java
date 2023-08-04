package br.mil.eb.decex.sisaluno.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_matricula", schema = "ensino")
public class ItemMatricula {
	
	private Long codigo;
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name = "codigo_matricula")
	private Matricula matricula;
	
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
	
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricua(Matricula matricula) {
		this.matricula = matricula;
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
