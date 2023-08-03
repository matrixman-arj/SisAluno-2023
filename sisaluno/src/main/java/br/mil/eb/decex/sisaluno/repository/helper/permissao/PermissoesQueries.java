package br.mil.eb.decex.sisaluno.repository.helper.permissao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.model.Permissao;
import br.mil.eb.decex.sisaluno.repository.filter.PermissaoFilter;

public interface PermissoesQueries {
	
			
	public Page<Permissao> filtrar(PermissaoFilter filtro, Pageable pageable);

	Permissao buscarComGrupo(Long codigo);

}
