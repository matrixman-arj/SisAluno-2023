package br.mil.eb.decex.sisaluno.enumerated;

public enum OrigemAeronautica {
	
	FAB_OTT("Oficial Técnico Temporário da Força Aérea Brasileira"),
	FAB_STT("Sargento Técnico Temporário da Força Aérea Brasileira"),
	FAB_OF_CARREIRA("Oficial de Carreira da Força Aérea Brasileira"),
	FAB_ST_SGT_CARREIRA("Subtenente ou Sargento de Carreira da Força Aérea Brasileira"),
	FAB_CB_SD("Cabo ou Soldado da Força Aérea Brasileira");	
		
	private String descricao;
	
	OrigemAeronautica(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
