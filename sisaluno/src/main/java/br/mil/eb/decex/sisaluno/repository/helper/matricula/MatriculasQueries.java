package br.mil.eb.decex.sisaluno.repository.helper.matricula;

import java.util.Optional;

import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.repository.filter.MatriculaFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public interface MatriculasQueries {
	
	public Optional<Matricula> porCpf(String cpf);	
		
//	public Page<Matricula> filtrar(MatriculaFilter filtro, Pageable pageable);

	public Matricula buscarComItens(Long codigo);
	
	public Matricula buscarComCurso(Long codigo);	
	

	public Page<Matricula> filtrar(MatriculaFilter filtro, Pageable pageable);

	public Page<Matricula> filtrarMatriculaPorEstEsino(MatriculaFilter filtro, Pageable pageable, UsuarioSistema sistema, Criteria criteria);	

}
