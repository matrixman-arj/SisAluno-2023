package br.mil.eb.decex.sisaluno.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.mil.eb.decex.sisaluno.enumerated.Periodo;
import br.mil.eb.decex.sisaluno.enumerated.SituacaoNoCurso;

@Entity
@Table(name = "matricula", schema = "ensino")
public class Matricula {
	
	@Id
    @SequenceGenerator(name = "ENSINO.MATRICULA_CODIGO_GENERATOR", sequenceName = "ENSINO.MATRICULA_CODIGO_SEQ",  allocationSize = 1)
    @GeneratedValue(        strategy = GenerationType.SEQUENCE, generator = "ENSINO.MATRICULA_CODIGO_GENERATOR")
    private Long codigo;
		
	@DateTimeFormat(pattern = "yyyy")
	private String ano;	
    
    @Column(name = "data_inicio_curso")
    @NotNull(message = "A data de inicio do curso é obrigatória")
    private LocalDate dataInicioCurso;
    
    @Column(name = "data_final_curso")
    private LocalDate dataFinalCurso;
    
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
    
//    private BigDecimal notaTfm;
    
    @Enumerated(EnumType.STRING)
    private SituacaoNoCurso situacao = SituacaoNoCurso.MATRICULADO;
    
    @Column(name = "situacao_no_curso_descr")
    private String situacaoNoCursoDescr;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo período é obrigatório")    
    private Periodo periodo;

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
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
	

//	public void setNotaTfm(BigDecimal notaTfm) {
//		this.notaTfm = notaTfm;
//	}

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
	
	public BigDecimal getNotaTfm() {
		BigDecimal nota = getTfm().add(tfm2).add(tfm3);
		BigDecimal d = new BigDecimal("3");
		nota = nota.divide(d);
		return getNotaTfm();
	}
	
	@PrePersist
    private void prePersist() {
		
		if (this.situacao != null) {
            this.situacaoNoCursoDescr = this.situacao.getDescricao();
        }        
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
