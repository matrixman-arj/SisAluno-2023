Sisaluno = Sisaluno || {};

Sisaluno.PesquisaRapidaAluno = (function() {
	
	function PesquisaRapidaAluno() {
		this.pesquisaRapidaAlunosModal = $('#pesquisaRapidaAlunos');
		this.cpfInput = $('#cpfAlunoModal');		
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-alunos-btn');		 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaAlunos');				
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-aluno').html();				
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);				
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaAluno.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaAlunosModal.on('shown.bs.modal', onModalShow.bind(this));

	}
	
	function onModalShow() {
		this.cpfInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaAlunosModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {				
				cpf: this.cpfInput.val(),
				
			}, 
			success: onPesquisaConcluida.bind(this),			
			error: onErroPesquisa.bind(this)
			
		});
	}
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaAlunoPesquisaRapida = new Sisaluno.TabelaAlunoPesquisaRapida(this.pesquisaRapidaAlunosModal);
		tabelaAlunoPesquisaRapida.iniciar();
	} 
	
	function onErroPesquisa() {
		$('.js-mensagem-erro').removeClass('hidden');
		
//		if(this.cpfInput <= 3){
//			this.mensagemErro.removeClass('hidden');
//		}
	}
	
	return PesquisaRapidaAluno;
	
}());

Sisaluno.TabelaAlunoPesquisaRapida = (function() {
	
	function TabelaAlunoPesquisaRapida(modal) {
		this.modalAluno = modal;
		this.aluno = $('.js-aluno-pesquisa-rapida');
	}
	
	TabelaAlunoPesquisaRapida.prototype.iniciar = function() {
		this.aluno.on('click', onAlunoSelecionado.bind(this));
	}
	
	function onAlunoSelecionado(evento) {
		this.modalAluno.modal('hide');
		
		var alunoSelecionado = $(evento.currentTarget);
		$('#cpf').val(alunoSelecionado.data('cpf'));
		$('#nomeAluno').val(alunoSelecionado.data('nome'));
		$('#codigoAluno').val(alunoSelecionado.data('codigo'));
	}
	
	return TabelaAlunoPesquisaRapida;
	
}());

$(function() {
	var pesquisaRapidaAluno = new Sisaluno.PesquisaRapidaAluno();
	pesquisaRapidaAluno.iniciar();
});