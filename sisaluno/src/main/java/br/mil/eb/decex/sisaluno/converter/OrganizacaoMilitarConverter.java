package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.OrganizacaoMilitar;

public class OrganizacaoMilitarConverter implements Converter<String, OrganizacaoMilitar> {

	@Override
	public OrganizacaoMilitar convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			OrganizacaoMilitar om = new OrganizacaoMilitar();
			om.setCodigo(Long.valueOf(codigo));
			return om;
		}
		
		return null;
	}

}
