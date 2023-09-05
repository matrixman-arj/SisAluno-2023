Sisaluno.TabelaItens = (function(){
	
	function TabelaItens(autocomplete){
		this.autocomplete = autocomplete;
		this.tabelaCursosContainer = $('.js-tabela-cursos-container');
		this.uuid = $('#uuid').val();
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	TabelaItens.prototype.iniciar = function(){
		this.autocomplete.on('item-selecionado', onItemSelecionado.bind(this));
	}
	
	function onItemSelecionado(evento, item){
		var resposta = $.ajax({
			url: 'item',
			method: 'POST',
			data: {
				codigoCurso: item.codigo,
				uuid: this.uuid
			}
			
		});
		resposta.done(onItemAdicionadoNoServidor.bind(this));
				
	}
	
	function onItemAdicionadoNoServidor(html){
		this.tabelaCursosContainer.html(html);
		$('.js-tabela-item').on('dblclick', onDoubleClick);
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
		
		this.emitter.trigger('tabela-itens-atualizada')
	}
	
	function onDoubleClick(evento){
		$(this).toggleClass('solicitando-exclusao');
	}
	
	function onExclusaoItemClick(evento){
		var codigoCurso = $(evento.target).data('codigo-curso');
		var resposta = $.ajax({
			url: 'item/' + this.uuid + '/' + codigoCurso,
			method: 'DELETE'
		});
		
		resposta.done(onItemAdicionadoNoServidor.bind(this));
	}
	
	return TabelaItens;
	
}());

$(function(){
	
	var autocomplete = new Sisaluno.Autocomplete();
	autocomplete.iniciar();
	
	var tabelaItens = new Sisaluno.TabelaItens(autocomplete);
	tabelaItens.iniciar();
});
