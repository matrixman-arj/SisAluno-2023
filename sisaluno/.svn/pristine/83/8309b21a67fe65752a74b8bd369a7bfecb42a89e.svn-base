package br.mil.eb.decex.sisaluno.config.init;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.mil.eb.decex.sisaluno.config.JPAConfig;
import br.mil.eb.decex.sisaluno.config.SecurityConfig;
import br.mil.eb.decex.sisaluno.config.ServiceConfig;
import br.mil.eb.decex.sisaluno.config.WebConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class<?>[] {JPAConfig.class, ServiceConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceRequestEncoding(true);
		
		return new Filter[] { characterEncodingFilter };
	}
	
	@Override	
	protected void customizeRegistration(Dynamic registration) {	
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

}
