package br.mil.eb.decex.sisaluno.repository.helper.npor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.mil.eb.decex.sisaluno.model.Npor;
import br.mil.eb.decex.sisaluno.repository.filter.NporFilter;

public interface NporsQueries {
	
			
	public Page<Npor> filtrar(NporFilter filtro, Pageable pageable);

//	public List<Uete> buscarComPai(@AuthenticationPrincipal UsuarioSistema sistema);
	
	public List<Npor> porOrdem(int ordem);
	
//	public List<OrganizacaoMilitar> porPai(Long omPai);
	
//	public List<Uete> porPai(@AuthenticationPrincipal UsuarioSistema sistema);
}


