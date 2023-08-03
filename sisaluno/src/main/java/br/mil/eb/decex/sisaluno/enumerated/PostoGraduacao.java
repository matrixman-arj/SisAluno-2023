package br.mil.eb.decex.sisaluno.enumerated;

/**
 * Enumerado dos Postos e Graduações das 
 * Forças Armadas
 * 
 * @author Vanilton Gomes dos Santos - 2º Sgt QE
 * @version 1.0
 */
public enum PostoGraduacao {

	GEN_EXERCITO("Gen Ex"),
	GEN_DIVISAO("Gen Div"),
	GEN_BRIGADA("Gen Bda"),
	CORONEL("Cel"),
	TEN_CORONEL("Ten Cel"),
	MAJOR("Maj"),
	CAPITAO("Cap"),
	PRI_TENENTE("1º Ten"),
	SEG_TENENTE("2º Ten"),
	ASP("Asp"),
	CADETE("Cadete"),
	SUBTENENTE("S Ten"),
	PRI_SARGENTO("1º SGT"),
	SEG_SARGENTO("2º SGT"),
	TER_SARGENTO("3º SGT"),
	CABO("Cabo"),
	T_MOR("Taifeiro Mor"),
	T1("Taifeiro de 1ª classe"),
	T2("Taifeiro de 2ª classe"),
	SOLDADO("Soldado"),
	ALUNO_CM("Aluno do Colégio Militar"),
	ALUNO("Aluno de Organização Militar de Ensino"),
	SVD_CIV("Servidor Civil");
	
	private String descricao;
	
	PostoGraduacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}