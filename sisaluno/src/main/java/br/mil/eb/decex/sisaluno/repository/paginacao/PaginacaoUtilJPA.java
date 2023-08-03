package br.mil.eb.decex.sisaluno.repository.paginacao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;

import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

@Component
public class PaginacaoUtilJPA {

	public void prepararOrdenacao(CriteriaBuilder builder, CriteriaQuery<?> query, Root<?> root, 
			Pageable pageable) {
		
		Sort sort = pageable.getSort();
		
		if(sort != null) {
			
			Order order = sort.iterator().next();
			
			String property = order.getProperty();
			List<String> caminhos = Arrays.asList(property.split("\\."));
			
			Path<?> path = montarCaminhoRecursivo(root, caminhos);
			
			query.orderBy(order.isAscending() ? builder.asc(path) : builder.desc(path));
			
		}		
				
	}	
	
//	public void prepararRestricoesDePaginacao(Pageable pageable, TypedQuery<?> typedQuery, CriteriaQuery<Aluno> query) {
//				
//		int paginaAtual = pageable.getPageNumber();
//		int totalRegistrosPorPagina = pageable.getPageSize();
//		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
//		
//		typedQuery.setFirstResult(primeiroRegistroDaPagina);
//		typedQuery.setMaxResults(totalRegistrosPorPagina);		
//		
//	}
	
	private Path<?> montarCaminhoRecursivo(Root<?> root, List<String> caminhos) {

		Iterator<String> iterator = caminhos.iterator();
		String propriedade = iterator.next();
		
		Path<?> path = root.get(propriedade);
		
		while (iterator.hasNext()) {
			path = path.get(iterator.next());			
		}
		
		return path;		
	}

	public void prepararRestricoesDePaginacao(TypedQuery<?> typedQuery, Pageable pageable, CriteriaQuery<?> query) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		typedQuery.setFirstResult(primeiroRegistroDaPagina);
		typedQuery.setMaxResults(totalRegistrosPorPagina);	
		
	}
	
	public void prepararRestricoesDePaginacao2(TypedQuery<?> queryResult, Pageable pageable, CriteriaQuery<?> query, UsuarioSistema sistema) {
		int paginaAtual = pageable.getPageNumber();
		System.out.println("Número da página: " + paginaAtual);
		
		int totalRegistrosPorPagina = pageable.getPageSize();
		System.out.println("Qtd reg por página: " + totalRegistrosPorPagina);
		
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		System.out.println("Prim reg da página: " + primeiroRegistroDaPagina);
		
		queryResult.setFirstResult(primeiroRegistroDaPagina);
		queryResult.setMaxResults(totalRegistrosPorPagina);	
		
	}	
	
}