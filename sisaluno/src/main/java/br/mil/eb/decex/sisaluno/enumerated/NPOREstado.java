package br.mil.eb.decex.sisaluno.enumerated;

public enum NPOREstado {
	//NPOR's existentes e seus estados.
			
	_38º_BI("38º Batalhão de infantaria - Vila Velha (ES)"),
	_28º_BIL("28º Batalhão de infantaria Leve - Campinas (SP)"),
	_2º_BIL("2º Batalhão de infantaria Leve - São Vicente (SP)"),
	_7º_BIB	 ("7º Batalhão de infantaria Blindada - Santa Cruz do Sul (RS)"),
	_1º_B_COM("7º Batalhão de Comunicações - Santo Ângelo (RS)"),
	_19º_RCMEC("19º Regimento de Cavalaria Mecanizado - Santa Rosa(RS)"),
	_3º_GAAAe("3º Grupo de Artilharia Antiaérea - Caxias do Sul (RS)"),
	_3º_GAC_Ap("3º Grupo de Artilharia de Campanha Autopropulsado - Santa Maria (RS)"),
	_3º_RC_MEC	("3º Regimento de Cavalaria Mecanizado - Bagé (RS)"),
	_9º_BIMtz("9° Batalhão de Infantaria Motorizado - Pelotas (RS)"),
	Pq_R_Mnt_3("Parque Regional de Manutenção da 3ª Região Militar - Santa Maria (RS)"),
	_12º_B_E_Cmb_Bld("12º Batalhão de Engenharia de Combate Blindado - Alegrete (RS)"),
	_4º_GAAAe("4º Grupo de Artilharia Antiaérea - Sete Lagoas (MG)"),
	_4º_GAC("4º Grupo de Artilharia de Campanha - Juiz de Fora (MG)"),
	_4º_B_E_Cmb("4º Batalhão de Engenharia de Combate - Itajubá (MG) "),
	_13º_BIB("13º Batalhão de infantaria Blindada - Ponta Grossa (PR)"),
	_20º_BIB("20º Batalhão de infantaria Blindada - Curitiba (PR)"),
	_23º_BI("23º Batalhão de infantaria - Blumenau (SC)"),
	_28º_GAC("Grupo de Artilharia de Campanha - Criciúma (SC)"),
	_33º_BI_Mec("Batalhão de infantaria Mecanizada - Cascavel (PR)"),
	_5º_BE_Cmb_Bld("5º Batalhão de Engenharia de Combate Blindado - Porto União (SC)"),
	_5º_B_Log("5º Batalhão Logístico - Curitiba (PR)"),
	_5º_GAC_AP("5º Grupo de Artilharia de Campanha Autopropulsado - Curitiba (PR)"),
	_5º_RCC("5º Regimento de Carros de Combate - Rio Negro (PR)"),
	_62º_BI("62º Batalhão de Infantaria - Joinville (SC)"),
	_63º_BI("63º Batalhão de Infantaria - Florianópolis (SC)"),
	_19º_BC("19º Batalhão de Caçadores - Salvador (BA)"),
	_28º_BC("28º Batalhão de Caçadores - Aracaju (SE)"),
	_15º_BIMtz("15º Batalhão de Infantaria Motorizado - João Pessoa (PB)"),
	_16º_BIMtz("16º Batalhão de Infantaria Motorizado - Natal (RN)"),
	_16º_RC_Mec("16º Regimento de Cavalaria Mecanizado - Bayeux (PB)"),
	_59º_BIMtz("59º Batalhão de Infantaria Motorizado - Maceió (AL) "),
	_72º_BIMtz("72º Batalhão de Infantaria Motorizado - Petrolina (PE)"),
	_2º_BIS("2º Batalhão de Infantaria de Selva - Belém (PA)"),
	_24º_BIL("24º Batalhão de Infantaria Leve - São Luís (MA)"),
	_18º_GAC("18º Grupo de Artilharia de Campanha - Rondonópolis (MT)"),
	_20º_RCB("20º Regimento de Cavalaria Blindado - Campo Grande (MS)"),
	_44º_BIMtz("44º Batalhão de Infantaria Motorizado - Cuiabá (MT)"),
	_23º_BC("23º Batalhão de Caçadores - Fortaleza (CE)"),
	_25º_BC("25º Batalhão de Caçadores - Teresina (PI)"),
	_2º_BE_Cnst("2º Batalhão de Engenharia de Construção - Teresina (PI)"),
	_32º_GAC("32º Grupo de Artilharia de Campanha - Brasília (DF)"),
	_11º_BE_Cnst("11º Batalhão de Engenharia de Construção - Araguari (MG)"),
	BGP("Batalhão da Guarda Presidencial - Brasília (DF)"),
	_36º_BIMtz("36º Batalhão de Infantaria Motorizado - Uberlândia (MG)"),
	_1º_BIS("1º Batalhão de Infantaria de Selva - Manaus (AM)"),
	_12º_B_Sup("12º Batalhão de Suprimento - Manaus (AM)");

	
	
	private String descricao;
	
	NPOREstado(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
