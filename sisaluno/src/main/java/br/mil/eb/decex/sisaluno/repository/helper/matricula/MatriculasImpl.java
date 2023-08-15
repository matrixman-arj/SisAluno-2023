package br.mil.eb.decex.sisaluno.repository.helper.matricula;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Matricula> filtrar(MatriculaFilter filtro,  Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Matricula.class);
		
		
		paginacaoUtil.preparar(criteria, pageable);		
		adicionarFiltro(filtro, criteria);
		
		List<Matricula> filtradas = criteria.list();		
		
		return new PageImpl<>(filtradas, pageable, total(filtro));
	
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
				criteria.add(Restrictions.ilike("aluno", filtro.getAluno().getNome()));
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
			
		}
	}
}	
	
