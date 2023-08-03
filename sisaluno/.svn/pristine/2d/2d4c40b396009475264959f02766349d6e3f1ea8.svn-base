package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Ano;

public class AnoConverter implements Converter<String, Ano> {

	@Override
	public Ano convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Ano ano = new Ano();
			ano.setCodigo(Long.valueOf(codigo));
			return ano;
		}
		
		return null;
	}

}
