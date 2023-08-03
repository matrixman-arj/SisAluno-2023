package br.mil.eb.decex.sisaluno.enumerated;

public enum ColegioMiltar {
	CMBEL("Colégio Militar de Belém"),
	CMBH("Colégio Militar de Belo Horizonte"),
	CMB("Colégio Militar de Brasília"),
	CMCG("Colégio Militar de Campo Grande"),
	CMC("Colégio Militar de Curitiba"),
	CMF("Colégio Militar de Fortaleza"),
	CMJF("Colégio Militar de Juiz de Fora"),
	CMM("Colégio Militar de Manaus"),
	CMPA("Colégio Militar de Porto Alegre"),
	CMR("Colégio Militar do Recife"),
	CMRJ("Colégio Militar do Rio de Janeiro"),
	CMS("Colégio Militar de Salvador"),
	CMSM("Colégio Militar de Santa Maria"),
	CMSP("Colégio Militar de São Paulo"),	
	F_O("Fundação Osório");
	
	private String descricao;
	
	ColegioMiltar(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
