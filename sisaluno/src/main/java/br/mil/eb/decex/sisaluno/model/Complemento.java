package br.mil.eb.decex.sisaluno.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import br.mil.eb.decex.sisaluno.enumerated.CFGOCurso;
import br.mil.eb.decex.sisaluno.enumerated.CFGSCurso;
import br.mil.eb.decex.sisaluno.enumerated.CPORCurso;
import br.mil.eb.decex.sisaluno.enumerated.Categoria;
import br.mil.eb.decex.sisaluno.enumerated.ColegioMiltar;
import br.mil.eb.decex.sisaluno.enumerated.Escolaridade;
import br.mil.eb.decex.sisaluno.enumerated.Etnia;
import br.mil.eb.decex.sisaluno.enumerated.MatBelCurso;
import br.mil.eb.decex.sisaluno.enumerated.MedicoCurso;
import br.mil.eb.decex.sisaluno.enumerated.ODONTOCurso;
import br.mil.eb.decex.sisaluno.enumerated.OficiaisCurso;
import br.mil.eb.decex.sisaluno.enumerated.OrigemAeronautica;
import br.mil.eb.decex.sisaluno.enumerated.OrigemCivilOuMilitar;
import br.mil.eb.decex.sisaluno.enumerated.OrigemEscolar;
import br.mil.eb.decex.sisaluno.enumerated.OrigemExercito;
import br.mil.eb.decex.sisaluno.enumerated.OrigemMarinha;
import br.mil.eb.decex.sisaluno.enumerated.Paises;
import br.mil.eb.decex.sisaluno.enumerated.Periodo;
import br.mil.eb.decex.sisaluno.enumerated.QCOCurso;
import br.mil.eb.decex.sisaluno.enumerated.Religiao;
import br.mil.eb.decex.sisaluno.enumerated.RendaFamiliar;

import br.mil.eb.decex.sisaluno.validation.CPF;

@Entity
@Table(name = "complemento")
public class Complemento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "COMPLEMENTO_CODIGO_GENERATOR", sequenceName = "COMPLEMENTO_CODIGO_SEQ",  allocationSize = 1)
    @GeneratedValue(        strategy = GenerationType.SEQUENCE, generator = "COMPLEMENTO_CODIGO_GENERATOR")
    private Long codigo;
    
    @CPF
    @NotBlank(message = "O Campo cpf é obrigatório")
    private String cpf;
    
    
    @NotBlank(message = "O Campo nome completo é obrigatório")
    private String nome;
    
    @Enumerated(EnumType.STRING) 
    @NotNull(message = "O Campo religião é obrigatório")
    private Religiao religiao;
    
    @Column(name = "religiao_descr")
    private String religiaoDescr;
    
    @NotBlank(message = "O Campo sexo é obrigatório")
    private String sexo;
    
    @ManyToOne
    @JoinColumn( name = "naturalidade_codigo")
    @NotNull(message = "O Campo naturalidade é obrigatório")
    private Estado naturalidade;
    
    @Column(name = "naturalidade_nome")
    private String naturalidadeNome;
    
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
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo escolaridade é obrigatório")
    private Escolaridade escolaridade;
    
    @Column(name = "escolaridade_descr")
    private String escolaridadeDescr;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Escolha uma catgoria")
    private Categoria categoria;
    
    @Column(name = "categoria_descr")
    private String categoriaDescr;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "cfgs_curso")
    private CFGSCurso cfgsCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "cfgo_curso")
    private CFGOCurso cfgoCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "matbel_curso")
    private MatBelCurso matbelCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "oficiais_curso")
    private OficiaisCurso oficiaisCurso;
    
    @Column
    private String area;
    @Enumerated(EnumType.STRING)
    @Column(name = "cpor_curso")
    private CPORCurso cporCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "medico_curso")
    private MedicoCurso medicoCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "qco_curso")
    private QCOCurso qcoCurso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "odonto_curso")
    private ODONTOCurso odontoCurso;
    
    @Column
    private String especialidade;
          
    @Size(min = 1, message = "Selecione ao menos um Estabelecimento de ensino")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "complemento_om",				
				joinColumns = @JoinColumn(name = "codigo_complemento"),
	 			inverseJoinColumns = @JoinColumn(name = "codigo_om"))
	private Set<OrganizacaoMilitar> oms;
    
    @ManyToOne
    @JoinColumn(name = "npor_codigo")
    private Npor npor;
    
    @ManyToOne
    @JoinColumn(name = "uete_codigo")
    private Uete uete;
    
    @Column(name = "data_nascimento")
    @NotNull(message = "A data de nascimento é obrigatória")
    private LocalDate dataNascimento;
    
    @Column(name = "data_inicio_curso")
    @NotNull(message = "A data de inicio do curso é obrigatória")
    private LocalDate dataInicioCurso;
    
    @Column(name = "data_final_curso")
    private LocalDate dataFinalCurso;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo Renda familiar é obrigatório")
    @Column(name = "renda_familiar")
    private RendaFamiliar rendaFamiliar;
    
    @Column(name = "renda_familiar_descr")
    private String rendaFamiliarDescr;
    
    @Max( value = 100L, message = "A nota de conteúdo atitudinal deve ser maior que 0,01 e menor ou igual a 10,00")
    @NotNull(message = "O Campo atitudinal obrigatório")
    @Column
    private BigDecimal atitudinal;
    
    @Max(value = 10L, message = "A nota de conteúdo atitudinal lateral deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column(name = "atitudinal_lateral")
    private BigDecimal atitudinalLateral;
    
    @Max(value = 10L, message = "A nota de conteúdo atitudinal vertical deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column(name = "atitudinal_vertical")
    private BigDecimal atitudinalVertical;
    
    @Max(value = 100L, message = "A nota do TFM deve ser maior que 0,01 e menor ou igual a 10,00")
    @NotNull(message = "O Campo último TFM é obrigatório")
    @Column
    private BigDecimal tfm;
    
    @Max(value = 10L, message = "A nota do TFM 2 deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column
    private BigDecimal tfm2;
    
    @Max(value = 10L, message = "A nota do TFM 3 deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column
    private BigDecimal tfm3;
    
    @Column(name = "seg_grad_pos")
    private String segGradPos;
    
//    @Enumerated(EnumType.STRING)
//    @NotNull(message = "O Campo Situação no curso é obrigatório")
//    @Column(name = "situacao_no_curso")
//    private SituacaoNoCurso situacaoNoCurso;
    
    @Column(name = "situacao_no_curso_descr")
    private String situacaoNoCursoDescr;
    
    @NotNull( message = "Informe se é cotista ou não")
    private String cotista;
    
    @NotNull(message = "Informe se tem pai ou mãe militar")
    @Column(name = "pai_mae_militar")
    private String paiOuMaeMilitar;
    
    @ManyToOne
    @JoinColumn(name = "dados_inseridos_por")
    private Usuario inseridoPor;
    
    private Boolean ativo;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo período é obrigatório")    
    private Periodo periodo;
        
    @Size(min = 1, message = "Selecione ao menos um ano para o curso")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "complemento_ano",				
				joinColumns = @JoinColumn(name = "codigo_complemento"),
	 			inverseJoinColumns = @JoinColumn(name = "codigo_ano"))
    private Set<Ano> anos;
    
    @Size(min = 1, message = "Selecione ao menos uma situação a qual você se enquadra no curso")
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "complemento_situacao",				
				joinColumns = @JoinColumn(name = "codigo_complemento"),
	 			inverseJoinColumns = @JoinColumn(name = "codigo_situacao"))
    private Set<SituacaoNoCurso> situacoes;

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

        if (this.categoria != null) {
            this.categoriaDescr = this.categoria.getDescricao();
        }

        if (this.cfgsCurso != null) {
            this.setArea(this.cfgsCurso.getDescricao());
        }

        if (this.cfgoCurso != null) {
            this.setArea(this.cfgoCurso.getDescricao());
        }

        if (this.matbelCurso != null) {
            this.setArea(this.matbelCurso.getDescricao());
        }

        if (this.oficiaisCurso != null) {
            this.setArea(this.oficiaisCurso.getDescricao());
        }

        if (this.cporCurso != null) {
            this.setArea(this.cporCurso.getDescricao());
        }

        if (this.medicoCurso != null) {
            this.setArea(this.medicoCurso.getDescricao());
        }

        if (this.qcoCurso != null) {
            this.setArea(this.qcoCurso.getDescricao());
        }

        if (this.odontoCurso != null) {
            this.setArea(this.odontoCurso.getDescricao());
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
        return this.codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Religiao getReligiao() {
        return this.religiao;
    }

    public void setReligiao(Religiao religiao) {
        this.religiao = religiao;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Estado getNaturalidade() {
        return this.naturalidade;
    }

    public void setNaturalidade(Estado naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNaturalidadeNome() {
        return this.naturalidadeNome;
    }

    public void setNaturalidadeNome(String naturalidadeNome) {
        this.naturalidadeNome = naturalidadeNome;
    }

    public OrigemEscolar getOrigemEscolar() {
        return this.origemEscolar;
    }

    public void setOrigemEscolar(OrigemEscolar origemEscolar) {
        this.origemEscolar = origemEscolar;
    }

    public ColegioMiltar getColegioMiltar() {
        return this.colegioMiltar;
    }

    public void setColegioMiltar(ColegioMiltar colegioMiltar) {
        this.colegioMiltar = colegioMiltar;
    }

    public Etnia getEtnia() {
        return this.etnia;
    }

    public void setEtnia(Etnia etnia) {
        this.etnia = etnia;
    }

    public OrigemCivilOuMilitar getOrigemCivilOuMilitar() {
        return this.origemCivilOuMilitar;
    }

    public void setOrigemCivilOuMilitar(OrigemCivilOuMilitar origemCivilOuMilitar) {
        this.origemCivilOuMilitar = origemCivilOuMilitar;
    }

    public OrigemAeronautica getOrigemFAB() {
        return this.origemFAB;
    }

    public void setOrigemFAB(OrigemAeronautica origemFAB) {
        this.origemFAB = origemFAB;
    }

    public OrigemExercito getOrigemEB() {
        return this.origemEB;
    }

    public void setOrigemEB(OrigemExercito origemEB) {
        this.origemEB = origemEB;
    }

    public OrigemMarinha getOrigemMB() {
        return this.origemMB;
    }

    public void setOrigemMB(OrigemMarinha origemMB) {
        this.origemMB = origemMB;
    }

    public String getOrigemMilitar() {
        return this.origemMilitar;
    }

    public void setOrigemMilitar(String origemMilitar) {
        this.origemMilitar = origemMilitar;
    }

    public Paises getPaises() {
        return this.paises;
    }

    public void setPaises(Paises paises) {
        this.paises = paises;
    }

    public Escolaridade getEscolaridade() {
        return this.escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public CFGSCurso getCfgsCurso() {
        return this.cfgsCurso;
    }

    public void setCfgsCurso(CFGSCurso cfgsCurso) {
        this.cfgsCurso = cfgsCurso;
    }

    public CFGOCurso getCfgoCurso() {
        return this.cfgoCurso;
    }

    public void setCfgoCurso(CFGOCurso cfgoCurso) {
        this.cfgoCurso = cfgoCurso;
    }

    public MatBelCurso getMatbelCurso() {
        return this.matbelCurso;
    }

    public void setMatbelCurso(MatBelCurso matbelCurso) {
        this.matbelCurso = matbelCurso;
    }

    public OficiaisCurso getOficiaisCurso() {
        return this.oficiaisCurso;
    }

    public void setOficiaisCurso(OficiaisCurso oficiaisCurso) {
        this.oficiaisCurso = oficiaisCurso;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public CPORCurso getCporCurso() {
        return this.cporCurso;
    }

    public void setCporCurso(CPORCurso cporCurso) {
        this.cporCurso = cporCurso;
    }

    public MedicoCurso getMedicoCurso() {
        return this.medicoCurso;
    }

    public void setMedicoCurso(MedicoCurso medicoCurso) {
        this.medicoCurso = medicoCurso;
    }

    public QCOCurso getQcoCurso() {
        return this.qcoCurso;
    }

    public void setQcoCurso(QCOCurso qcoCurso) {
        this.qcoCurso = qcoCurso;
    }

    public ODONTOCurso getOdontoCurso() {
        return this.odontoCurso;
    }

    public void setOdontoCurso(ODONTOCurso odontoCurso) {
        this.odontoCurso = odontoCurso;
    }

    public String getEspecialidade() {
        return this.especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Uete getUete() {
        return this.uete;
    }

    public void setUete(Uete uete) {
        this.uete = uete;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataInicioCurso() {
        return this.dataInicioCurso;
    }

    public void setDataInicioCurso(LocalDate dataInicioCurso) {
        this.dataInicioCurso = dataInicioCurso;
    }

    public LocalDate getDataFinalCurso() {
        return this.dataFinalCurso;
    }

    public void setDataFinalCurso(LocalDate dataFinalCurso) {
        this.dataFinalCurso = dataFinalCurso;
    }      

	public Set<OrganizacaoMilitar> getOms() {
		return oms;
	}

	public void setOms(Set<OrganizacaoMilitar> oms) {
		this.oms = oms;
	}

	public Npor getNpor() {
        return this.npor;
    }

    public void setNpor(Npor npor) {
        this.npor = npor;
    }

    public RendaFamiliar getRendaFamiliar() {
        return this.rendaFamiliar;
    }

    public void setRendaFamiliar(RendaFamiliar rendaFamiliar) {
        this.rendaFamiliar = rendaFamiliar;
    }

    public BigDecimal getAtitudinal() {
        return this.atitudinal;
    }

    public void setAtitudinal(BigDecimal atitudinal) {
        this.atitudinal = atitudinal;
    }

    public BigDecimal getAtitudinalLateral() {
        return this.atitudinalLateral;
    }

    public void setAtitudinalLateral(BigDecimal atitudinalLateral) {
        this.atitudinalLateral = atitudinalLateral;
    }

    public BigDecimal getAtitudinalVertical() {
        return this.atitudinalVertical;
    }

    public void setAtitudinalVertical(BigDecimal atitudinalVertical) {
        this.atitudinalVertical = atitudinalVertical;
    }

    public BigDecimal getTfm() {
        return this.tfm;
    }

    public void setTfm(BigDecimal tfm) {
        this.tfm = tfm;
    }

    public BigDecimal getTfm2() {
        return this.tfm2;
    }

    public void setTfm2(BigDecimal tfm2) {
        this.tfm2 = tfm2;
    }

    public BigDecimal getTfm3() {
        return this.tfm3;
    }

    public void setTfm3(BigDecimal tfm3) {
        this.tfm3 = tfm3;
    }

    public String getSegGradPos() {
        return this.segGradPos;
    }

    public void setSegGradPos(String segGradPos) {
        this.segGradPos = segGradPos;
    }


    public String getCotista() {
        return this.cotista;
    }

    public void setCotista(String cotista) {
        this.cotista = cotista;
    }

    public String getPaiOuMaeMilitar() {
        return this.paiOuMaeMilitar;
    }

    public void setPaiOuMaeMilitar(String paiOuMaeMilitar) {
        this.paiOuMaeMilitar = paiOuMaeMilitar;
    }

    public Usuario getInseridoPor() {
        return this.inseridoPor;
    }

    public void setInseridoPor(Usuario inseridoPor) {
        this.inseridoPor = inseridoPor;
    }

    public Boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }    

    public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}	

	public Set<Ano> getAnos() {
		return anos;
	}

	public void setAnos(Set<Ano> anos) {
		this.anos = anos;
	}

	public String getReligiaoDescr() {
        return this.religiaoDescr;
    }

    public void setReligiaoDescr(String religiaoDescr) {
        this.religiaoDescr = religiaoDescr;
    }

    public String getOrigemEscolarDescr() {
        return this.origemEscolarDescr;
    }

    public void setOrigemEscolarDescr(String origemEscolarDescr) {
        this.origemEscolarDescr = origemEscolarDescr;
    }

    public String getColegioMiltarDescr() {
        return this.colegioMiltarDescr;
    }

    public void setColegioMiltarDescr(String colegioMiltarDescr) {
        this.colegioMiltarDescr = colegioMiltarDescr;
    }

    public String getEtniaDescr() {
        return this.etniaDescr;
    }

    public void setEtniaDescr(String etniaDescr) {
        this.etniaDescr = etniaDescr;
    }

    public String getOrigemCivilOuMilitarDescr() {
        return this.origemCivilOuMilitarDescr;
    }

    public void setOrigemCivilOuMilitarDescr(String origemCivilOuMilitarDescr) {
        this.origemCivilOuMilitarDescr = origemCivilOuMilitarDescr;
    }

    public String getPaisesDescr() {
        return this.paisesDescr;
    }

    public void setPaisesDescr(String paisesDescr) {
        this.paisesDescr = paisesDescr;
    }

    public String getEscolaridadeDescr() {
        return this.escolaridadeDescr;
    }

    public void setEscolaridadeDescr(String escolaridadeDescr) {
        this.escolaridadeDescr = escolaridadeDescr;
    }

    public String getCategoriaDescr() {
        return this.categoriaDescr;
    }

    public void setCategoriaDescr(String categoriaDescr) {
        this.categoriaDescr = categoriaDescr;
    }

    public String getRendaFamiliarDescr() {
        return this.rendaFamiliarDescr;
    }

    public void setRendaFamiliarDescr(String rendaFamiliarDescr) {
        this.rendaFamiliarDescr = rendaFamiliarDescr;
    }

    public String getSituacaoNoCursoDescr() {
        return this.situacaoNoCursoDescr;
    }

    public void setSituacaoNoCursoDescr(String situacaoNoCursoDescr) {
        this.situacaoNoCursoDescr = situacaoNoCursoDescr;
    }    

    public Set<SituacaoNoCurso> getSituacoes() {
		return situacoes;
	}

	public void setSituacoes(Set<SituacaoNoCurso> situacoes) {
		this.situacoes = situacoes;
	}

	public boolean isNovo() {
        return this.codigo == null;
    }

    public boolean isEdicao() {
        return this.codigo != null;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.codigo, this.cpf});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Complemento other = (Complemento)obj;
            return Objects.equals(this.codigo, other.codigo) && Objects.equals(this.cpf, other.cpf);
        }
    }
}
