package br.mil.eb.decex.sisaluno.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mil.eb.decex.sisaluno.model.Usuario;
import br.mil.eb.decex.sisaluno.repository.helper.usuario.UsuariosQueries;


public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {
	
	public Optional<Usuario> findByIdentidade(String identidade);

	public List<Usuario> findByCodigoIn(Long[] codigos);
	
	public Optional<Usuario> findByOm(Long om);
	
	

	

	

		
	
}
