package br.mil.eb.decex.sisaluno.repository.helper.matricula;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.repository.filter.MatriculaFilter;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtil;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public class MatriculasImpl implements MatriculasQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	
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
		adicionarFiltro(filtro, criteria);
		
		List<Matricula> filtradas = criteria.list();		
		filtradas.forEach(m -> Hibernate.initialize(m.getCodigo()));
		return new PageImpl<>(filtradas, pageable, total(filtro));
	
  }
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Matricula> buscarMatriculasPorOM(MatriculaFilter filtro,  Pageable pageable, @AuthenticationPrincipal UsuarioSistema sistema) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Matricula.class);
				
		criteria.add(Restrictions.eq("om.codigo", sistema.getUsuario().getOm().getCodigo()));
		
		paginacaoUtil.preparar(criteria, pageable);		
		adicionarFiltro(filtro, criteria);
		
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
	
//	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
//	public Page<Matricula> buscarMatriculasPorOM(MatriculaFilter filtro, Pageable pageable, UsuarioSistema sistema, Criteria criteria) {
//        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
//        CriteriaQuery<Matricula> query = criteriaBuilder.createQuery(Matricula.class);
//        Root<Matricula> matriculaRoot = query.from(Matricula.class);
//
//        // Crie uma junção com a entidade OM, supondo que Matricula tenha um relacionamento com OM
//        Join<Matricula, OrganizacaoMilitar> omJoin = matriculaRoot.join("om");
//
//        // Verifique se a OM da matrícula é igual à OM do usuário logado
//        Predicate condicaoOM = criteriaBuilder.equal(omJoin, sistema);
//
//        // Aplicar a condição à consulta
//        query.where(condicaoOM);
//        
//        paginacaoUtil.preparar(criteria, pageable);		
//		adicionarFiltro(filtro, criteria);
//
//        return (Page<Matricula>) manager.createQuery(query).getResultList();
//    }
	
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
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());		
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(MatriculaFilter filtro, Criteria criteria) {
		criteria.createAlias("itens", "i");
		criteria.createAlias("aluno", "a");
		
		if(filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCodigo())) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}
			
			if (!StringUtils.isEmpty(filtro.getCpf())) {
				criteria.add(Restrictions.eq("a.cpf", filtro.getCpf()));
			}
									
			if (!StringUtils.isEmpty(filtro.getMatricula())) {
				criteria.add(Restrictions.eq("numeroMatricula", filtro.getMatricula()));
			}			
		
			if (!StringUtils.isEmpty(filtro.getNomeAluno())) {
				criteria.add(Restrictions.ilike("a.nome", filtro.getNomeAluno(), MatchMode.ANYWHERE));
			}			
					
			if (!StringUtils.isEmpty(filtro.getAnoLetivo())) {
				criteria.add(Restrictions.eq("anoLetivo", filtro.getAnoLetivo()));
			}
			
			if (!StringUtils.isEmpty(filtro.getDataCriacao())) {
				criteria.add(Restrictions.eq("dataCriacao", filtro.getDataCriacao()));
			}
			if (!StringUtils.isEmpty(filtro.getDataInicioCurso())) {
				criteria.add(Restrictions.eq("dataInicioCurso", filtro.getDataInicioCurso()));
			}
			
			if (!StringUtils.isEmpty(filtro.getDataFinalCurso())) {
				criteria.add(Restrictions.eq("dataFinalCurso", filtro.getDataFinalCurso()));
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
}


	
}
