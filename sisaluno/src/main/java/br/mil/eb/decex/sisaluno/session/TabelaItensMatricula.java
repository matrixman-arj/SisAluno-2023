package br.mil.eb.decex.sisaluno.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.model.ItemMatricula;

class TabelaItensMatricula {
	
	private String uuid;
	private List<ItemMatricula> itens = new ArrayList<>();	
	
	public TabelaItensMatricula(String uuid) {		
		this.uuid = uuid;
	}

	public void adicionarItem(Curso curso, Integer quantidade) {		
		Optional<ItemMatricula> itemMatriculaOptional = buscarItemPorCurso(curso);
		
		ItemMatricula itemMatricula = null;
		if(itemMatriculaOptional.isPresent()) {
			itemMatricula = itemMatriculaOptional.get();
			itemMatricula.setQuantidade(itemMatricula.getQuantidade() + quantidade);
		}else {
			
			itemMatricula = new ItemMatricula();
			itemMatricula.setCurso(curso);
			itemMatricula.setQuantidade(1);
			itens.add(0, itemMatricula);
			
			System.out.println("quantidade: " + getItens().size());
		}
		
	}
	
	public void alterarQuantidadeItens(Curso curso, Integer quantidade) {
		ItemMatricula itemMatricula = buscarItemPorCurso(curso).get();
		itemMatricula.setQuantidade(quantidade);
	}
	
	public void excluirItem(Curso curso) {
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getCurso().equals(curso))
				.findAny().orElse(0);
		itens.remove(indice);
		
		System.out.println("quantidade: " + getItens().size());
	}
	
	
	public int total() {
		return itens.size();
	}

	public List<ItemMatricula> getItens() {		
		return itens;
	}	
	
	private Optional<ItemMatricula> buscarItemPorCurso(Curso curso) {
		return itens.stream()
				.filter(i -> i.getCurso().equals(curso))
				.findAny();
		
	}	

	public String getUuid() {
		return uuid;
	}
		

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaItensMatricula other = (TabelaItensMatricula) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}	
	
}
