function verificaVinculo(value){
	
	var divTipoVinculo = document.getElementById("divTipoVinculo");
		
	
	if (value == '') {
		console.log(value);
		divTipoVinculo.hidden = true;
		
		
	} else if (value == 'CARREIRA') {
		divTipoVinculo.hidden = true;
		
	} else if (value == 'TEMPORARIO') {
		divTipoVinculo.hidden = false;
		
	}
};