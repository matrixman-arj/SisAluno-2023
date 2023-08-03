package br.mil.eb.decex.sisaluno.repository.helper.grupo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import br.mil.eb.decex.sisaluno.model.Grupo;
import br.mil.eb.decex.sisaluno.repository.filter.GrupoFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;

public interface GruposQueries {
	
	public Page<Grupo> filtrar(GrupoFilter filtro, Pageable pageable);
	
	public Optional<Grupo> porNome(String nome);
	
	public List<String> permissoes(Grupo grupo);
	
	public Grupo buscarComPermissoes(Long codigo);		

	public List<Grupo> porGrupo(@AuthenticationPrincipal UsuarioSistema sistema);

	

}
