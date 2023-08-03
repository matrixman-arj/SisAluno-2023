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
@Table(name = "situacao_no_curso", schema = "ensino")
public class SituacaoNoCurso implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "ENSINO.SITUACAONOCURSO_CODIGO_GENERATOR",sequenceName = "ENSINO.SITUACAONOCURSO_CODIGO_SEQ",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ENSINO.SITUACAONOCURSO_CODIGO_GENERATOR")
	private Long codigo;
	
	private String nome;
	
	
	private String descricao;
	
	@ManyToMany()
	@JoinTable(name = "aluno_situacao",			
				joinColumns = @JoinColumn(name = "codigo_situacao"), 
				inverseJoinColumns = @JoinColumn(name = "codigo_aluno"))
	private Set<Aluno> alunos;
		
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
		if (!(obj instanceof SituacaoNoCurso)) {
			return false;
		}
		SituacaoNoCurso other = (SituacaoNoCurso) obj;
		return Objects.equals(codigo, other.codigo);
	}
}
