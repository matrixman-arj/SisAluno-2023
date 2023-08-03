function verificaEspSgt(value){
	
	var divMATBEL = document.getElementById("divMATBEL");
	var divUETE = document.getElementById("divUETE");
	
		
	if (value == '') {
		console.log(value);
		divMATBEL.hidden = true;
		divUETE.hidden = true;
		
	}else if (value == 'PERIODO_BASICO') {
		divUETE.hidden = false;
		divMATBEL.hidden = true;
	
		
	}else if (value == 'MATERIAL_BELICO') {
		divMATBEL.hidden = false;
		divUETE.hidden = true;
		
	
	}else if (value == 'ARTILHARIA') {
		divMATBEL.hidden = true;
		divUETE.hidden = true;
		
	}else if (value == 'AVIACAO') {
		divMATBEL.hidden = true;
		divUETE.hidden = true;
		
	}else if (value == 'CAVALARIA') {
		divMATBEL.hidden = true;
		divUETE.hidden = true;
		
	}else if (value == 'COMUNICACOES') {
		divMATBEL.hidden = true;
		divUETE.hidden = true;
		
	}else if (value == 'ENGENHARIAR') {
		divMATBEL.hidden = true;
		divUETE.hidden = true;
		
	}else if (value == 'INFANTARIA') {
		divMATBEL.hidden = true;
		divUETE.hidden = true;
		
	}else if (value == 'INTENDENCIA') {
		divMATBEL.hidden = true;
		divUETE.hidden = true;
		
	}else if (value == 'MUSICA') {
		divMATBEL.hidden = true;
		divUETE.hidden = true;
		
	}else if (value == 'SAUDE') {
		divMATBEL.hidden = true;
		divUETE.hidden = true;
		
	}else if (value == 'TOPOGRAFIA') {
		divMATBEL.hidden = true;
		divUETE.hidden = true;
	}
};