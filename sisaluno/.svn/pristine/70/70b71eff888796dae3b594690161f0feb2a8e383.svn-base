package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Npor;

public class NporConverter implements Converter<String, Npor> {

	@Override
	public Npor convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Npor npor = new Npor();
			npor.setCodigo(Long.valueOf(codigo));
			return npor;
		}
		
		return null;
	}

}
