package br.mil.eb.decex.sisaluno.repository.helper.om;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.model.OrganizacaoMilitar;
import br.mil.eb.decex.sisaluno.repository.filter.OMFilter;

public interface OMsQueries {
	
			
	public Page<OrganizacaoMilitar> filtrar(OMFilter filtro, Pageable pageable);

//	public List<OrganizacaoMilitar> buscarComPai(@AuthenticationPrincipal UsuarioSistema sistema);
	
	public List<OrganizacaoMilitar> porOrdem(int ordem);
	
//	public List<OrganizacaoMilitar> porPai(Long omPai);
	
//	public List<OrganizacaoMilitar> porPai(@AuthenticationPrincipal UsuarioSistema sistema);
}


