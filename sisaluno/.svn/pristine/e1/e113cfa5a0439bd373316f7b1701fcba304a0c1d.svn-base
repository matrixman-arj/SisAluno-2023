package br.mil.eb.decex.sisaluno.repository.helper.estado;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.StringUtils;
import br.mil.eb.decex.sisaluno.model.Estado;
import br.mil.eb.decex.sisaluno.repository.filter.EstadoFilter;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtil;

public class EstadosImpl implements EstadosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Estado> filtrar(EstadoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estado.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		criteria.createAlias("regiao", "r");
				
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}	

	private Long total(EstadoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estado.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(EstadoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			if (filtro.getRegiao() != null) {
				criteria.add(Restrictions.eq("regiao", filtro.getRegiao()));
			}
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
			}
		}
	}	

}
