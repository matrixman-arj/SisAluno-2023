Sisaluno.Matricula = (function (){
	
	function Matricula(tabelaItens){
		this.tabelaItens = tabelaItens;
	}
	
	Matricula.prototype.iniciar = function(){
//		this.tabelaItens.on('tabela-itens-atualizada', onTabelaItensAtualizada.bind(this));
		
	}
	
//	function onTabelaItensAtualizada(evento){
//		
//	}
	
	return Matricula;
	
}());

$(function() {
	
	var autocomplete = new Sisaluno.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Sisaluno.TabelaItens(autocomplete);
	tabelaItens.iniciar();
	
	var matricula = new Sisaluno.Matricula(tabelaItens);
	matricula.iniciar();
})