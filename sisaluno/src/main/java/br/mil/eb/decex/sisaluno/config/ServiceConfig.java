package br.mil.eb.decex.sisaluno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.mil.eb.decex.sisaluno.service.CadastroUsuarioService;
import br.mil.eb.decex.sisaluno.storage.FotoStorage;
import br.mil.eb.decex.sisaluno.storage.local.FotoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroUsuarioService.class)
public class ServiceConfig {
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}
	
	

}
