package br.mil.eb.decex.sisaluno.enumerated;

public enum CFGSCurso {
	// Cursos a serem realizados para a formação e graduação de Sargentos.
	
	PERIODO_BASICO("Período Básico"),
	ARTILHARIA("Artilharia"),
	AVIACAO("Aviação"),
	CAVALARIA("Cavalaria"),
	COMUNICACOES("Comunicações"),
	ENGENHARIA("Engenharia"),
	INFANTARIA("Infantaria"),
	INTENDENCIA("Intendência"),
	MATERIAL_BELICO("Material Bélico"),
	MUSICA("Música"),
	SAUDE("Saúde"),
	TOPOGRAFIA("Topografia");
	
	
	private String descricao;
	
	CFGSCurso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
