package br.mil.eb.decex.sisaluno.repository.helper.permissao;

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

import br.mil.eb.decex.sisaluno.model.Permissao;
import br.mil.eb.decex.sisaluno.repository.filter.PermissaoFilter;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtil;


public class PermissaoImpl  implements PermissoesQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Permissao> filtrar(PermissaoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Permissao.class);
		
		paginacaoUtil.preparar(criteria, pageable);	
		adicionarFiltro(filtro, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Transactional(readOnly = true)
	@Override
	public Permissao buscarComGrupo(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Permissao.class);
		criteria.createAlias("permissoes", "p", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Permissao) criteria.uniqueResult();
		
	}	
	
	private Long total(PermissaoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Permissao.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(PermissaoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		 }			 
			 			 
		}
	}
	
}
