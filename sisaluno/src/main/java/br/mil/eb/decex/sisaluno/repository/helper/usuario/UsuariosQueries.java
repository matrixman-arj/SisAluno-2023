package br.mil.eb.decex.sisaluno.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.model.Usuario;
import br.mil.eb.decex.sisaluno.repository.filter.UsuarioFilter;

public interface UsuariosQueries {
	
	public Optional<Usuario> porIdentidadeEAtivo(String identidade);
	
	public List<String> permissoes(Usuario usuario);
	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);
	
	public Usuario buscarComGrupos(Long codigo);
	
	
	

	

}
