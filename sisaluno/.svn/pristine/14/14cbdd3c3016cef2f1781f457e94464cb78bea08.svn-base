package br.mil.eb.decex.sisaluno.repository.helper.curso;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.dto.CursoDTO;
import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.repository.filter.CursoFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public interface CursosQueries {
	
	public Page<Curso> filtrar(CursoFilter filtro, Pageable pageable);
	
	public Page<Curso> filtrarPelaOmUsuLogado(CursoFilter filtro, Pageable pageable, UsuarioSistema sistema);

	public List<CursoDTO> porSkuOuDescricao(String skuOuDescricao);	
	
}
