var Sisaluno = Sisaluno || {};

Sisaluno.MaskCpf = (function() {
	
	class MaskCpf {
        constructor() {
            this.inputCpf = $('.js-cpf');

        }
        enable() {
            this.inputCpf.mask('000.000.000-00');
        }
    }	
	
	return MaskCpf;
	
}());

$(function() {
	var maskCpf = new Sisaluno.MaskCpf();
	maskCpf.enable();
});
