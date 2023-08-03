package br.mil.eb.decex.sisaluno.enumerated;

public enum MatBelCurso {
	// Cursos a serem realizados para a formação em Material Bélico.
	
	MEC_OPE("Mecânico Operador"),
	MNT_ARM("Manutenção de Armamento"),
	MNT_VTR_AUTO("Manutenção de Viatura Automóvel"),
	MNT_VTR_BL("Manutenção de Viatura Blindada"),
	MNT_COM("Manutenção de Comunicações");
	
	
	private String descricao;
	
	MatBelCurso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
