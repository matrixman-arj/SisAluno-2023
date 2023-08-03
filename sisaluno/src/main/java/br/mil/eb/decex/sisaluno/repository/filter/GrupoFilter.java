package br.mil.eb.decex.sisaluno.repository.filter;

import java.util.List;

import br.mil.eb.decex.sisaluno.model.Permissao;

public class GrupoFilter {
	
	private String nome;
	private List<Permissao> permissoes;
	
	public String getNome() {
		return nome;
	}	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Permissao> getPermissoes() {
		return permissoes;
	}	
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	
	
	

}
