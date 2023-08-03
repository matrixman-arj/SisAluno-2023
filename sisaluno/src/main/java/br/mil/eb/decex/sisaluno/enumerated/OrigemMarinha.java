package br.mil.eb.decex.sisaluno.enumerated;

public enum OrigemMarinha {
	
	MB_OTT("Oficial Técnico Temporário da Marinha do Brasil"),
	MB_STT("Sargento Técnico Temporário da Marinha do Brasil"),
	MB_OF_CARREIRA("Oficial de Carreira da Marinha do Brasil"),
	MB_ST_SGT_CARREIRA("Subtenente ou Sargento de Carreira da Marinha do Brasil"),
	MB_CB_SD("Cabo ou Soldado da Marinha do Brasil");	
		
	private String descricao;
	
	OrigemMarinha(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
