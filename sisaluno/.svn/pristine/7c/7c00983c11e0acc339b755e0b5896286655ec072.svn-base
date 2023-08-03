package br.mil.eb.decex.sisaluno.repository.helper.om;

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

import br.mil.eb.decex.sisaluno.model.OrganizacaoMilitar;
import br.mil.eb.decex.sisaluno.repository.filter.OMFilter;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtil;


public class OMsImpl  implements OMsQueries{
	
	@PersistenceContext
	private EntityManager manager;

	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<OrganizacaoMilitar> filtrar(OMFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(OrganizacaoMilitar.class);
		
		paginacaoUtil.preparar(criteria, pageable);	
		adicionarFiltro(filtro, criteria);
		criteria.createAlias("estado", "e", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("e.regiao", "r", JoinType.LEFT_OUTER_JOIN);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public List<OrganizacaoMilitar> porOrdem(int ordem) {
		return manager
				.createQuery("from OrganizacaoMilitar om ORDER BY om.ordem", OrganizacaoMilitar.class)
				.getResultList();
	}
	
//	@Override
//	public List<OrganizacaoMilitar> porPai(Long om_pai_codigo) {
//		return manager
//				.createQuery("from OrganizacaoMilitar om where(om_pai_codigo) = (:om_pai_codigo) ORDER BY om.ordem", OrganizacaoMilitar.class)
//				.setParameter("om_pai_codigo", om_pai_codigo).getResultList();
//	}
	
	
//	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
//	@Override
//	public List<OrganizacaoMilitar> porPai(@AuthenticationPrincipal UsuarioSistema sistema) {
//		Criteria criteria = manager.unwrap(Session.class).createCriteria(OrganizacaoMilitar.class);
//		criteria.createAlias("omPai", "pai", JoinType.INNER_JOIN);
//		criteria.add(Restrictions.eq("omPai.codigo", sistema.getUsuario().getOm().getCodigo()));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		return criteria.list();
//		
//		
//	}
	
	
//	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
//	@Override
//	public List<OrganizacaoMilitar> buscarComPai(@AuthenticationPrincipal UsuarioSistema sistema) {
//		Criteria criteria = manager.unwrap(Session.class).createCriteria(OrganizacaoMilitar.class);
//		criteria.createAlias("omPai", "pai", JoinType.INNER_JOIN);
//		criteria.add(Restrictions.eq("omPai.codigo", sistema.getUsuario().getOm().getCodigo()));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		return criteria.list();
//		
//		
//	}	
	
	private Long total(OMFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(OrganizacaoMilitar.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(OMFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getSigla())) {
				criteria.add(Restrictions.ilike("sigla", filtro.getSigla(), MatchMode.ANYWHERE));
		 }			 
			 
			 if (!StringUtils.isEmpty(filtro.getDescricao())) {
					criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			 }
			 
//			 if (filtro.getSubordinacao() != null) {
//				 criteria.add(Restrictions.eq("omPai", filtro.getSubordinacao()));
//			 }
			 			 			 
		}
	}	
			
}
