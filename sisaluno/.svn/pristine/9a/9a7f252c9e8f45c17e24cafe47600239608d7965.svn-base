package br.mil.eb.decex.sisaluno.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.Estado;
import br.mil.eb.decex.sisaluno.model.Regiao;
import br.mil.eb.decex.sisaluno.repository.helper.regiao.RegioesQueries;

@Repository
public interface Regioes extends JpaRepository<Regiao, Long>, RegioesQueries {
	
	
	
	public Optional<Regiao> findByNomeAndEstado(String nome, Regiao regiao);

	public List<Regiao> findByEstadoCodigo(Long codigoEstado);

	public Optional<Regiao> findByNomeAndEstado(String nome, Estado estado);

	

		

}
