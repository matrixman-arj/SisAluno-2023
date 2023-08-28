package br.mil.eb.decex.sisaluno.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.mil.eb.decex.sisaluno.enumerated.Ano;
import br.mil.eb.decex.sisaluno.enumerated.Periodo;
import br.mil.eb.decex.sisaluno.enumerated.SituacaoNoCurso;

@Entity
@Table(name = "matricula", schema = "ensino")
public class Matricula {
	
	@Id
    @SequenceGenerator(name = "ENSINO.MATRICULA_CODIGO_GENERATOR", sequenceName = "ENSINO.MATRICULA_CODIGO_SEQ",  allocationSize = 1)
    @GeneratedValue(        strategy = GenerationType.SEQUENCE, generator = "ENSINO.MATRICULA_CODIGO_GENERATOR")
    private Long codigo;
	
	@Column(name = "numero_matricula")
	private String numeroMatricula;
	
	@ManyToOne
	@JoinColumn(name = "codigo_aluno")
	@NotNull(message = "É obrigatório selecionar um aluno para a matricula")
	private Aluno aluno;
	
	@Column(name = "cpf_aluno")
	private String cpfAluno;
	
	@ManyToOne
	@JoinColumn(name = "codigo_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "codigo_curso")
	private Curso curso; 
	
	@Size(min = 1, message = "Selecione ao menos um curso")
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ensino.item_matricula", joinColumns = @JoinColumn(name = "codigo_matricula")
	 						, inverseJoinColumns = @JoinColumn(name = "codigo_curso"))
	private List<ItemMatricula> itens = new ArrayList<>();
	
	@OneToOne
	@JoinColumn(name = "codigo_om")
	private OrganizacaoMilitar om;
	
	@Column(name = "data_criacao")   
    private LocalDate dataCriacao = LocalDate.now();
		
	@Enumerated(EnumType.STRING)
	@Column(name = "ano_letivo")	
	private Ano anoLetivo;
	
	@Column(name = "ano_letivo_descr")
	private String anoLetivoDescr;	
    
    @Column(name = "data_inicio_curso")    
    private LocalDate dataInicioCurso;
    
    @Column(name = "data_final_curso")    
    private LocalDate dataFinalCurso;
    
    @Max( value = 10L, message = "A nota de conteúdo atitudinal deve ser maior que 0,01 e menor ou igual a 10,00")    
    @Column
    private BigDecimal atitudinal;
    
    @Max(value = 10L, message = "A nota de conteúdo atitudinal lateral deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column(name = "atitudinal_lateral")
    private BigDecimal atitudinalLateral;
    
    @Max(value = 10L, message = "A nota de conteúdo atitudinal vertical deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column(name = "atitudinal_vertical")
    private BigDecimal atitudinalVertical;
    
    @Max(value = 10L, message = "A nota do TFM deve ser maior que 0,01 e menor ou igual a 10,00")   
    @Column
    private BigDecimal tfm;
    
    @Max(value = 10L, message = "A nota do TFM 2 deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column
    private BigDecimal tfm2;
    
    @Max(value = 10L, message = "A nota do TFM 3 deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column
    private BigDecimal tfm3;    
    
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao_no_curso")
    private SituacaoNoCurso situacao = SituacaoNoCurso.MATRICULADO;
    
    @Column(name = "situacao_no_curso_descr")
    private String situacaoNoCursoDescr;
    
    @Enumerated(EnumType.STRING)
   
    private Periodo periodo;
    
//    @OneToMany(mappedBy = "matricula", cascade = CascadeType.ALL, orphanRemoval = true)    
//    private List<ItemMatricula> itens = new ArrayList<>();
    
    private String uuid;
    
    @Column(name = "total_tfm")
    private BigDecimal totalTFM = BigDecimal.ZERO;
    
    @Column(name = "total_atitudinal")
    private BigDecimal totalAtitudinal = BigDecimal.ZERO;

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNumeroMatricula() {
		return numeroMatricula;
	}
	public void setNumeroMatricula(String numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}	
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}	
	
	public String getCpfAluno() {
		return cpfAluno;
	}
	public void setCpfAluno(String cpfAluno) {
		this.cpfAluno = cpfAluno;
	}
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}		
	
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	public OrganizacaoMilitar getOm() {
		return om;
	}	
	public void setOm(OrganizacaoMilitar om) {
		this.om = om;
	}	
	
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public SituacaoNoCurso getSituacao() {
		return situacao;
	}
	public void setSituacao(SituacaoNoCurso situacao) {
		this.situacao = situacao;
	}	
	
	public Ano getAnoLetivo() {
		return anoLetivo;
	}
	public void setAnoLetivo(Ano anoLetivo) {
		this.anoLetivo = anoLetivo;
	}
	
	public String getAnoLetivoDescr() {
		return anoLetivoDescr;
	}
	public void setAnoLetivoDescr(String anoLetivoDescr) {
		this.anoLetivoDescr = anoLetivoDescr;
	}
	
	public LocalDate getDataInicioCurso() {
		return dataInicioCurso;
	}
	public void setDataInicioCurso(LocalDate dataInicioCurso) {
		this.dataInicioCurso = dataInicioCurso;
	}

	public LocalDate getDataFinalCurso() {
		return dataFinalCurso;
	}

	public void setDataFinalCurso(LocalDate dataFinalCurso) {
		this.dataFinalCurso = dataFinalCurso;
	}

	public BigDecimal getAtitudinal() {
		return atitudinal;
	}

	public void setAtitudinal(BigDecimal atitudinal) {
		this.atitudinal = atitudinal;
	}

	public BigDecimal getAtitudinalLateral() {
		return atitudinalLateral;
	}

	public void setAtitudinalLateral(BigDecimal atitudinalLateral) {
		this.atitudinalLateral = atitudinalLateral;
	}

	public BigDecimal getAtitudinalVertical() {
		return atitudinalVertical;
	}

	public void setAtitudinalVertical(BigDecimal atitudinalVertical) {
		this.atitudinalVertical = atitudinalVertical;
	}

	public BigDecimal getTfm() {
		return tfm;
	}

	public void setTfm(BigDecimal tfm) {
		this.tfm = tfm;
	}

	public BigDecimal getTfm2() {
		return tfm2;
	}

	public void setTfm2(BigDecimal tfm2) {
		this.tfm2 = tfm2;
	}

	public BigDecimal getTfm3() {
		return tfm3;
	}
	public void setTfm3(BigDecimal tfm3) {
		this.tfm3 = tfm3;
	}

	public String getSituacaoNoCursoDescr() {
		return situacaoNoCursoDescr;
	}
	public void setSituacaoNoCursoDescr(String situacaoNoCursoDescr) {
		this.situacaoNoCursoDescr = situacaoNoCursoDescr;
	}

	public Periodo getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	public List<ItemMatricula> getItens() {
		return itens;
	}
	public void setItens(List<ItemMatricula> itens) {
		this.itens = itens;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}	
	
	public BigDecimal getTotalTFM() {
		return totalTFM;
	}
	public void setTotalTFM(BigDecimal totalTFM) {
		this.totalTFM = totalTFM;
	}
	public BigDecimal getTotalAtitudinal() {
		return totalAtitudinal;
	}
	public void setTotalAtitudinal(BigDecimal totalAtitudinal) {
		this.totalAtitudinal = totalAtitudinal;
	}
	
	
	
	@PrePersist
    private void prePersist() {
		
		
		if(isNova()) {
			
			if (this.situacao != null) {
				this.situacaoNoCursoDescr = this.situacao.getDescricao();
			}
			
			this.dataCriacao = LocalDate.now();
			
			if(this.anoLetivo != null) {
				this.anoLetivoDescr = this.anoLetivo.getDescricao();
			}
			
			if(this.aluno != null) {
				this.cpfAluno = this.aluno.getCpf().replaceAll("\\.|-", "");
			}
			
			if(this.anoLetivo != null) {
				Random gerador = new Random();
				setNumeroMatricula(this.anoLetivoDescr + "-" +  gerador.nextInt(900)*10);
			}
			System.out.println("Matricula: " + getNumeroMatricula());
		}
	}
		
		
	
	public boolean isNova() {
		return codigo == null;
	}
	
	public boolean isEdicao() {
		return codigo != null;
	}
	
	public void adicionarItens(List<ItemMatricula> itens) {
		this.itens = itens;
		this.itens.forEach(i -> i.setMatricula(this));
		
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
		Matricula other = (Matricula) obj;
		return Objects.equals(codigo, other.codigo);
	}
	
		
}
