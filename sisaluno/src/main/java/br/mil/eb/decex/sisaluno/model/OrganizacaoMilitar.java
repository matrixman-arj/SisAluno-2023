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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "om", schema = "comum")
public class OrganizacaoMilitar implements Serializable{
		
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "COMUM.OM_CODIGO_GENERATOR",sequenceName = "COMUM.OM_CODIGO_SEQ",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "COMUM.OM_CODIGO_GENERATOR")
	private Long codigo;
	
	@NotBlank(message = "O campo sigla é obrigatório")
	private String sigla;
	
	@NotBlank(message = "O campo descrição é obrigatório")
	private String descricao;
	
	@ManyToMany()
	@JoinTable(name = "ensino.curso_om",				
				joinColumns = @JoinColumn(name = "codigo_om"), 
				inverseJoinColumns = @JoinColumn(name = "codigo_curso"))
	private Set<Aluno> alunos;	
	
	@ManyToOne
	@JoinColumn(name = "codigo_rm")
	private RegiaoMilitar regiaoMilitar;	

	@ManyToOne
	@JoinColumn(name = "codigo_estado")
	private Estado estado;
	
	@Transient
	private Regiao regiao;
	
	@ManyToOne
	@JoinColumn(name = "codigo_regiao")
	private Regiao codigoRegiao;
	
	private int ordem;
	
	
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
	
	public Set<Aluno> getAlunos() {
		return alunos;
	}
	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}	
	
	public Regiao getCodigoRegiao() {
		return codigoRegiao;
	}
	public void setCodigoRegiao(Regiao codigoRegiao) {
		this.codigoRegiao = codigoRegiao;
	}
	
	@PrePersist
	private void prePersist() {
		this.codigoRegiao = estado.getRegiao();
	}
	
	public boolean isNova() {
		return codigo == null;
	}
	
	public String getNomeEstadoRegiao() {
		if(this.estado != null) {
			return this.estado.getNome() + "/" + this.estado.getRegiao().getNome();//			
		}
		
		return null;
	}
//	
//	@PostConstruct
//	private void postCosntruct() {
//		if(this.estado == null) {
//			estado = getEstado();  
//			regiao = estado.getRegiao();//			
//		}
//		
//	}
//	
	@PreUpdate
	private void preUpdate() {
		
//		regiao = estado.getRegiao();
		this.estado = getEstado();  
//		this.codigoRegiao = estado.getRegiao();  
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
		OrganizacaoMilitar other = (OrganizacaoMilitar) obj;
		return Objects.equals(codigo, other.codigo);
	}	
	
}
