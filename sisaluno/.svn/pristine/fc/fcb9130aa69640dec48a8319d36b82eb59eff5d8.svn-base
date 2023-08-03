package br.mil.eb.decex.sisaluno.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cidade", schema = "comum")
public class Cidade implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "COMUM.CIDADE_CODIGO_GENERATOR",sequenceName = "COMUM.CIDADE_CODIGO_SEQ",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "COMUM.CIDADE_CODIGO_GENERATOR")
	private Long codigo;
	
	private String nome;
	private String sigla;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codigo_regiao")
	@JsonIgnore
	private Regiao regiao;
	
	
	
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
	
	public String getSigla() {
		return sigla;
	}	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public Regiao getRegiao() {
		return regiao;
	}	
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}
	
	public boolean temRegiao() {
		return regiao != null;
	}
	
	public boolean isNova() {
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
		if (!(obj instanceof Cidade)) {
			return false;
		}
		Cidade other = (Cidade) obj;
		return Objects.equals(codigo, other.codigo);
	}
}
