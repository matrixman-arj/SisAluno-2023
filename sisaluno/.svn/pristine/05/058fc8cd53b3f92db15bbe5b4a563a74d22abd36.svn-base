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
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "uete", schema = "ensino")
public class Uete implements Serializable{
		
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ENSINO.UETE_CODIGO_GENERATOR",sequenceName = "ENSINO.UETE_CODIGO_SEQ",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ENSINO.UETE_CODIGO_GENERATOR")
	private Long codigo;
	
	@NotBlank(message = "O campo sigla é obrigatório")
	private String sigla;
	
	@NotBlank(message = "O campo descrição é obrigatório")
	private String descricao;
				
	@Transient
	private Regiao regiao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_rm")
	private RegiaoMilitar regiaoMilitar;
	
	//@NotNull(message = "Selecione um estado")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_estado")
	@JsonIgnore
	private Estado estado;
	
	
	private int ordem;
	
	
	@PreUpdate
	private void preUpdate() {
		if(!isNova()) {
		this.estado.setRegiao(estado.getRegiao());
		this.setEstado(getEstado());
		}
		
	}
	
	
	public Estado getEstado() {
		return estado;
	}	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Regiao getRegiao() {
		return regiao;
	}	
	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}	
	
	public RegiaoMilitar getRegiaoMilitar() {
		return regiaoMilitar;
	}
	public void setRegiaoMilitar(RegiaoMilitar regiaoMilitar) {
		this.regiaoMilitar = regiaoMilitar;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
			
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	
	public boolean isNova() {
		return codigo == null;
	}
	
	public String getNomeEstadoRegiao() {
		if(this.estado != null) {
			return this.estado.getNome() + "/" + this.estado.getRegiao().getNome();
		}
		
		return null;
	}
	
	public String getNomeEstado() {
		if(this.estado != null) {
			return this.estado.getNome();
		}
		
		return null;
	}
	
	public String getNomeRegiao() {
		if(this.estado != null) {
			return this.estado.getRegiao().getNome();
		}
		return null;
	}
	
	public Long getCodigoRegiao() {
		if(this.estado != null) {
			return this.estado.getRegiao().getCodigo();
		}
		return null;
	}
	
	public Long getCodigoEstado() {
		if(this.estado != null) {
			return this.estado.getCodigo();
		}
		return null;
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
		Uete other = (Uete) obj;
		return Objects.equals(codigo, other.codigo);
	}	
	
}
