function verifica(value){
	
	var divPais = document.getElementById("divPais");
	var divEB = document.getElementById("divEB");
	var divMB = document.getElementById("divMB");
	var divFAB = document.getElementById("divFAB");	
	
	if (value == 'ONA') {
		console.log(value);
		divPais.hidden = false;
		divEB.hidden = true;
		divMB.hidden = true;
		divFAB.hidden = true;
		
	} else if (value == '') {
		divPais.hidden = true;
		divEB.hidden = true;
		divMB.hidden = true;
		divFAB.hidden = true;
		
	} else if (value == 'CIVIL') {
		divPais.hidden = true;
		divEB.hidden = true;
		divMB.hidden = true;
		divFAB.hidden = true;
		
	}else if (value == 'EXERCITO') {
		divPais.hidden = true;
		divEB.hidden = false;
		divMB.hidden = true;
		divFAB.hidden = true;
		
	}else if (value == 'MARINHA') {
		divPais.hidden = true;
		divEB.hidden = true;
		divMB.hidden = false;
		divFAB.hidden = true;
		
	}else if (value == 'AERONAUTICA') {
		divPais.hidden = true;
		divEB.hidden = true;
		divMB.hidden = true;
		divFAB.hidden = false;
		
	}else if (value == 'FORCAS_AUXILIARES') {
		divPais.hidden = true;
		divEB.hidden = true;
		divMB.hidden = true;
		divFAB.hidden = true;
		
	}
};