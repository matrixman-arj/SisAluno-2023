package br.mil.eb.decex.sisaluno.enumerated;

public enum Escolaridade {
	
	MEDIO("Ensino Médio"),
	SUPERIOR_CURSANDO("Superior - Cursando"),
	SUPERIOR_COMPLETO("Superior - Completo"),
	POS_GRADUADO("Pós-graduado");
	
	private String descricao;
	
	Escolaridade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
