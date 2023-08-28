package br.mil.eb.decex.sisaluno.model;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.mil.eb.decex.sisaluno.enumerated.ColegioMiltar;
import br.mil.eb.decex.sisaluno.enumerated.Escolaridade;
import br.mil.eb.decex.sisaluno.enumerated.Etnia;
import br.mil.eb.decex.sisaluno.enumerated.OrigemAeronautica;
import br.mil.eb.decex.sisaluno.enumerated.OrigemCivilOuMilitar;
import br.mil.eb.decex.sisaluno.enumerated.OrigemEscolar;
import br.mil.eb.decex.sisaluno.enumerated.OrigemExercito;
import br.mil.eb.decex.sisaluno.enumerated.OrigemMarinha;
import br.mil.eb.decex.sisaluno.enumerated.Paises;
import br.mil.eb.decex.sisaluno.enumerated.Religiao;
import br.mil.eb.decex.sisaluno.enumerated.RendaFamiliar;
import br.mil.eb.decex.sisaluno.validation.CPF;

@Entity
@Table(name = "aluno", schema = "ensino")
public class Aluno implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator(name = "ENSINO.ALUNO_CODIGO_GENERATOR", sequenceName = "ENSINO.ALUNO_CODIGO_SEQ",  allocationSize = 1)
    @GeneratedValue(        strategy = GenerationType.SEQUENCE, generator = "ENSINO.ALUNO_CODIGO_GENERATOR")
    private Long codigo;
	
	@CPF
	@NotBlank(message = "O Campo cpf é obrigatório")
	private String cpf;	
	
	@NotBlank(message = "O Campo nome completo é obrigatório")
	private String nome;
	
	@NotBlank(message = "O Campo sexo é obrigatório")
	private String sexo;
	
	@Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo escolaridade é obrigatório")
    private Escolaridade escolaridade;
    
    @Column(name = "escolaridade_descr")
    private String escolaridadeDescr;
	
    @ManyToOne
    @JoinColumn( name = "naturalidade_codigo")
    @NotNull(message = "O Campo naturalidade é obrigatório")
    private Estado naturalidade;
    
    @Column(name = "naturalidade_nome")
    private String naturalidadeNome;
	
    @Column(name = "data_nascimento")
    @NotNull(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING) 
    @NotNull(message = "O Campo religião é obrigatório")
    private Religiao religiao;
    
    @Column(name = "religiao_descr")
    private String religiaoDescr;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo Origem escolar é obrigatório")
    @Column(name = "origem_escolar")
    private OrigemEscolar origemEscolar;
    
    @Column(name = "origem_escolar_descr")
    private String origemEscolarDescr;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "colegio_militar" )
    private ColegioMiltar colegioMiltar;
    
    @Column(name = "colegio_militar_descr")
    private String colegioMiltarDescr;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo Etnia é obrigatório")
    private Etnia etnia;
    
    @Column(name = "etnia_descr")
    private String etniaDescr;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo Civil/Militar é obrigatório")
    @Column(name = "origem_civil_militar")
    private OrigemCivilOuMilitar origemCivilOuMilitar;
    
    @Column(name = "origem_civil_militar_descr")
    private String origemCivilOuMilitarDescr;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "origem_fab")
    private OrigemAeronautica origemFAB;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "origem_eb")
    private OrigemExercito origemEB;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "origem_mb")
    private OrigemMarinha origemMB;
    
    @Column(name = "origem_militar")
    private String origemMilitar;
    
    @Enumerated(EnumType.STRING)
    private Paises paises;
    
    @Column(name = "paises_descr")
    private String paisesDescr;
    
    @NotNull(message = "Informe se tem pai ou mãe militar")
    @Column(name = "pai_mae_militar")
    private String paiOuMaeMilitar;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo Renda familiar é obrigatório")
    @Column(name = "renda_familiar")
    private RendaFamiliar rendaFamiliar;
    
    @Column(name = "renda_familiar_descr")
    private String rendaFamiliarDescr;
    
    @Column(name = "seg_grad_pos")
    private String segGradPos;
    
    @NotNull( message = "Informe se é cotista ou não")
    private String cotista;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dados_inseridos_por")
    private Usuario inseridoPor;
    
    private Boolean ativo;
    
    private String foto;
	
	@Column(name = "content_type")
	private String contentType;
	
	@Transient
	private boolean novaFoto;
    
    @PrePersist
    private void prePersist() {
        this.cpf = this.cpf.replaceAll("\\.|-", "");
        if (this.religiao != null) {
            this.religiaoDescr = this.religiao.getDescricao();
        }

        if (this.origemEscolar != null) {
            this.origemEscolarDescr = this.origemEscolar.getDescricao();
        }

        if (this.colegioMiltar != null) {
            this.colegioMiltarDescr = this.colegioMiltar.getDescricao();
        }

        if (this.etnia != null) {
            this.etniaDescr = this.etnia.getDescricao();
        }

        if (this.origemCivilOuMilitar != null) {
            this.setOrigemMilitar(this.origemCivilOuMilitar.getDescricao());
        }

        if (this.origemEB != null) {
            this.setOrigemMilitar(this.origemEB.getDescricao());
        }

        if (this.origemFAB != null) {
            this.setOrigemMilitar(this.origemFAB.getDescricao());
        }

        if (this.origemMB != null) {
            this.setOrigemMilitar(this.origemMB.getDescricao());
        }

        if (this.paises != null) {
            this.paisesDescr = this.paises.getDescricao();
        }

        if (this.escolaridade != null) {
            this.escolaridadeDescr = this.escolaridade.getDescricao();
        }       

        if (this.naturalidade != null) {
            this.setNaturalidadeNome(this.naturalidade.getNome());
        }

    }
    
    
    @PostLoad
    private void postLoad() {
        this.cpf = this.cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
    }

    @PreUpdate
    private void preUpdate() {
        this.cpf = this.cpf.replaceAll("\\.|-", "");
    }

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Escolaridade getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(Escolaridade escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getEscolaridadeDescr() {
		return escolaridadeDescr;
	}

	public void setEscolaridadeDescr(String escolaridadeDescr) {
		this.escolaridadeDescr = escolaridadeDescr;
	}

	public Estado getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(Estado naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNaturalidadeNome() {
		return naturalidadeNome;
	}

	public void setNaturalidadeNome(String naturalidadeNome) {
		this.naturalidadeNome = naturalidadeNome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Religiao getReligiao() {
		return religiao;
	}

	public void setReligiao(Religiao religiao) {
		this.religiao = religiao;
	}

	public String getReligiaoDescr() {
		return religiaoDescr;
	}

	public void setReligiaoDescr(String religiaoDescr) {
		this.religiaoDescr = religiaoDescr;
	}

	public OrigemEscolar getOrigemEscolar() {
		return origemEscolar;
	}

	public void setOrigemEscolar(OrigemEscolar origemEscolar) {
		this.origemEscolar = origemEscolar;
	}

	public String getOrigemEscolarDescr() {
		return origemEscolarDescr;
	}

	public void setOrigemEscolarDescr(String origemEscolarDescr) {
		this.origemEscolarDescr = origemEscolarDescr;
	}

	public ColegioMiltar getColegioMiltar() {
		return colegioMiltar;
	}

	public void setColegioMiltar(ColegioMiltar colegioMiltar) {
		this.colegioMiltar = colegioMiltar;
	}

	public String getColegioMiltarDescr() {
		return colegioMiltarDescr;
	}

	public void setColegioMiltarDescr(String colegioMiltarDescr) {
		this.colegioMiltarDescr = colegioMiltarDescr;
	}

	public Etnia getEtnia() {
		return etnia;
	}

	public void setEtnia(Etnia etnia) {
		this.etnia = etnia;
	}

	public String getEtniaDescr() {
		return etniaDescr;
	}

	public void setEtniaDescr(String etniaDescr) {
		this.etniaDescr = etniaDescr;
	}

	public OrigemCivilOuMilitar getOrigemCivilOuMilitar() {
		return origemCivilOuMilitar;
	}

	public void setOrigemCivilOuMilitar(OrigemCivilOuMilitar origemCivilOuMilitar) {
		this.origemCivilOuMilitar = origemCivilOuMilitar;
	}

	public String getOrigemCivilOuMilitarDescr() {
		return origemCivilOuMilitarDescr;
	}

	public void setOrigemCivilOuMilitarDescr(String origemCivilOuMilitarDescr) {
		this.origemCivilOuMilitarDescr = origemCivilOuMilitarDescr;
	}

	public OrigemAeronautica getOrigemFAB() {
		return origemFAB;
	}

	public void setOrigemFAB(OrigemAeronautica origemFAB) {
		this.origemFAB = origemFAB;
	}

	public OrigemExercito getOrigemEB() {
		return origemEB;
	}

	public void setOrigemEB(OrigemExercito origemEB) {
		this.origemEB = origemEB;
	}

	public OrigemMarinha getOrigemMB() {
		return origemMB;
	}

	public void setOrigemMB(OrigemMarinha origemMB) {
		this.origemMB = origemMB;
	}

	public String getOrigemMilitar() {
		return origemMilitar;
	}

	public void setOrigemMilitar(String origemMilitar) {
		this.origemMilitar = origemMilitar;
	}

	public Paises getPaises() {
		return paises;
	}

	public void setPaises(Paises paises) {
		this.paises = paises;
	}

	public String getPaisesDescr() {
		return paisesDescr;
	}

	public void setPaisesDescr(String paisesDescr) {
		this.paisesDescr = paisesDescr;
	}

	public String getPaiOuMaeMilitar() {
		return paiOuMaeMilitar;
	}

	public void setPaiOuMaeMilitar(String paiOuMaeMilitar) {
		this.paiOuMaeMilitar = paiOuMaeMilitar;
	}

	public RendaFamiliar getRendaFamiliar() {
		return rendaFamiliar;
	}

	public void setRendaFamiliar(RendaFamiliar rendaFamiliar) {
		this.rendaFamiliar = rendaFamiliar;
	}

	public String getRendaFamiliarDescr() {
		return rendaFamiliarDescr;
	}

	public void setRendaFamiliarDescr(String rendaFamiliarDescr) {
		this.rendaFamiliarDescr = rendaFamiliarDescr;
	}

	public String getSegGradPos() {
		return segGradPos;
	}

	public void setSegGradPos(String segGradPos) {
		this.segGradPos = segGradPos;
	}

	public String getCotista() {
		return cotista;
	}

	public void setCotista(String cotista) {
		this.cotista = cotista;
	}

	public Usuario getInseridoPor() {
		return inseridoPor;
	}

	public void setInseridoPor(Usuario inseridoPor) {
		this.inseridoPor = inseridoPor;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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
        return this.codigo == null;
    }

    public boolean isEdicao() {
        return this.codigo != null;
    }

	@Override
	public int hashCode() {
		return Objects.hash(codigo, cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(cpf, other.cpf);
	}    
	
}
