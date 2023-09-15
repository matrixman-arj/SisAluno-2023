function verificaUniverso(value){
	
	var divUETE = document.getElementById("divUETE");
		
	
	if (value == '') {
		console.log(value);
		divUETE.hidden = true;
		
		
	} else if (value == 'FORM_OF') {
		divUETE.hidden = true;
		
	} else if (value == 'OF_PRACA') {
		divUETE.hidden = true;
		
	}else if (value == 'FORM_GRAD_PCA') {
		divUETE.hidden = false;
		
	}
};
