package br.mil.eb.decex.sisaluno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.SituacaoNoCurso;
import br.mil.eb.decex.sisaluno.repository.helper.situacao.SituacoesQueries;

@Repository
public interface Situacoes extends JpaRepository<SituacaoNoCurso, Long>, SituacoesQueries{	

	public Optional<SituacaoNoCurso> findByNome(String nome);

	
	
}
