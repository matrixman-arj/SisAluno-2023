package br.mil.eb.decex.sisaluno.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "regiao_militar", schema = "comum")
public class RegiaoMilitar implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "COMUM.REGIAO_MILITAR_CODIGO_GENERATOR",sequenceName = "COMUM.REGIAO_MILITAR_CODIGO_SEQ",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "COMUM.REGIAO_MILITAR_CODIGO_GENERATOR")
	private Long codigo;
	
	private String nome;	
	
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
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RegiaoMilitar)) {
			return false;
		}
		RegiaoMilitar other = (RegiaoMilitar) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
	

}
