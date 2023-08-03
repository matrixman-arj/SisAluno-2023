package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.RegiaoMilitar;

public class RegiaoMilitarConverter implements Converter<String, RegiaoMilitar> {

	@Override
	public RegiaoMilitar convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			RegiaoMilitar regiaoMilitar = new RegiaoMilitar();
			regiaoMilitar.setCodigo(Long.valueOf(codigo));
			return regiaoMilitar;
		}
		
		return null;
	}

}
