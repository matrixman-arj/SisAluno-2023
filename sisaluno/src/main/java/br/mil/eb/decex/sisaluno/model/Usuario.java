package br.mil.eb.decex.sisaluno.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.enumerated.PostoGraduacao;
import br.mil.eb.decex.sisaluno.validation.AtributoConfirmacao;
import br.mil.eb.decex.sisaluno.validation.IDT;

@AtributoConfirmacao(atributo = "senha", atributoConfirmacao = "confirmacaoSenha"
					, message = "Confirmação da senha não confere")
@Entity
@Table(name = "usuario", schema = "comum")
@DynamicUpdate
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "COMUM.USUARIO_CODIGO_GENERATOR",sequenceName = "COMUM.USUARIO_CODIGO_SEQ",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "COMUM.USUARIO_CODIGO_GENERATOR")
	
	private Long codigo;
	
	@NotBlank(message = "O campo nome é obrigatório! ")
	private String nome;
	
	@NotBlank(message = "O campo nome de guerra é obrigatório! ")
	@Column(name = "nome_guerra")
	private String nomeGuerra;
	
	
	@NotBlank(message = "Identidade é obrigatória")
	@IDT
	private String identidade;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "posto_graduacao")
	@NotNull(message = "O posto é obrigatório")
	private PostoGraduacao posto;
	
	@Column(name = "posto_descricao")
	private String postoDescricao;
			
	@ManyToOne
    @JoinColumn(name = "om_codigo")
	@NotNull(message = "A OM é obrigatória")	
    private OrganizacaoMilitar om;
	
	@NotBlank(message = "E-mail é obrigatório")	
	@Email(message = "E-mail inválido")
	private String email;	
	
	
	private String senha;
		
	
	@Transient	
	private String confirmacaoSenha;
	
	@Transient
	private Long omSistema;
	

	private Boolean ativo;
	
	@Size(min = 1, message = "Selecione ao menos um grupo")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "comum.usuario_grupo", joinColumns = @JoinColumn(name = "codigo_usuario")
	 						, inverseJoinColumns = @JoinColumn(name = "codigo_grupo"))
	private List<Grupo> grupos;
		
	private String foto;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Transient
	private boolean novaFoto;
				
	@PrePersist
	private void prePersist() {
		this.identidade = this.identidade.replaceAll("\\.|-", "");
		this.postoDescricao = this.posto.getDescricao();
	}
	
	
	@PostLoad
	private void postLoad() {
		this.identidade = this.identidade.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");		
		
	}
	
	@PreUpdate
	private void preUpdate() {		
		
		this.confirmacaoSenha = senha;
		
//		System.out.println(">>> senha Modelo: " + getSenha());
//		System.out.println(">>> Confirmação de senha Modelo: " + getConfirmacaoSenha());
//		
		this.identidade = this.identidade.replaceAll("\\.|-", "");
		
	}
		

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

	
	public String getNomeGuerra() {
		return nomeGuerra;
	}

	public void setNomeGuerra(String nomeGuerra) {
		this.nomeGuerra = nomeGuerra;
	}
	

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}
	

	public PostoGraduacao getPosto() {
		return posto;
	}
	
	
	public void setPosto(PostoGraduacao posto) {
		this.posto = posto;
	}
	
	
	public String getPostoDescricao() {
		return postoDescricao;
	}
	public void setPostoDescricao(String postoDescricao) {
		this.postoDescricao = postoDescricao;
	}

	public OrganizacaoMilitar getOm() {
		return om;
	}
	
	
	public void setOm(OrganizacaoMilitar om) {
		this.om = om;
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}		
		
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
	
	public Long getOmSistema() {
		return omSistema;
	}

	public void setOmSistema(Long omSistema) {
		this.omSistema = omSistema;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}	

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public boolean isNovaFoto() {
		return novaFoto;
	}

	public void setNovaFoto(boolean novaFoto) {
		this.novaFoto = novaFoto;
	}	

	public String getFotoOuMock() {
		return !StringUtils.isEmpty(foto) ? foto : "usuario-mock.jpg";
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(this.foto);
	}
	
	public boolean isNovo() {
		return codigo == null;
	}
	
	public boolean isEdicao() {
		return codigo != null;
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}	
	
}
