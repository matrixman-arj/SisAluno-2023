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
		
		bindQuantidade.call(this);
		bindTabelaItem.call(this);
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
		resposta.done(onItemAtualizadoNoServidor.bind(this));
				
	}
	
	function onItemAtualizadoNoServidor(html){
		this.tabelaCursosContainer.html(html);
		
		bindQuantidade.call(this);
		
		
		var tabelaItem = bindTabelaItem.call(this);		
		this.emitter.trigger('tabela-itens-atualizada');
	}
	
	function onQuantidadeItemAlterado(evento) {
		var input = $(evento.target);
		var quantidade = input.val();
		
		if(quantidade = input <= 0){
			input.val(1);
			quantidade = 1;
		}
		
		var codigoCurso = input.data('codigo-curso');
		
		var resposta = $.ajax({
			url: 'item/' + codigoCurso,
			method: 'PUT',
			data: {
				quantidade: quantidade,
				uuid: this.uuid
			}
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function onDoubleClick(evento){
		$(this).toggleClass('solicitando-exclusao');
	}
	
	function onExclusaoItemClick(evento) {
		var codigoCurso = $(evento.target).data('codigo-curso');
		var resposta = $.ajax({
			url: 'item/' + this.uuid + '/' + codigoCurso,
			method: 'DELETE'
		});
		
		resposta.done(onItemAtualizadoNoServidor.bind(this));
	}
	
	function bindQuantidade(){		
		var quantidadeItemInput = $('.js-tabela-curso-quantidade-item');
		quantidadeItemInput.on('change', onQuantidadeItemAlterado.bind(this));
	}
	
	function bindTabelaItem(){
		var tabelaItem = $('.js-tabela-item');
		tabelaItem.on('dblclick', onDoubleClick);		
		$('.js-exclusao-item-btn').on('click', onExclusaoItemClick.bind(this));
		return tabelaItem;
	}
	
	return TabelaItens;
	
}());