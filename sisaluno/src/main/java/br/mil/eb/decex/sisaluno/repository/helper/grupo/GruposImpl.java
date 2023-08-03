package br.mil.eb.decex.sisaluno.repository.helper.grupo;

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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Grupo;
import br.mil.eb.decex.sisaluno.model.Permissao;
import br.mil.eb.decex.sisaluno.model.GrupoPermissao;
import br.mil.eb.decex.sisaluno.repository.filter.GrupoFilter;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtil;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public class GruposImpl implements GruposQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@Override
	public Optional<Grupo> porNome(String nome) {
		return manager
				.createQuery("from Grupo where(nome) = (:nome)", Grupo.class)
				.setParameter("nome", nome).getResultList().stream().findFirst();
	}
	
			
	@Override
	public List<Grupo> porGrupo(@AuthenticationPrincipal UsuarioSistema sistema) {
		List<Grupo> gruposGrupo = sistema.getUsuario().getGrupos();
		for(Grupo grupoGrupo : gruposGrupo) {
		return manager
				.createQuery("from Grupo where (codigo) >= (:codigo)", Grupo.class)
				.setParameter("codigo", grupoGrupo.getCodigo())
				.getResultList();			
		}
		
		return gruposGrupo;	
		
	}
		
	@Override
	public List<String> permissoes(Grupo grupo) {
		return manager.createQuery(
				"select distinct p.nome from Grupo g inner join g.permissoes g inner join g.permissoes p where g = :grupo", String.class)
				.setParameter("grupo", grupo)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Page<Grupo> filtrar(GrupoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Grupo.class);
				
		paginacaoUtil.preparar(criteria, pageable);		
		adicionarFiltro(filtro, criteria);
		
		List<Grupo> filtrados = criteria.list();
		filtrados.forEach(g -> Hibernate.initialize(g.getPermissoes()));
		return new PageImpl<>(filtrados, pageable, total(filtro));
	}
	
	@Transactional(readOnly = true)
	@Override
	public Grupo buscarComPermissoes(Long codigo) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Grupo.class);
		criteria.createAlias("permissoes", "p", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Grupo) criteria.uniqueResult();
		
	}
	
	private Long total(GrupoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Grupo.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(GrupoFilter filtro, Criteria criteria) {
		if (filtro != null) {
			
			if (!StringUtils.isEmpty(filtro.getNome())) {
				criteria.add(Restrictions.eq("nome", filtro.getNome()));
			}					 
//			 criteria.createAlias("grupos", "g", JoinType.LEFT_OUTER_JOIN);
				if (filtro.getPermissoes() != null && !filtro.getPermissoes().isEmpty()) {
					List<Criterion> subqueries = new ArrayList<>();
					for (Long codigoPermissao : filtro.getPermissoes().stream().mapToLong(Permissao::getCodigo).toArray()) {
						DetachedCriteria dc = DetachedCriteria.forClass(GrupoPermissao.class);
						dc.add(Restrictions.eq("id.permissao.codigo", codigoPermissao));
						dc.setProjection(Projections.property("id.grupo"));
						
						subqueries.add(Subqueries.propertyIn("codigo", dc));
					}
					
					Criterion[] criterions = new Criterion[subqueries.size()];
					criteria.add(Restrictions.and(subqueries.toArray(criterions)));
				}
		}
			 
	}

}
