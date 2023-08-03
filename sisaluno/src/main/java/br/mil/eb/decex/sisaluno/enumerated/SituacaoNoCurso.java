package br.mil.eb.decex.sisaluno.enumerated;

public enum SituacaoNoCurso {
	
	MATRICULADO("Matriculado"),
	MATRICULADO_ORDEM_JUDICIAL("Matriculado Por Ordem Judicial"),
	TRANCAMENTO("Trancamento"),
	DESLIGAMENTO("Desligamento"),
	ÓBITO("Óbito"),
	APROVADO("Aprovado"),
	REPROVADO("Reprovado");
	
	private String descricao;
	
	SituacaoNoCurso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
