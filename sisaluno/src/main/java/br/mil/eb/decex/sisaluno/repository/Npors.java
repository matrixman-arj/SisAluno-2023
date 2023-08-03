package br.mil.eb.decex.sisaluno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.Npor;
import br.mil.eb.decex.sisaluno.repository.helper.npor.NporsQueries;

@Repository
public interface Npors extends JpaRepository<Npor, Long>, NporsQueries{	

	public Optional<Npor> findBySigla(String sigla);
	
}
