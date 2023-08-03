//var cpf = document.querySelector("#cpf");
//
//cpf.addEventListener("blur", function(){
//   if(cpf.value) cpf.value = cpf.value.match(/.{1,3}/g).join(".").replace(/\.(?=[^.]*$)/,"-");
//});
//




var Sisaluno = Sisaluno || {};


Sisaluno.MaskCPF = (function() {
	
	function MaskCPF() {
		this.inputCPF = $('.js-cpf');
		
	}
	
	MaskCPF.prototype.enable = function() {
		this.inputCPF.mask('000.000.000-00');
	}
	
	return MaskCPF;
	
}());

$(function() {
	
	var maskCPF = new Sisaluno.MaskCPF();
	maskCPF.enable();
	
	
	
});
