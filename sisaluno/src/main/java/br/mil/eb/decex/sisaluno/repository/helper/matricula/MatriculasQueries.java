package br.mil.eb.decex.sisaluno.repository.helper.matricula;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.repository.filter.MatriculaFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public interface MatriculasQueries {
	
	public Optional<Matricula> porCpf(String cpf);
	
	public Matricula buscarComItens(Long codigo);
	
	public Matricula buscarComCurso(Long codigo);
	
	public Page<Matricula> filtrarPelaOmUsuLogado(MatriculaFilter filtro, Pageable pageable, UsuarioSistema sistema);


	public Page<Matricula> filtrar(MatriculaFilter filtro,  Pageable pageable);
		
	
	
	
	
	

	

}
