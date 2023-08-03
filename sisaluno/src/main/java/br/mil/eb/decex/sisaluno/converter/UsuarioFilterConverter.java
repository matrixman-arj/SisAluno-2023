package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.repository.filter.UsuarioFilter;

public class UsuarioFilterConverter implements Converter<String, UsuarioFilter> {

	@Override
	public UsuarioFilter convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			UsuarioFilter usuarioFilter = new UsuarioFilter();
			usuarioFilter.toString();
			return usuarioFilter;
		}
		
		return null;
	}

}
