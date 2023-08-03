package br.mil.eb.decex.sisaluno.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import br.mil.eb.decex.sisaluno.enumerated.Status;

@Entity
@Table(name = "notificacao_diaria")
@DynamicUpdate
public class NotificacaoDiaria {
	
	@Id
	@SequenceGenerator(name = "NOTIFICACAO_DIARIA_CODIGO_GENERATOR",sequenceName = "NOTIFICACAO_DIARIA_CODIGO_SEQ",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "NOTIFICACAO_DIARIA_CODIGO_GENERATOR")
	private Long codigo;
	
	@ManyToOne
	@JoinColumn(name = "usuario_codigo")	
	private Usuario usuario;
	
	@JoinColumn(name = "om_codigo")
	private OrganizacaoMilitar om;
	
	@Column(name = "status_diario")
	private String statusDiario;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_diaria")
	private LocalDateTime dataDiaria;
	
	@Enumerated(EnumType.STRING)
	private Status status;

	
	public Long getCodigo() {
		return codigo;
	}	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}	
	
	
	public Usuario getUsuario() {
		return usuario;
	}	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public OrganizacaoMilitar getOm() {
		return om;
	}	
	public void setOm(OrganizacaoMilitar om) {
		this.om = om;
	}
	
	public String getStatusDiario() {
		return statusDiario;
	}	
	public void setStatusDiario(String statusDiario) {
		this.statusDiario = statusDiario;
	}

	
	public LocalDateTime getDataDiaria() {
		return dataDiaria;
	}	
	public void setDataDiaria(LocalDateTime dataDiaria) {
		this.dataDiaria = dataDiaria;
	}	
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	@PrePersist
	private void prePersist() {			
		dataDiaria = LocalDateTime.now();
		status = Status.AGUARDANDO;
		om = this.usuario.getOm();
		
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
		if (!(obj instanceof NotificacaoDiaria)) {
			return false;
		}
		NotificacaoDiaria other = (NotificacaoDiaria) obj;
		return Objects.equals(codigo, other.codigo);
	}	

}
