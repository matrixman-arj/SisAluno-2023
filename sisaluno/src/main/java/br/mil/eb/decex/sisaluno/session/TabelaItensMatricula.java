package br.mil.eb.decex.sisaluno.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.model.ItemMatricula;

@SessionScope
@Component
public class TabelaItensMatricula {
	
	private String uuid;
	private List<ItemMatricula> itens = new ArrayList<>();	
	
	public TabelaItensMatricula(String uuid) {		
		this.uuid = uuid;
	}

	public void adicionarItem(Curso curso, RedirectAttributes attributes) {
		
		Optional<ItemMatricula> itemMatriculaOptional = itens.stream()
			.filter(i -> i.getCurso().equals(curso))
			.findAny();
		
		ItemMatricula itemMatricula = null;
		if(itemMatriculaOptional.isPresent()) {
			itemMatricula = itemMatriculaOptional.get();
			itemMatricula = null;
		}else {
			
			itemMatricula = new ItemMatricula();
			itemMatricula.setCurso(curso);
			
			itens.add(0, itemMatricula);
		}
		
	}
	
	public void excluirItem(Curso curso) {
		int indice = IntStream.range(0, itens.size())
				.filter(i -> itens.get(i).getCurso().equals(curso))
				.findAny().getAsInt();
		itens.remove(indice);
	}
	
	public int total() {
		return itens.size();
	}

	public List<ItemMatricula> getItens() {		
		return itens;
	}
	
	@SuppressWarnings("unused")
	private Optional<ItemMatricula> buscarItemPorCurso(Curso curso) {
		return itens.stream()
				.filter(i -> i.getCurso().equals(curso))
				.findAny();
		
	}	

	public String getUuid() {
		return uuid;
	}
	
	

	public void setItens(List<ItemMatricula> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uuid);
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
		return Objects.equals(uuid, other.uuid);
	}
	
	
}
