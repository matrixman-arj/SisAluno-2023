package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Uete;

public class UeteConverter implements Converter<String, Uete> {

	@Override
	public Uete convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Uete uete = new Uete();
			uete.setCodigo(Long.valueOf(codigo));
			return uete;
		}
		
		return null;
	}

}
