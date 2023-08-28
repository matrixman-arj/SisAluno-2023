package br.mil.eb.decex.sisaluno.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "item_matricula", schema = "ensino")
public class ItemMatricula {
	
	@Id
    @SequenceGenerator(name = "ENSINO.ITEM_MATRICULA_CODIGO_GENERATOR", sequenceName = "ENSINO.ITEM_MATRICULA_CODIGO_SEQ",  allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENSINO.ITEM_MATRICULA_CODIGO_GENERATOR")
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "codigo_curso")
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
	public void setMatricula(Matricula matricula) {
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
