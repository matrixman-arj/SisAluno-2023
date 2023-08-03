package br.mil.eb.decex.sisaluno.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.Aluno;
import br.mil.eb.decex.sisaluno.repository.helper.aluno.AlunosQueries;

@Repository
public interface Alunos extends JpaRepository<Aluno, Long>, AlunosQueries, JpaSpecificationExecutor<Aluno> {
	
	public Optional<Aluno> findByCpf(String cpf);	

	public List<Aluno> findByCpfStartingWithIgnoreCase(String cpf);
}
 