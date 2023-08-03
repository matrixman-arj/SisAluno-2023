package br.mil.eb.decex.sisaluno.enumerated;

public enum Categoria {
	// Tipo de formação que o aluno está realizando.
	
	FORM_OF("Formação de Oficiais"),
	OF_PRACA("Oficiais e Praças"),
	FORM_GRAD_PCA("Formação e graduação de Praças");
	
	
	private String descricao;
	
	Categoria(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
