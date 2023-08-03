package br.mil.eb.decex.sisaluno.enumerated;

public enum Periodo {
	// Período em que o aluno está realizando o curso.
	
	Pri_ano("1º Ano"),
	Seg_ano("2º Ano"),
	Ter_ano("3º Ano"),
	Quar_ano("4º Ano"),
	Quin_ano("5º Ano"),;
	
	
	private String descricao;
	
	Periodo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
