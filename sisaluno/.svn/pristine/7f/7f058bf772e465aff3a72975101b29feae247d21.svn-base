package br.mil.eb.decex.sisaluno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.OrganizacaoMilitar;
import br.mil.eb.decex.sisaluno.repository.helper.om.OMsQueries;

@Repository
public interface OMs extends JpaRepository<OrganizacaoMilitar, Long>, OMsQueries{	

	public Optional<OrganizacaoMilitar> findBySigla(String sigla);

	
	
}
