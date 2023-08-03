 function verificaEspOF(value){
	
	var divMEDICO = document.getElementById("divMEDICO");
	var divODONTO = document.getElementById("divODONTO");
	var divQCO = document.getElementById("divQCO");
	var divCFGO = document.getElementById("divCFGO");
	var divCPOR = document.getElementById("divCPOR");
	var divNPOR = document.getElementById("divNPOR");
		
	if (value == '') {
		console.log(value);
		divCFGO.hidden = true;
		divCPOR.hidden = true;
		divNPOR.hidden = true;
		divMEDICO.hidden = true;
		divODONTO.hidden = true;
		divQCO.hidden = true;
		
		
	} else if (value == 'FARMACIA') {
		divCFGO.hidden = true;
		divCPOR.hidden = true;
		divNPOR.hidden = true;
		divMEDICO.hidden = true;
		divODONTO.hidden = true;
		divQCO.hidden = true;
		
	} else if (value == 'MEDICO') {
		divCFGO.hidden = true;
		divCPOR.hidden = true;
		divNPOR.hidden = true;
		divMEDICO.hidden = false;
		divODONTO.hidden = true;
		divQCO.hidden = true;
		
	}else if (value == 'ODONTO') {
		divCFGO.hidden = true;
		divCPOR.hidden = true;
		divNPOR.hidden = true;
		divMEDICO.hidden = true;
		divODONTO.hidden = false;
		divQCO.hidden = true;
		
	}else if (value == 'QCO') {		
		divCFGO.hidden = true;
		divCPOR.hidden = true;
		divNPOR.hidden = true;
		divMEDICO.hidden = true;
		divODONTO.hidden = true;
		divQCO.hidden = false;
		
		
	}else if (value == 'CFGO') {
		divCFGO.hidden = false;
		divCPOR.hidden = true;
		divNPOR.hidden = true;
		divMEDICO.hidden = true;
		divODONTO.hidden = true;
		divQCO.hidden = true;
		
	}else if (value == 'CPOR') {
		divCFGO.hidden = true;
		divCPOR.hidden = false;
		divNPOR.hidden = false;
		divMEDICO.hidden = true;
		divODONTO.hidden = true;
		divQCO.hidden = true;
		
	}else {
		divCFGO.hidden = true;
		divCPOR.hidden = true;
		divNPOR.hidden = true;
		divMEDICO.hidden = true;
		divODONTO.hidden = true;
		divQCO.hidden = true;
		
	}		
	
};