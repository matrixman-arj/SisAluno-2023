package br.mil.eb.decex.sisaluno.repository.helper.aluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.model.Aluno;
import br.mil.eb.decex.sisaluno.repository.filter.AlunoFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public interface AlunosQueries {
	
	public Page<Aluno> filtrar(AlunoFilter filtro, Pageable pageable);
	
	public Page<Aluno> filtrarPelaOmUsuLogado(AlunoFilter filtro, Pageable pageable, UsuarioSistema sistema);

//	List<String> filtrarPelaOmUsuLogado(UsuarioSistema sistema);
	
}
