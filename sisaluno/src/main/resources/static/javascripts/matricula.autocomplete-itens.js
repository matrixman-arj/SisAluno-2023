Sisaluno = Sisaluno || {};

Sisaluno.Autocomplete = (function() {
	
	function Autocomplete() {
		this.skuOuDescricaoInput = $('.js-sku-descr-curso-input');
		var htmlTemplateAutocomplete = $('#template-autocomplete-curso').html();
		this.template = Handlebars.compile(htmlTemplateAutocomplete);
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	Autocomplete.prototype.iniciar = function() {
		var options = {
			url: function(skuOuDescr) {
				return this.skuOuDescricaoInput.data('url') + '?skuOuDescr=' + skuOuDescr;
			}.bind(this),
			getValue: 'descr',
			minCharNumber: 3,
			requestDelay: 300,
			ajaxSettings: {
				contentType: 'application/json'
			},
			template: {
				type: 'custom',
				method: template.bind(this)
			},
			list:{
				onChooseEvent: onItemSelecionado.bind(this)
			}
			
		};
		
		this.skuOuDescricaoInput.easyAutocomplete(options);
	}
	
	function onItemSelecionado(){
		this.emitter.trigger('item-selecionado', this.skuOuDescricaoInput.getSelectedItemData());
		this.skuOuDescricaoInput.val('');
		this.skuOuDescricaoInput.focus();
	}
	
	function template(descr, curso){
		return this.template(curso);
	}
				
	return Autocomplete
	
}());

$(function(){
	
	var autocomplete = new Sisaluno.Autocomplete();
	autocomplete.iniciar();
})

