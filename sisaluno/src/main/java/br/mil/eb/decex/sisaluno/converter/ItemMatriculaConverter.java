package br.mil.eb.decex.sisaluno.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.ItemMatricula;

public class ItemMatriculaConverter implements Converter<String, ItemMatricula> {

	@Override
	public ItemMatricula convert(String codigo) {
		if (!StringUtils.isEmpty(codigo)) {
			ItemMatricula itemMatricula = new ItemMatricula();
			itemMatricula.setCodigo(Long.valueOf(codigo));
			return itemMatricula;
		}
		
		return null;
	}

}
