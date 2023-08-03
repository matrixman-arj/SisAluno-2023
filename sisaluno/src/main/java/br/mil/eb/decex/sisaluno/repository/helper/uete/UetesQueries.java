package br.mil.eb.decex.sisaluno.repository.helper.uete;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.model.Uete;
import br.mil.eb.decex.sisaluno.repository.filter.UeteFilter;

public interface UetesQueries {
	
			
	public Page<Uete> filtrar(UeteFilter filtro, Pageable pageable);

//	public List<Uete> buscarComPai(@AuthenticationPrincipal UsuarioSistema sistema);
	
	public List<Uete> porOrdem(int ordem);
	
//	public List<OrganizacaoMilitar> porPai(Long omPai);
	
//	public List<Uete> porPai(@AuthenticationPrincipal UsuarioSistema sistema);
}


