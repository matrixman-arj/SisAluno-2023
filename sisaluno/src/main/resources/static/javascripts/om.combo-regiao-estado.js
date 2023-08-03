var Sisaluno = Sisaluno || {};

Sisaluno.ComboRegiao = (function() {
	
	function ComboRegiao() {
		this.combo = $('#regiao');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboRegiao.prototype.iniciar = function() {
		this.combo.on('change', onRegiaoAlterada.bind(this));
	}
	
	function onRegiaoAlterada(){
		this.emitter.trigger('alterada', this.combo.val());

	}
	
	return ComboRegiao;
	
}());

Sisaluno.ComboEstado = (function() {
	
	function ComboEstado(comboRegiao) {
		this.comboRegiao = comboRegiao;
		this.combo = $('#estado');
		this.imgLoading = $('.js-img-loading');
		this.inputHiddenEstadoSelecionado = $('#inputHiddenEstadoSelecionado');
	}
	
	ComboEstado.prototype.iniciar = function() {
		reset.call(this);
		this.comboRegiao.on('alterada', onRegiaoAlterada.bind(this));
		var codigoRegiao = this.comboRegiao.combo.val();		
		inicializarEstados.call(this, codigoRegiao);	
	}
	
	function onRegiaoAlterada(evento, codigoRegiao){
//		console.log('codigo da regiao, no combo estado', codigoRegiao);		
		this.inputHiddenEstadoSelecionado.val('');
		inicializarEstados.call(this, codigoRegiao); 
	}
	
	function inicializarEstados(codigoRegiao) {
		if (codigoRegiao){
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: {'regiao': codigoRegiao},
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			
			resposta.done(noBuscarEstadosFinalizado.bind(this));
		} else {
			reset.call(this);
		}
	}
	
	function noBuscarEstadosFinalizado(estados){
		var options = [];
		estados.forEach(function(estado) {
			options.push('<option value="' + estado.codigo + '">' + estado.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
		
		var codigoEstadoSelecionado = this.inputHiddenEstadoSelecionado.val();
		if (codigoEstadoSelecionado) {
			this.combo.val(codigoEstadoSelecionado);
		}
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione o estado</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	}
	
	function iniciarRequisicao() {
		reset.call(this);
		this.imgLoading.show();
	}
	
	function finalizarRequisicao() {
		this.imgLoading.hide();
	}
	
	return ComboEstado;
	
}());

$(function() {
	var comboRegiao = new Sisaluno.ComboRegiao();
	comboRegiao.iniciar();
	
	var comboEstado = new Sisaluno.ComboEstado(comboRegiao);
	comboEstado.iniciar();
});