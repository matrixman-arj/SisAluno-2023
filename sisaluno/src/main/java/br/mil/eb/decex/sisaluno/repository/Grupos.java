package br.mil.eb.decex.sisaluno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.Grupo;
import br.mil.eb.decex.sisaluno.repository.helper.grupo.GruposQueries;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long>, GruposQueries{

	Optional<Grupo> findByNome(String nome);

	
	
}
