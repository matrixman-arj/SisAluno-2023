package br.mil.eb.decex.sisaluno.repository.helper.matricula;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.model.ItemMatricula;
import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.repository.filter.MatriculaFilter;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtil;

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
	
	@Override
	public Optional<Matricula> porCpfAlunoEAnoLetivo (String identidade) {
		Matricula matricula = new Matricula();
		return manager
				.createQuery("from Matricula where(cpfAluno) = (:cpfAluno) and (anoLetivo) = (:anoLetivo)", Matricula.class)
				.setParameter("cpfAluno", matricula.getAluno().getCpf())
				.setParameter("anoLetivo", matricula.getAnoLetivo())
				.getResultList().stream().findFirst();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Matricula> filtrar(MatriculaFilter filtro,  Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Matricula.class);
		
		
		paginacaoUtil.preparar(criteria, pageable);		
		adicionarFiltro(filtro, criteria);
		
		List<Matricula> filtradas = criteria.list();		
		filtradas.forEach(m -> Hibernate.initialize(m.getItens()));
		return new PageImpl<>(filtradas, pageable, total(filtro));
	
  }
	
	@Transactional(readOnly = true)
	@Override
	public Matricula buscarComCurso(Long codigo) {
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
		if(filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCpf())) {
				criteria.add(Restrictions.eq("cpf", filtro.getMatricula().getAluno().getCpf()));
			}
									
			if (!StringUtils.isEmpty(filtro.getMatricula())) {
				criteria.add(Restrictions.eq("matricula", filtro.getMatricula().getNumeroMatricula()));
			}			
		
			if (!StringUtils.isEmpty(filtro.getAluno())) {
				criteria.add(Restrictions.ilike("aluno", filtro.getAluno().getNome(), MatchMode.ANYWHERE));
			}			
					
			if (!StringUtils.isEmpty(filtro.getAnoLetivo())) {
				criteria.add(Restrictions.eq("anoLetivo", filtro.getAnoLetivo()));
			}
			
			if (!StringUtils.isEmpty(filtro.getDataCriacao())) {
				criteria.add(Restrictions.eq("dataCriacao", filtro.getMatricula().getDataCriacao()));
			}
			if (!StringUtils.isEmpty(filtro.getDataInicioCurso())) {
				criteria.add(Restrictions.eq("dataInicioCurso", filtro.getMatricula().getDataInicioCurso()));
			}
			
			if (!StringUtils.isEmpty(filtro.getDataFinalCurso())) {
				criteria.add(Restrictions.eq("dataFinalCurso", filtro.getMatricula().getDataFinalCurso()));
			}
			
			if (filtro.getCursos() != null && !filtro.getCursos().isEmpty()) {
				List<Criterion> subqueries = new ArrayList<>();
				for (Long codigoCurso : filtro.getCursos().stream().mapToLong(Curso::getCodigo).toArray()) {
					DetachedCriteria dc = DetachedCriteria.forClass(ItemMatricula.class);
					dc.add(Restrictions.eq("id.curso.codigo", codigoCurso));
					dc.setProjection(Projections.property("id.matricula"));
					
					subqueries.add(Subqueries.propertyIn("codigo", dc));
				}
				
				Criterion[] criterions = new Criterion[subqueries.size()];
				criteria.add(Restrictions.and(subqueries.toArray(criterions)));
			
		}
	}
}	

}
