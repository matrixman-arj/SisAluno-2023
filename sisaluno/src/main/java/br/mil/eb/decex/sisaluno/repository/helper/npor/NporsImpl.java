package br.mil.eb.decex.sisaluno.repository.helper.npor;

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

import br.mil.eb.decex.sisaluno.model.Npor;
import br.mil.eb.decex.sisaluno.repository.filter.NporFilter;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtil;


public class NporsImpl  implements NporsQueries{
	
	@PersistenceContext
	private EntityManager manager;

	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Npor> filtrar(NporFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Npor.class);
		
		paginacaoUtil.preparar(criteria, pageable);	
		adicionarFiltro(filtro, criteria);
		criteria.createAlias("estado", "e", JoinType.LEFT_OUTER_JOIN);
		criteria.createAlias("e.regiao", "r", JoinType.LEFT_OUTER_JOIN);
		
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}
	
	@Override
	public List<Npor> porOrdem(int ordem) {
		return manager
				.createQuery("from Npor np ORDER BY np.ordem", Npor.class)
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
//	public List<Uete> porPai(@AuthenticationPrincipal UsuarioSistema sistema) {
//		Criteria criteria = manager.unwrap(Session.class).createCriteria(Uete.class);
//		criteria.createAlias("omPai", "pai", JoinType.INNER_JOIN);
//		criteria.add(Restrictions.eq("omPai.codigo", sistema.getUsuario().getOm().getCodigo()));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		return criteria.list();
//		
//		
//	}
	
//	
//	@SuppressWarnings("unchecked")
//	@Transactional(readOnly = true)
//	@Override
//	public List<Uete> buscarComPai(@AuthenticationPrincipal UsuarioSistema sistema) {
//		Criteria criteria = manager.unwrap(Session.class).createCriteria(Uete.class);
//		criteria.createAlias("omPai", "pai", JoinType.INNER_JOIN);
//		criteria.add(Restrictions.eq("omPai.codigo", sistema.getUsuario().getOm().getCodigo()));
////		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		return criteria.list();
		
		
//	}	
	
	private Long total(NporFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Npor.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(NporFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getSigla())) {
				criteria.add(Restrictions.ilike("sigla", filtro.getSigla(), MatchMode.ANYWHERE));
		 }			 
			 
			 if (!StringUtils.isEmpty(filtro.getDescricao())) {
					criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			 }
			 
			 if (!StringUtils.isEmpty(filtro.getRegiaoMilitar())) {
					criteria.add(Restrictions.eq("regiaoMilitar", filtro.getRegiaoMilitar()));
				}
			 			 			 
		}
	}	
			
}
