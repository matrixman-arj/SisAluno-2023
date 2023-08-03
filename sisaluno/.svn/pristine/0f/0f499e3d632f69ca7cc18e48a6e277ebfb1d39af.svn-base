package br.mil.eb.decex.sisaluno.repository.helper.curso;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.dto.CursoDTO;
import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.repository.filter.CursoFilter;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtilJPA;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public class CursosImpl implements CursosQueries {
	
	@PersistenceContext
	private EntityManager manager;	
			
	@Autowired
	private PaginacaoUtilJPA paginacaoUtilJPA;
	
	/*############################--INÍCIO DO NOVO MÉTODO FILTRAR--#######################################################*/
	/** Método responsável por realizar um filtro na quantidade total de 
	 * cursos existentes no banco de dados */
	@Override
	@Transactional(readOnly = true)
	public Page<Curso> filtrar(CursoFilter filtro, Pageable pageable) {
		/* Primeiro pegamos uma referência do CriteriaBuilder do EntityManager(manager),
		 * o qual podemos utilizar, as diferentes partes da consulta.
		 */		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		/* Utilizando o CriteriaBuilder, que criamos acima, podemos agora, criar um CriteriaQuery,
		 * do tipo Curso, que descreve o que precisamos na consulta.
		 */
		CriteriaQuery<Curso> query = criteriaBuilder.createQuery(Curso.class);
		
		/* Com o CriteriaQuery<Curso>, declaramos o ponto de partida da consulta que, 
		 * é a entidade Curso e, guardamos essa entidade, na variável curso para que,
		 * possamos utilizar posteriormente.
		 */
		Root<Curso> curso = query.from(Curso.class);
		
		/* Criamos agora, uma lista dinâmica de predicados.*/
		List<Predicate> predicates = new ArrayList<>();
		
		/* Verificamos agora, se o parametro foi preenchido através do CursoFilter ao qual,
		 * demos o nome de filtro se estiver, adicionamos na lista de predicados.
		 */
		TypedQuery<Curso> typedQuery = adicionarFiltro(filtro, criteriaBuilder, query, curso, predicates);
				
		paginacaoUtilJPA.prepararRestricoesDePaginacao(typedQuery, pageable, query);
		
		return new PageImpl<>(typedQuery.getResultList(), pageable, total(filtro));
	}
	/*############################--FINAL DO NOVO MÉTODO FILTRAR--#######################################################**/
	
	
	/*############################--INÍCIO DO ANTIGO MÉTODO ADICIONAR FILTRO--###########################################**/
	private void adicionarFiltro(CursoFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getSku())) {
				criteria.add(Restrictions.eq("sku", filtro.getSku()));
			}
		}
	}
	/*############################--FINAL DO ANTIGO MÉTODO ADICIONAR FILTRO--###########################################**/
	

	
	/*############################--INÍCIO DO NOVO MÉTODO ADICIONAR FILTRO--###########################################
	 * Método responsável por fazer a filtragem pelo SKU, a partir dos dados digitados pelo usuário,
	 * recebido aqui através do CursoFilter que armazena o que recebeu, na variável a qual demos o nome de filtro.
	 * Este método foi extraído do método filtrar, para que possa ser utilizado por outros métodos. 
	 */
	private TypedQuery<Curso> adicionarFiltro(CursoFilter filtro, CriteriaBuilder criteriaBuilder,
			CriteriaQuery<Curso> query, Root<Curso> curso, List<Predicate> predicates) {
		if(filtro.getSku() != null) {
			predicates.add(criteriaBuilder.equal(curso.get("sku"), filtro.getSku()));
		}
		
		/* Verificamos os predicados para adicionarmso a cláusula where.*/
		if (!predicates.isEmpty()) {
			query.where(predicates.stream().toArray( Predicate[]::new));
		}
		
		/* Depois disso, criamos uma instância TypedQuery<Curso> do nosso CriteriaQuery.*/
		TypedQuery<Curso> typedQuery = manager.createQuery(query);
		return typedQuery;
	}
	/*############################--FINAL DO NOVO MÉTODO ADICIONAR FILTRO--###########################################*/
	
	/*############################--INÍCIO DO ANTIGO MÉTODO TOTAL--###########################################*/
	/** Método responsável por retornar a quantidade total de cursos existentes no banco de dados*/
	private Long total(CursoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Curso.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}	
	/**############################--FINAL DO ANTIGO MÉTODO TOTAL--###########################################*/
	
	
	/**############################--INÍCIO DO NOVO MÉTODO totalJPA2--###########################################*/
	private Long totalJPA2(CursoFilter filtro, UsuarioSistema sistema) {
		
		/* Primeiro pegamos uma referência do CriteriaBuilder do EntityManager(manager),
		 * o qual podemos utilizar, as diferentes partes da consulta.
		 */	
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		/* Utilizando o CriteriaBuilder, que criamos acima, podemos agora, criar um CriteriaQuery,
		 * do tipo Long, que descreve o que precisamos na consulta que, nesse caso é aquantidade total de registros para a OM do usuário logado.
		 */
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		
		/* Com o CriteriaQuery<Curso>, declaramos o ponto de partida da consulta que, 
		 * é a entidade Curso e, guardamos essa entidade, na variável curso para que,
		 * possamos utilizar posteriormente.
		 */
		Root<Curso> curso = query.from(Curso.class);
		
		/* Criamos agora, uma lista dinâmica de predicados.*/
		List<Predicate> predicates = new ArrayList<>();
		
		/* Aqui fazemos um Join entre a tabela Curso e Usuario através da coluna inseridoPor */
		
//		Join<Curso, Usuario> usuario = curso.join("inseridoPor");
		
		/*Com essa linha, pegamos o usuario que tem ligação com a tabela curso através do campo inseridoPor, 
		 * do Join acima e, verificamos apenas os cursos que foram inseridos por usuários daquela om.  
		 */
//		predicates.add(criteriaBuilder.equal(curso.get("inseridoPor"), sistema.getUsuario().getOm().getCodigo()));
		
		if(!predicates.isEmpty()) {
			query.where(predicates.stream().toArray(Predicate[]::new));
		}

		query.select(criteriaBuilder.count(curso));
		return manager.createQuery(query).getSingleResult();
	}
	/**############################--FINAL DO NOVO MÉTODO totalJPA2--###########################################*/
	
	
	/**#####################===INÍCIO DO MÉTODO QUE FILTRA PELA OM DO USUÁRIO LOGADO==###################**/
	@Override
	@Transactional(readOnly = true)
	public Page<Curso> filtrarPelaOmUsuLogado(CursoFilter filtro, Pageable pageable, UsuarioSistema sistema) {
		
		/* Primeiro pegamos uma referência do CriteriaBuilder do EntityManager(manager),
		 * o qual podemos utilizar, as diferentes partes da consulta.
		 */		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		/* Utilizando o CriteriaBuilder, que criamos acima, podemos agora, criar um CriteriaQuery,
		 * do tipo Curso, que descreve o que precisamos na consulta, que é uma lista apenas 
		 * com cursos da OM do usuario logado.
		 */
		CriteriaQuery<Curso> query = criteriaBuilder.createQuery(Curso.class);
		
		/*
		 * Com o CriteriaQuery<Curso>, declaramos o ponto de partida da consulta que, 
		 * é a entidade Curso e, guardamos essa entidade, na variável curso para que,
		 * possamos utilizar posteriormente.
		 */
		Root<Curso> curso = query.from(Curso.class);
		
		/* Aqui fazemos um Join entre a tabela Curso e Usuario através da coluna inseridoPor*/
		
//		Join<Curso, Usuario> usuario = curso.join("inseridoPor");
		
		/*
		 * Criamos agora, uma lista dinâmica de predicados.
		 */
		List<Predicate> predicates = new ArrayList<>();
		
		/*Com essa linha, pegamos o usuario que tem ligação com a tabela curso através do campo inseridoPor, 
		 * do Join acima e, verificamos apenas os cursos que foram inseridos por usuários daquela om.  
		 */
//		predicates.add(criteriaBuilder.equal(curso.get("inseridoPor"), sistema.getUsuario().getOm().getCodigo()));
		
		/*
		 * Verificamos agora, se o parametro foi preenchido através do CursoFilter ao qual,
		 * demos o nome de filtro se estiver, adicionamos na lista de predicados.
		 */
		TypedQuery<Curso> queryResult = adicionarFiltro(filtro, criteriaBuilder, query, curso, predicates);
		
		query.from(Curso.class);
				
		if(!predicates.isEmpty()) {
			query.where(predicates.stream().toArray(Predicate[]::new));
		}
		
		paginacaoUtilJPA.prepararRestricoesDePaginacao2(queryResult, pageable, query, sistema);
		
		return new PageImpl<>(queryResult.getResultList(), pageable, totalJPA2(filtro, sistema));
	}
	/**#####################===FINAL DO MÉTODO QUE FILTRA PELA OM DO USUÁRIO LOGADO==###################**/


	@Override
	public List<CursoDTO> porSkuOuDescricao(String skuOuDescr) {
		String jpql = "select new br.mil.eb.decex.sisaluno.dto.CursoDTO(codigo, sku, "
					+ "modalidade, universo, descr, linha, vinculo, foto)"
					+ "from Curso where lower(sku) like lower(:skuOuDescr) or lower(descr) like lower(:skuOuDescr)";
		List<CursoDTO> cursosFiltrados = manager.createQuery(jpql, CursoDTO.class)
					.setParameter("skuOuDescr", skuOuDescr + "%")
					.getResultList();
		return cursosFiltrados;
	}		
	
	
}
