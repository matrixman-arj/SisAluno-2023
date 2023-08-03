package br.mil.eb.decex.sisaluno.enumerated;

public enum CPORCurso {
	// CPOR (oficiais da reserva) sobre a área do curso superior que cursa
	
	C_EXAT_TERRA("Ciências Exatas e da Terra"),
	C_BIO("Ciências Biológicas"),
	ENG("Engenharias"),
	C_SAU("Ciências da Saúde"),
	C_AGRA("Ciências Agrárias"),
	C_HUM("Ciências Humanas"),
	LING_LETR_ART("Linguística, Letras e Artes"),
	C_SOC_APL("Ciências Sociais Aplicadas");
	
	
	private String descricao;
	
	CPORCurso(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
