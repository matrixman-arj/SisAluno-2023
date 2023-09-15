function verificaVinculo(value){
	
	var divVinculo = document.getElementById("divVinculo");
		
	
	if (value == '') {
		console.log(value);
		divVinculo.hidden = true;
		
		
	} else if (value == 'CARREIRA') {
		divVinculo.hidden = true;
		
	} else if (value == 'TEMPORARIO') {
		divVinculo.hidden = false;
		
	}
};