package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Usuario;

public class UsuarioConverter implements Converter<String, Usuario> {

	@Override
	public Usuario convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Usuario usuario = new Usuario();
			usuario.setCodigo(Long.valueOf(codigo));
			return usuario;
		}
		
		return null;
	}

}
