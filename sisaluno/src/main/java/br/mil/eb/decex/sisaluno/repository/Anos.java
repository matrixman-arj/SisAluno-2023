package br.mil.eb.decex.sisaluno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.Ano;
import br.mil.eb.decex.sisaluno.repository.helper.ano.AnosQueries;

@Repository
public interface Anos extends JpaRepository<Ano, Long>, AnosQueries{	

	public Optional<Ano> findByAno(String ano);

	
	
}
