package br.mil.eb.decex.sisaluno.service.event.usuario;

import org.springframework.util.StringUtils;

import br.mil.eb.decex.sisaluno.model.Usuario;

public class UsuarioSalvoEvent {
	
	private Usuario usuario;
	
	public UsuarioSalvoEvent(Usuario usuario) {
		
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(usuario.getFoto());
	}
	
	public boolean isNovaFoto() {
		return usuario.isNovaFoto();
	}
}
