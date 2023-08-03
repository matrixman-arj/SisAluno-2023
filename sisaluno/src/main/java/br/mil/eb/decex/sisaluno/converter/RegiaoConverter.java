package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Regiao;

public class RegiaoConverter implements Converter<String, Regiao> {

	@Override
	public Regiao convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Regiao regiao = new Regiao();
			regiao.setCodigo(Long.valueOf(codigo));
			return regiao;
		}
		
		return null;
	}

}
