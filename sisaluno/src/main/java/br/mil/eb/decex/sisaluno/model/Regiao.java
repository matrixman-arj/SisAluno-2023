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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "regiao", schema = "comum")
public class Regiao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "REGIAO_CODIGO_GENERATOR",sequenceName = "REGIAO_CODIGO_SEQ",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "REGIAO_CODIGO_GENERATOR")
	private Long codigo;
	
	private String nome;
	
	@NotNull(message = "Estado é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_estado")
	@JsonIgnore
	private Estado estado;
	
	public Long getCodigo() {
		return codigo;
	}	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}	
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public boolean temEstado() {
		return estado != null;
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
		if (!(obj instanceof Regiao)) {
			return false;
		}
		Regiao other = (Regiao) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
	
	

}
