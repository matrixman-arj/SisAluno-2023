package br.mil.eb.decex.sisaluno.repository.helper.aluno;

import java.util.List;

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

import br.mil.eb.decex.sisaluno.model.Aluno;
import br.mil.eb.decex.sisaluno.repository.filter.AlunoFilter;
import br.mil.eb.decex.sisaluno.repository.paginacao.PaginacaoUtil;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public class AlunosImpl implements AlunosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Aluno> filtrar(AlunoFilter filtro,  Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Aluno.class);
		
		
		paginacaoUtil.preparar(criteria, pageable);		
		adicionarFiltro(filtro, criteria);
		
		List<Aluno> filtrados = criteria.list();		
		
		return new PageImpl<>(filtrados, pageable, total(filtro));
	
  }
	
	
		
	private Long total(AlunoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Aluno.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());		
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(AlunoFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if (!StringUtils.isEmpty(filtro.getCpf())) {
				criteria.add(Restrictions.eq("cpf", filtro.getCpf()));
			}
			
			
			}
		}



	@Override
	public Page<Aluno> filtrarPelaOmUsuLogado(AlunoFilter filtro, Pageable pageable, UsuarioSistema sistema) {
		// TODO Auto-generated method stub
		return null;
	}
		
	
}
