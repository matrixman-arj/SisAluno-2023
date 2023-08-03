package br.mil.eb.decex.sisaluno.enumerated;

public enum OrigemExercito {
	
	EB_OTT("Oficial Técnico Temporário do Exército Brasileiro"),
	EB_STT("Sargento Técnico Temporário do Exército Brasileiro"),
	EB_OF_CARREIRA("Oficial de Carreira do Exército Brasileiro"),
	EB_ST_SGT_CARREIRA("Subtenente ou Sargento de Carreira do Exército Brasileiro"),
	EB_CB_SD("Cabo ou Soldado do Exército Brasileiro");
		
	
	private String descricao;
	
	OrigemExercito(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
