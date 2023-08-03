package br.mil.eb.decex.sisaluno.enumerated;

public enum OrigemEscolar {
	
	ESCOLA_PARTICULAR("Escola particular"),
	ESCOLA_PUBLICA("Escola pública"),
	COLEGIO_MILITAR("Colégio Militar");
	
	
	private String descricao;
	
	OrigemEscolar(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
