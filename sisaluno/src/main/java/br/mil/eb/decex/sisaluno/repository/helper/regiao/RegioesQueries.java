package br.mil.eb.decex.sisaluno.repository.helper.regiao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.model.Regiao;
import br.mil.eb.decex.sisaluno.repository.filter.RegiaoFilter;

public interface RegioesQueries {
	
	public Page<Regiao> filtrar(RegiaoFilter filtro, Pageable pageable);
}
