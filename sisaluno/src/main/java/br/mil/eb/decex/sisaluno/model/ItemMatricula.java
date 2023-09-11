package br.mil.eb.decex.sisaluno.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Transient
	private Integer quantidade;
	
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
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
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
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
