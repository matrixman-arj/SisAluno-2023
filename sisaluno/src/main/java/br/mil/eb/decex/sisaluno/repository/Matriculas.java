package br.mil.eb.decex.sisaluno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.repository.helper.matricula.MatriculasQueries;

public interface Matriculas extends JpaRepository<Matricula, Long>, MatriculasQueries{
		
	
}
