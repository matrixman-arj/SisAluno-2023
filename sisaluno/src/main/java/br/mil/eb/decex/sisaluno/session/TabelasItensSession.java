package br.mil.eb.decex.sisaluno.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.model.ItemMatricula;

@SessionScope
@Component
public class TabelasItensSession {
	
	private Set<TabelaItensMatricula> tabelas = new HashSet<>();
	
	
//	public void adicionarItem(String uuid, Curso curso, Integer quantidade) {
//		TabelaItensMatricula tabela = tabelas.stream()
//				.filter(tab -> tab.getUuid().equals(uuid))
//				.findAny().orElse(new TabelaItensMatricula(uuid));
//		tabela.adicionarItem(curso, quantidade);
//		tabelas.add(tabela);
//	}

	public void adicionarItem(String uuid, Curso curso, int quantidade) {
		TabelaItensMatricula tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(curso, quantidade);
		tabelas.add(tabela);
	}


	public void excluirItem(String uuid, Curso curso) {
		TabelaItensMatricula tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(curso);
		
	}
	
	
	public List<ItemMatricula> getItens(String uuid) {		
		return buscarTabelaPorUuid(uuid).getItens();
	}
	
	private TabelaItensMatricula buscarTabelaPorUuid(String uuid) {
		TabelaItensMatricula tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaItensMatricula(uuid));
		return tabela;
	}

}
