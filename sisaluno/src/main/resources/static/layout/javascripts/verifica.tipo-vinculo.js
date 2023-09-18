function verificaTipoVinculo(value){
	
	var divCporEstado = document.getElementById("divCporEstado");
		
	
	if (value == '') {
		console.log(value);
		divCporEstado.hidden = true;
		
		
	} else if (value == 'OTT') {
		divCporEstado.hidden = true;
		
	} else if (value == 'R2') {
		divCporEstado.hidden = false;
		
	}else if (value == 'STT') {
		divCporEstado.hidden = true;
		
	}	
};