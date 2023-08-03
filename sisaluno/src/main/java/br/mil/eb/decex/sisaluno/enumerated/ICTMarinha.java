package br.mil.eb.decex.sisaluno.enumerated;

public enum ICTMarinha {
	//Instituições Científicas, Tecnológicas e de Inovação (ICT) da Marinha
	
	 DGDNTM("Diretoria-Geral de Desenvolvimento Nuclear e Tecnológico da Marinha"),
	 IPqM("Instituto de Pesquisas da Marinha"),
	 HNMD_IPB("Hospital Naval Marcílio Dias / Instituto de Pesquisas Biomédicas"),
	 CASNAV("Centro de Análises de Sistemas Navais"),
	 EGN("Escola de Guerra Naval"),
	 IEAPM("Instituto de Estudos do Mar Almirante Paulo Moreira"),
	 CTMSP("Centro Tecnológico da Marinha em São Paulo"),
	 CHM("Centro de Hidrografia da Marinha"),
	 LFM("Laboratório Farmacêutico da Marinha"),
	 CTecCFN("Centro Tecnológico do Corpo de Fuzileiros Navais");

		
	private String descricao;
	
	ICTMarinha(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
