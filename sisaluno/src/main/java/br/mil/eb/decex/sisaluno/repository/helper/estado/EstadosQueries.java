package br.mil.eb.decex.sisaluno.repository.helper.estado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.model.Estado;
import br.mil.eb.decex.sisaluno.repository.filter.EstadoFilter;

public interface EstadosQueries {
	
	public Page<Estado> filtrar(EstadoFilter filtro, Pageable pageable);
}
