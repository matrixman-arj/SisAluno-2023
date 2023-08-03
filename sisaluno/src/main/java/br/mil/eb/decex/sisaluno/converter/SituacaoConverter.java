package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.SituacaoNoCurso;

public class SituacaoConverter implements Converter<String, SituacaoNoCurso> {

	@Override
	public SituacaoNoCurso convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			SituacaoNoCurso situacao = new SituacaoNoCurso();
			situacao.setCodigo(Long.valueOf(codigo));
			return situacao;
		}
		
		return null;
	}

}
