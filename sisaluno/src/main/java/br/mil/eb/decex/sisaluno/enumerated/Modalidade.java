package br.mil.eb.decex.sisaluno.enumerated;

public enum Modalidade {
	// Tipo de formação que o aluno está realizando.
	
	FORMACAO("Formação"),
	GRADUACAO("Graduação"),
	FORM_GRAD("Formação e graduação"),
	APERF("Aperfeiçoamento"),
	ESPEC("Especialização"),
	EXTEN("Extensão"),
	ALT_EST_MIL("Alto Estudos Militares"),
	POL_ESTR_ALT_ADM("Política, Estratégia E Alta Administração"),
	PREPARACAO("Preparação"),
	ESTAGIOS("Estágios"),
	POS_GRADU("Pós-Graduação"),
	MESTRADO("Mestrado"),
	DOUTORADO("Doutorado"),
	EXTEN_UNIVER("Extensão Universitária"),
	ENS_BASI("Ensino Básico"),
	TEC("Técnico"),
	NIV_TEC("Nível Técnico"),
	EDUC_PROF_NIV_TEC("Educação Profissional – Nível Técnico"),
	TECNOLO("Tecnológico"),
	OUTROS("Outros Cursos");
	
	
	private String descricao;
	
	Modalidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
