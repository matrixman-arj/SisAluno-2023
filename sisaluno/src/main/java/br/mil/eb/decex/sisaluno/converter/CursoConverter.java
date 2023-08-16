package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Curso;

public class CursoConverter implements Converter<String, Curso> {

	@Override
	public Curso convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Curso curso = new Curso();
			curso.setCodigo(Long.valueOf(codigo));
			return curso;
		}
		
		return null;
	}

}
