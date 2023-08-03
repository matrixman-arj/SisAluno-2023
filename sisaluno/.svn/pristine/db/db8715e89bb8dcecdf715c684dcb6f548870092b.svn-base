package br.mil.eb.decex.sisaluno.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ano", schema = "ensino")
public class Ano implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "ENSINO.ANO_CODIGO_GENERATOR",sequenceName = "ENSINO.ANO_CODIGO_SEQ",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ENSINO.ANO_CODIGO_GENERATOR")
	private Long codigo;
	
	private String ano;
	
	@ManyToMany()
	@JoinTable(name = "aluno_ano",			
				joinColumns = @JoinColumn(name = "codigo_ano"), 
				inverseJoinColumns = @JoinColumn(name = "codigo_aluno"))
	private Set<Aluno> alunos;
		
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}	
	
	public Set<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}
	public boolean isNovo() {
		return codigo == null;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Ano)) {
			return false;
		}
		Ano other = (Ano) obj;
		return Objects.equals(codigo, other.codigo);
	}
}
