package br.mil.eb.decex.sisaluno.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.mil.eb.decex.sisaluno.model.Usuario;


public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

	public UsuarioSistema(Usuario usuario
			, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getIdentidade(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	

}
