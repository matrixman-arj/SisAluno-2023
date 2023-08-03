package br.mil.eb.decex.sisaluno.repository.filter;

import br.mil.eb.decex.sisaluno.model.Estado;

public class RegiaoFilter {
		
	private Estado estado;
	private String nome;
	
	public String getNome() {
		return nome;
	}	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
			
}
