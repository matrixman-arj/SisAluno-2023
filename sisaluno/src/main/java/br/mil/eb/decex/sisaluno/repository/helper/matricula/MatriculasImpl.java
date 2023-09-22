package br.mil.eb.decex.sisaluno.repository.helper.matricula;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Aluno;
import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.model.Usuario;
import br.mil.eb.decex.sisaluno.repository.filter.MatriculaFilter;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtil;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtilJPA;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public class MatriculasImpl implements MatriculasQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	@Autowired
	private PaginacaoUtilJPA paginacaoUtilJPA;
	
	
	@Override
	public Optional<Matricula> porCpf(String cpf) {		
		return manager
				.createQuery("from Matricula where(cpf) = (:cpf)", Matricula.class)
				.setParameter("cpf", cpf).getResultList().stream().findFirst();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Matricula> filtrar(MatriculaFilter filtro,  Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Matricula.class);
		
		
		paginacaoUtil.preparar(criteria, pageable);		
		adicionarFiltro(filtro, null, null, null, null);
		
		List<Matricula> filtradas = criteria.list();		
		filtradas.forEach(m -> Hibernate.initialize(m.getCodigo()));
		return new PageImpl<>(filtradas, pageable, total(filtro));
	
  }
		
	@Transactional(readOnly = true)
	@Override
	public Matricula buscarComCurso(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Matricula.class);
		criteria.createAlias("curso", "c", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Matricula) criteria.list();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Matricula buscarComItens(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Matricula.class);
		criteria.createAlias("itens", "i", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Matricula) criteria.uniqueResult();
	}
		
	private Long total(MatriculaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Matricula.class);
		adicionarFiltro(filtro, null, null, null, null);
		criteria.setProjection(Projections.rowCount());		
		return (Long) criteria.uniqueResult();
	}
	
	/**############################--INÍCIO DO NOVO MÉTODO totalJPA2--###########################################*/
	private Long totalJPA2(MatriculaFilter filtro, UsuarioSistema sistema) {
		
		/* Primeiro pegamos uma referência do CriteriaBuilder do EntityManager(manager),
		 * o qual podemos utilizar, as diferentes partes da consulta.
		 */	
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		/* Utilizando o CriteriaBuilder, que criamos acima, podemos agora, criar um CriteriaQuery,
		 * do tipo Long, que descreve o que precisamos na consulta que, nesse caso é aquantidade total de registros para a OM do usuário logado.
		 */
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		
		/* Com o CriteriaQuery<Aluno>, declaramos o ponto de partida da consulta que, 
		 * é a entidade Aluno e, guardamos essa entidade, na variável aluno para que,
		 * possamos utilizar posteriormente.
		 */
		Root<Aluno> aluno = query.from(Aluno.class);
		
		/* Criamos agora, uma lista dinâmica de predicados.*/
		List<Predicate> predicates = new ArrayList<>();
		
		/* Aqui fazemos um Join entre a tabela Aluno e Usuario através da coluna inseridoPor */
		@SuppressWarnings("unused")
		Join<Aluno, Usuario> usuario = aluno.join("inseridoPor");
		
		/*Com essa linha, pegamos o usuario que tem ligação com a tabela aluno através do campo inseridoPor, 
		 * do Join acima e, verificamos apenas os alunos que foram inseridos por usuários daquela om.  
		 */
		predicates.add(criteriaBuilder.equal(aluno.get("inseridoPor"), sistema.getUsuario().getOm().getCodigo()));
		
		if(!predicates.isEmpty()) {
			query.where(predicates.stream().toArray(Predicate[]::new));
		}

		query.select(criteriaBuilder.count(aluno));
		return manager.createQuery(query).getSingleResult();
	}
	/**############################--FINAL DO NOVO MÉTODO totalJPA2--###########################################*/

	private TypedQuery<Matricula> adicionarFiltro(EntityManager entityManager,MatriculaFilter filtro) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Matricula> query = criteriaBuilder.createQuery(Matricula.class);
        Root<Matricula> matriculaRoot = query.from(Matricula.class);

        // Crie aliases para as classes relacionadas
        Join<Matricula, Aluno> alunoJoin = matriculaRoot.join("aluno");

        // Crie uma lista de predicados para as condições da consulta
        List<Predicate> predicates = new ArrayList<>();
        
        
     // Verifique se o nome do aluno foi informado no filtro
        if (filtro.getNomeAluno() != null && !filtro.getNomeAluno().isEmpty()) {
            predicates.add(criteriaBuilder.like(alunoJoin.get("nome"), "%" + filtro.getNomeAluno() + "%"));
        }

        // Verifique se o CPF do aluno foi informado no filtro
        if (filtro.getCpf() != null && !filtro.getCpf().isEmpty()) {
            predicates.add(criteriaBuilder.equal(alunoJoin.get("cpf"), filtro.getCpf()));
        }

        // Aplicar os predicados à consulta usando o operador "AND"
        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
		
		
		
			// Crie um alias para o aluno
	        Selection<Aluno> alunoAlias = alunoJoin.alias("a");
	        
	     // Crie uma seleção que inclua o aluno (ou outros atributos que você deseja retornar)
	        Selection<Matricula> selection = criteriaBuilder.construct(
	            Matricula.class,
	            matricula.get("aluno"),	            
	            alunoAlias // Isso incluirá o aluno com alias
	        );
			
	   				
		if(filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {				
				Predicate condition = criteriaBuilder.equal(matricula.get("codigo"), filtro.getCodigo());
				query.where(condition);
			}
			
			if (!StringUtils.isEmpty(filtro.getCpf())) {				
				query.select(matricula.get("aluno").get("cpf"));
				Predicate condition = criteriaBuilder.equal(matricula.get("aluno").get("cpf"), filtro.getCpf());
				query.where(condition);
			}
									
			if (!StringUtils.isEmpty(filtro.getMatricula())) {				
				Predicate condition = criteriaBuilder.equal(matricula.get("numeroMatricula"), filtro.getMatricula());
				query.where(condition);
			}			
		
			if (!StringUtils.isEmpty(filtro.getNomeAluno())) {				
				Predicate condition = criteriaBuilder.like(matricula.get("a.nome"), filtro.getNomeAluno());
				query.where(condition);
			}			
					
			if (!StringUtils.isEmpty(filtro.getAnoLetivo())) {
				Predicate condition = criteriaBuilder.equal(matricula.get("anoLetivo"), filtro.getAnoLetivo());
				query.where(condition);
			}
			
			if (!StringUtils.isEmpty(filtro.getDataCriacao())) {
				Predicate condition = criteriaBuilder.equal(matricula.get("dataCriacao"), filtro.getDataCriacao());	
				query.where(condition);
			}
			if (!StringUtils.isEmpty(filtro.getDataInicioCurso())) {
				Predicate condition = criteriaBuilder.equal(matricula.get("dataInicioCurso"), filtro.getDataInicioCurso());	
				query.where(condition);
			}
			
			if (!StringUtils.isEmpty(filtro.getDataFinalCurso())) {
				Predicate condition = criteriaBuilder.equal(matricula.get("dataFinalCurso"), filtro.getDataFinalCurso());	
				query.where(condition);
			}
			
//			if (filtro.getCursos() != null && !filtro.getCursos().isEmpty()) {
//				List<Criterion> subqueries = new ArrayList<>();
//				for (Long codigoCurso : filtro.getCursos().stream().mapToLong(Curso::getCodigo).toArray()) {
//					DetachedCriteria dc = DetachedCriteria.forClass(ItemMatricula.class);
//					dc.add(Restrictions.eq("id.curso.codigo", codigoCurso));
//					dc.setProjection(Projections.property("id.matricula"));
//					
//					subqueries.add(Subqueries.propertyIn("codigo", dc));
//				}
//				
//				Criterion[] criterions = new Criterion[subqueries.size()];
//				criteria.add(Restrictions.and(subqueries.toArray(criterions)));
//			
//		}
	}
		/* Depois disso, criamos uma instância TypedQuery<Aluno> do nosso CriteriaQuery.*/
		TypedQuery<Matricula> typedQuery = manager.createQuery(query);
		return typedQuery;
}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Matricula> filtrarMatriculaPorEstEsino(MatriculaFilter filtro, Pageable pageable, UsuarioSistema sistema, Criteria criteria) {
		
		/* Primeiro pegamos uma referência do CriteriaBuilder do EntityManager(manager),
		 * o qual podemos utilizar, as diferentes partes da consulta.
		 */		
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		/* Utilizando o CriteriaBuilder, que criamos acima, podemos agora, criar um CriteriaQuery,
		 * do tipo Aluno, que descreve o que precisamos na consulta, que é uma lista apenas 
		 * com alunos da OM do usuario logado.
		 */
		CriteriaQuery<Matricula> query = criteriaBuilder.createQuery(Matricula.class);
		
		/*
		 * Com o CriteriaQuery<Aluno>, declaramos o ponto de partida da consulta que, 
		 * é a entidade Aluno e, guardamos essa entidade, na variável aluno para que,
		 * possamos utilizar posteriormente.
		 */
		Root<Matricula> matricula = query.from(Matricula.class);
		
		/* Aqui fazemos um Join entre a tabela Aluno e Usuario através da coluna inseridoPor*/
		@SuppressWarnings("unused")
		Join<Matricula, Usuario> usuario = matricula.join("usuario");
		
		/*
		 * Criamos agora, uma lista dinâmica de predicados.
		 */
		List<Predicate> predicates = new ArrayList<>();
		
		/*Com essa linha, pegamos o usuario que tem ligação com a tabela aluno através do campo inseridoPor, 
		 * do Join acima e, verificamos apenas os alunos que foram inseridos por usuários daquela om.  
		 */
		predicates.add(criteriaBuilder.equal(matricula.get("usuario"), sistema.getUsuario().getOm().getCodigo()));
		
		/*
		 * Verificamos agora, se o parametro foi preenchido através do AlunoFilter ao qual,
		 * demos o nome de filtro se estiver, adicionamos na lista de predicados.
		 */
		TypedQuery<Matricula> queryResult = adicionarFiltro(filtro, criteriaBuilder, query, matricula, predicates);
		
		query.from(Matricula.class);
				
		if(!predicates.isEmpty()) {
			query.where(predicates.stream().toArray(Predicate[]::new));
		}
		
		paginacaoUtilJPA.prepararRestricoesDePaginacao2(queryResult, pageable, query, sistema);
		
		return new PageImpl<>(queryResult.getResultList(), pageable, totalJPA2(filtro, sistema));
	}


	/**#####################===FINAL DO MÉTODO QUE FILTRA PELA OM DO USUÁRIO LOGADO==###################**/	

}
