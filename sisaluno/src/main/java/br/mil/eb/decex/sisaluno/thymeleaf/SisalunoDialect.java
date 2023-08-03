package br.mil.eb.decex.sisaluno.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.mil.eb.decex.sisaluno.thymeleaf.processor.ClassForErrorAtributeTagProcessor;
import br.mil.eb.decex.sisaluno.thymeleaf.processor.MessageElementTagProcessor;
import br.mil.eb.decex.sisaluno.thymeleaf.processor.OrderElementTagProcessor;
import br.mil.eb.decex.sisaluno.thymeleaf.processor.OrderElementTagProcessor2;
import br.mil.eb.decex.sisaluno.thymeleaf.processor.PaginationElementTagProcessor;
import br.mil.eb.decex.sisaluno.thymeleaf.processor.PaginationElementTagProcessor2;

public class SisalunoDialect extends AbstractProcessorDialect{
	
	public SisalunoDialect() {
		super("DECEx Sisaluno", "sisaluno", StandardDialect.PROCESSOR_PRECEDENCE);
		
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {	
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAtributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor2(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor2(dialectPrefix));
		
		return processadores;
		
	}

}
