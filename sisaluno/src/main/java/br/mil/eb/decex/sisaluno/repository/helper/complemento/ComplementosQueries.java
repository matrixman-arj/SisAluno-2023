package br.mil.eb.decex.sisaluno.repository.helper.complemento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.model.Complemento;
import br.mil.eb.decex.sisaluno.repository.filter.ComplementoFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public interface ComplementosQueries {
	
	public Page<Complemento> filtrar(ComplementoFilter filtro, Pageable pageable);
	
//	public Page<Complemento> filtrar(ComplementoFilter filtro, Pageable pageable);
	
	public Page<Complemento> filtrarPelaOmUsuLogado(ComplementoFilter filtro, Pageable pageable, UsuarioSistema sistema);

//	List<String> filtrarPelaOmUsuLogado(UsuarioSistema sistema);
	
}
