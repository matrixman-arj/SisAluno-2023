package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public class UsuarioSistemaConverter implements Converter<String, UsuarioSistema> {

	@Override
	public UsuarioSistema convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			UsuarioSistema usuarioSistema = new UsuarioSistema(null, null);
			usuarioSistema.toString();
			return usuarioSistema;
		}
		
		return null;
	}

}
