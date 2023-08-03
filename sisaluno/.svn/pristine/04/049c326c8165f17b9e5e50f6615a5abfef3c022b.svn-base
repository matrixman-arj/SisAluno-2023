package br.mil.eb.decex.sisaluno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.repository.helper.curso.CursosQueries;

@Repository
public interface Cursos extends JpaRepository<Curso, Long>, CursosQueries, JpaSpecificationExecutor<Curso> {
	
	public Optional<Curso> findBySku(String sku);	

//	public List<Curso> findByCpfStartingWithIgnoreCase(String cpf);
}
 