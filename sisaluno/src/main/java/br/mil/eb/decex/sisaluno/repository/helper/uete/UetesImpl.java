package br.mil.eb.decex.sisaluno.repository.helper.uete;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Uete;
import br.mil.eb.decex.sisaluno.repository.filter.UeteFilter;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtil;


public class UetesImpl  implements UetesQueries{
	
	@PersistenceContext
	private EntityManager manager;

	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Uete> filtrar(UeteFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Uete.class);
		
		paginacaoUtil.preparar(criteria, pageable);	
		adicionarFiltro(filtro, criteria);
		criteria.createAlias("estado", "e", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("e.regiao", "r", JoinType.LEFT_OUTER_JOIN);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	private Long total(UeteFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Uete.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	@Override
	public List<Uete> porOrdem(int ordem) {
		return manager
				.createQuery("from Uete ue ORDER BY ue.ordem", Uete.class)
				.getResultList();
	}

	
	
	private void adicionarFiltro(UeteFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getSigla())) {
				criteria.add(Restrictions.ilike("sigla", filtro.getSigla(), MatchMode.ANYWHERE));
		 }			 
			 
			 if (!StringUtils.isEmpty(filtro.getDescricao())) {
					criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			 }
			 
			 			 			 
		}
	}	
			
}
