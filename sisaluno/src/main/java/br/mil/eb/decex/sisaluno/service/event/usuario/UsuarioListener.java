package br.mil.eb.decex.sisaluno.service.event.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.mil.eb.decex.sisaluno.storage.FotoStorage;

@Component
public class UsuarioListener {
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#evento.temFoto() and #evento.novaFoto")
	
	public void usuarioSalvo(UsuarioSalvoEvent evento) {		
		fotoStorage.salvar(evento.getUsuario().getFoto());		
		
	}

}
