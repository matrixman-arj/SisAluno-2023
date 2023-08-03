package br.mil.eb.decex.sisaluno.dto;

import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.enumerated.Linha;
import br.mil.eb.decex.sisaluno.enumerated.Modalidade;
import br.mil.eb.decex.sisaluno.enumerated.Universo;
import br.mil.eb.decex.sisaluno.enumerated.Vinculo;


public class CursoDTO{
		
    private Long codigo;
    private String sku;
    private String modalidade;	
    private String universo;   
    private String descr;    
    private String linha;    
    private String vinculo;
    private String foto;
	
    
    public CursoDTO(Long codigo, String sku, Modalidade modalidade, Universo universo, String descr, Linha linha, Vinculo vinculo, String foto) {
		
		this.codigo = codigo;
		this.sku = sku;
		this.modalidade = modalidade.getDescricao();
		this.universo = universo.getDescricao();
		this.descr = descr;
		this.linha = linha.getDescricao();
		this.vinculo = vinculo.getDescricao();	
		this.foto = StringUtils.isEmpty(foto)? "Posto-oficial-praça-mock.png" : foto;	
	}

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getUniverso() {
		return universo;
	}
	public void setUniverso(String universo) {
		this.universo = universo;
	}

	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getLinha() {
		return linha;
	}
	public void setLinha(String linha) {
		this.linha = linha;
	}

	public String getVinculo() {
		return vinculo;
	}
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}    

}
