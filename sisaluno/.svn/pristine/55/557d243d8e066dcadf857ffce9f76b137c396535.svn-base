package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Aluno;

public class AlunoConverter implements Converter<String, Aluno> {

	@Override
	public Aluno convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			Aluno aluno = new Aluno();
			aluno.setCodigo(Long.valueOf(codigo));
			return aluno;
		}
		
		return null;
	}

}
