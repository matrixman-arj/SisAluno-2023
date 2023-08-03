package br.mil.eb.decex.sisaluno.repository.helper.complemento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Complemento;
import br.mil.eb.decex.sisaluno.model.Usuario;
import br.mil.eb.decex.sisaluno.repository.filter.ComplementoFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public class ComplementosImpl implements ComplementosQueries {
	
	@PersistenceContext
	private EntityManager manager;	
			
	
	
	/*############################--INÍCIO DO NOVO MÉTODO FILTRAR--#######################################################*/
	/** Método responsável por realizar um filtro na quantidade total de 
	 * complementos existentes no banco de dados */
	@Override
	@Transactional(readOnly = true)
	public Page<Complemento> filtrar(ComplementoFilter filtro, Pageable pageable) {
		/* Primeiro pegamos uma referência do CriteriaBuilder do EntityManager(manager),
		 * o qual podemos utilizar, as diferentes partes da consulta.
		 */		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		/* Utilizando o CriteriaBuilder, que criamos acima, podemos agora, criar um CriteriaQuery,
		 * do tipo Complemento, que descreve o que precisamos na consulta.
		 */
		CriteriaQuery<Complemento> query = criteriaBuilder.createQuery(Complemento.class);
		
		/* Com o CriteriaQuery<Complemento>, declaramos o ponto de partida da consulta que, 
		 * é a entidade Complemento e, guardamos essa entidade, na variável complemento para que,
		 * possamos utilizar posteriormente.
		 */
		Root<Complemento> complemento = query.from(Complemento.class);
		
		/* Criamos agora, uma lista dinâmica de predicados.*/
		List<Predicate> predicates = new ArrayList<>();
		
		/* Verificamos agora, se o parametro foi preenchido através do ComplementoFilter ao qual,
		 * demos o nome de filtro se estiver, adicionamos na lista de predicados.
		 */
		TypedQuery<Complemento> typedQuery = adicionarFiltro(filtro, criteriaBuilder, query, complemento, predicates);
				
//		paginacaoUtilJPA.prepararRestricoesDePaginacao(typedQuery, pageable, query);
		
		return new PageImpl<>(typedQuery.getResultList(), pageable, total(filtro));
	}
	/*############################--FINAL DO NOVO MÉTODO FILTRAR--#######################################################**/
	
	
	/*############################--INÍCIO DO ANTIGO MÉTODO ADICIONAR FILTRO--###########################################**/
	private void adicionarFiltro(ComplementoFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(!StringUtils.isEmpty(filtro.getCpf())) {
				criteria.add(Restrictions.eq("cpf", filtro.getCpf()));
			}
		}
	}
	/*############################--FINAL DO ANTIGO MÉTODO ADICIONAR FILTRO--###########################################**/
	

	
	/*############################--INÍCIO DO NOVO MÉTODO ADICIONAR FILTRO--###########################################
	 * Método responsável por fazer a filtragem pelo CPF, a partir dos dados digitados pelo usuário,
	 * recebido aqui através do ComplementoFilter que armazena o que recebeu, na variável a qual demos o nome de filtro.
	 * Este método foi extraído do método filtrar, para que possa ser utilizado por outros métodos. 
	 */
	private TypedQuery<Complemento> adicionarFiltro(ComplementoFilter filtro, CriteriaBuilder criteriaBuilder,
			CriteriaQuery<Complemento> query, Root<Complemento> complemento, List<Predicate> predicates) {
		if(filtro.getCpf() != null) {
			predicates.add(criteriaBuilder.equal(complemento.get("cpf"), filtro.getCpf()));
		}
		
		/* Verificamos os predicados para adicionarmso a cláusula where.*/
		if (!predicates.isEmpty()) {
			query.where(predicates.stream().toArray( Predicate[]::new));
		}
		
		/* Depois disso, criamos uma instância TypedQuery<Complemento> do nosso CriteriaQuery.*/
		TypedQuery<Complemento> typedQuery = manager.createQuery(query);
		return typedQuery;
	}
	/*############################--FINAL DO NOVO MÉTODO ADICIONAR FILTRO--###########################################*/
	
	/*############################--INÍCIO DO ANTIGO MÉTODO TOTAL--###########################################*/
	/** Método responsável por retornar a quantidade total de complementos existentes no banco de dados*/
	private Long total(ComplementoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Complemento.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}	
	/**############################--FINAL DO ANTIGO MÉTODO TOTAL--###########################################*/
	
	
	/**############################--INÍCIO DO NOVO MÉTODO totalJPA2--###########################################*/
	private Long totalJPA2(ComplementoFilter filtro, UsuarioSistema sistema) {
		
		/* Primeiro pegamos uma referência do CriteriaBuilder do EntityManager(manager),
		 * o qual podemos utilizar, as diferentes partes da consulta.
		 */	
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		/* Utilizando o CriteriaBuilder, que criamos acima, podemos agora, criar um CriteriaQuery,
		 * do tipo Long, que descreve o que precisamos na consulta que, nesse caso é aquantidade total de registros para a OM do usuário logado.
		 */
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		
		/* Com o CriteriaQuery<Complemento>, declaramos o ponto de partida da consulta que, 
		 * é a entidade Complemento e, guardamos essa entidade, na variável complemento para que,
		 * possamos utilizar posteriormente.
		 */
		Root<Complemento> complemento = query.from(Complemento.class);
		
		/* Criamos agora, uma lista dinâmica de predicados.*/
		List<Predicate> predicates = new ArrayList<>();
		
		/* Aqui fazemos um Join entre a tabela Complemento e Usuario através da coluna inseridoPor */
		@SuppressWarnings("unused")
		Join<Complemento, Usuario> usuario = complemento.join("inseridoPor");
		
		/*Com essa linha, pegamos o usuario que tem ligação com a tabela complemento através do campo inseridoPor, 
		 * do Join acima e, verificamos apenas os complementos que foram inseridos por usuários daquela om.  
		 */
		predicates.add(criteriaBuilder.equal(complemento.get("inseridoPor"), sistema.getUsuario().getOm().getCodigo()));
		
		if(!predicates.isEmpty()) {
			query.where(predicates.stream().toArray(Predicate[]::new));
		}

		query.select(criteriaBuilder.count(complemento));
		return manager.createQuery(query).getSingleResult();
	}
	/**############################--FINAL DO NOVO MÉTODO totalJPA2--###########################################*/
	
	
	/**#####################===INÍCIO DO MÉTODO QUE FILTRA PELA OM DO USUÁRIO LOGADO==###################**/
	@Override
	@Transactional(readOnly = true)
	public Page<Complemento> filtrarPelaOmUsuLogado(ComplementoFilter filtro, Pageable pageable, UsuarioSistema sistema) {
		
		/* Primeiro pegamos uma referência do CriteriaBuilder do EntityManager(manager),
		 * o qual podemos utilizar, as diferentes partes da consulta.
		 */		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		/* Utilizando o CriteriaBuilder, que criamos acima, podemos agora, criar um CriteriaQuery,
		 * do tipo Complemento, que descreve o que precisamos na consulta, que é uma lista apenas 
		 * com complementos da OM do usuario logado.
		 */
		CriteriaQuery<Complemento> query = criteriaBuilder.createQuery(Complemento.class);
		
		/*
		 * Com o CriteriaQuery<Complemento>, declaramos o ponto de partida da consulta que, 
		 * é a entidade Complemento e, guardamos essa entidade, na variável complemento para que,
		 * possamos utilizar posteriormente.
		 */
		Root<Complemento> complemento = query.from(Complemento.class);
		
		/* Aqui fazemos um Join entre a tabela Complemento e Usuario através da coluna inseridoPor*/
		@SuppressWarnings("unused")
		Join<Complemento, Usuario> usuario = complemento.join("inseridoPor");
		
		/*
		 * Criamos agora, uma lista dinâmica de predicados.
		 */
		List<Predicate> predicates = new ArrayList<>();
		
		/*Com essa linha, pegamos o usuario que tem ligação com a tabela complemento através do campo inseridoPor, 
		 * do Join acima e, verificamos apenas os complementos que foram inseridos por usuários daquela om.  
		 */
		predicates.add(criteriaBuilder.equal(complemento.get("inseridoPor"), sistema.getUsuario().getOm().getCodigo()));
		
		/*
		 * Verificamos agora, se o parametro foi preenchido através do ComplementoFilter ao qual,
		 * demos o nome de filtro se estiver, adicionamos na lista de predicados.
		 */
		TypedQuery<Complemento> queryResult = adicionarFiltro(filtro, criteriaBuilder, query, complemento, predicates);
		
		query.from(Complemento.class);
				
		if(!predicates.isEmpty()) {
			query.where(predicates.stream().toArray(Predicate[]::new));
		}
		
//		paginacaoUtilJPA.prepararRestricoesDePaginacao2(queryResult, pageable, query, sistema);
		
		return new PageImpl<>(queryResult.getResultList(), pageable, totalJPA2(filtro, sistema));
	}
	/**#####################===FINAL DO MÉTODO QUE FILTRA PELA OM DO USUÁRIO LOGADO==###################**/		
	
		
}
