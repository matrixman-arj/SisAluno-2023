package br.mil.eb.decex.sisaluno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.mil.eb.decex.sisaluno.enumerated.Ano;
import br.mil.eb.decex.sisaluno.model.Aluno;
import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.repository.helper.matricula.MatriculasQueries;

public interface Matriculas extends JpaRepository<Matricula, Long>, MatriculasQueries {
	
	public Optional<Matricula> findByAlunoAndAnoLetivo(Aluno aluno, Ano anoLetivo);

	public Aluno findByAluno(Aluno aluno);

}
