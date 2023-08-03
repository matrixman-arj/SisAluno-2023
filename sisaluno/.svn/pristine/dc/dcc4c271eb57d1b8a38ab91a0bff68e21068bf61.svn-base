package br.mil.eb.decex.sisaluno.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import br.mil.eb.decex.sisaluno.enumerated.RendaFamiliar;
import br.mil.eb.decex.sisaluno.validation.CPF;

@Embeddable
public class CursoOmId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "codigo_curso")
	private Curso curso;
	
	@ManyToOne
	@JoinColumn(name = "codigo_om")
	private OrganizacaoMilitar om;
	
	@ManyToOne
	@JoinColumn(name = "codigo_ano")
	private Ano codigoAno;
	
	@CPF
    @NotBlank(message = "O Campo cpf é obrigatório")
    private String cpf;
	
	@Column(name = "data_inicio_curso")
    @NotNull(message = "A data de inicio do curso é obrigatória")
    private LocalDate dataInicioCurso;
    
    @Column(name = "data_final_curso")
    private LocalDate dataFinalCurso;
    
//   
    
    @Column(name = "situacao_no_curso_descr")
    private String situacaoNoCursoDescr;
    
    @Column(name = "seg_grad_pos")
    private String segGradPos;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo Renda familiar é obrigatório")
    @Column(name = "renda_familiar")
    private RendaFamiliar rendaFamiliar;
    
    @Column(name = "renda_familiar_descr")
    private String rendaFamiliarDescr;
    
    @Max( value = 10L, message = "A nota de conteúdo atitudinal deve ser maior que 0,01 e menor ou igual a 10,00")
    @NotNull(message = "O Campo atitudinal obrigatório")
    @Column
    private BigDecimal atitudinal;
    
    @Max(value = 10L, message = "A nota de conteúdo atitudinal lateral deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column(name = "atitudinal_lateral")
    private BigDecimal atitudinalLateral;
    
    @Max(value = 10L, message = "A nota de conteúdo atitudinal vertical deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column(name = "atitudinal_vertical")
    private BigDecimal atitudinalVertical;
    
    @Max(value = 10L, message = "A nota do TFM deve ser maior que 0,01 e menor ou igual a 10,00")
    @NotNull(message = "O Campo último TFM é obrigatório")
    @Column
    private BigDecimal tfm;
    
    @Max(value = 10L, message = "A nota do TFM 2 deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column
    private BigDecimal tfm2;
    
    @Max(value = 10L, message = "A nota do TFM 3 deve ser maior que 0,01 e menor ou igual a 10,00")
    @Column
    private BigDecimal tfm3;
    
    private String ano;

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

	public Ano getCodigoAno() {
		return codigoAno;
	}

	public void setCodigoAno(Ano codigoAno) {
		this.codigoAno = codigoAno;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	

	public String getSituacaoNoCursoDescr() {
		return situacaoNoCursoDescr;
	}

	public void setSituacaoNoCursoDescr(String situacaoNoCursoDescr) {
		this.situacaoNoCursoDescr = situacaoNoCursoDescr;
	}

	public String getSegGradPos() {
		return segGradPos;
	}

	public void setSegGradPos(String segGradPos) {
		this.segGradPos = segGradPos;
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

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoAno, curso, om);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CursoOmId other = (CursoOmId) obj;
		return Objects.equals(codigoAno, other.codigoAno) && Objects.equals(curso, other.curso)
				&& Objects.equals(om, other.om);
	}

	
	
}
