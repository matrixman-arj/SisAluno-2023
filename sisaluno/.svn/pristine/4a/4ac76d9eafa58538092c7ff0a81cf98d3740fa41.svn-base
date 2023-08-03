package br.mil.eb.decex.sisaluno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.Uete;
import br.mil.eb.decex.sisaluno.repository.helper.uete.UetesQueries;

@Repository
public interface Uetes extends JpaRepository<Uete, Long>, UetesQueries{	

	public Optional<Uete> findBySigla(String sigla);
	
}
