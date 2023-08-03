package br.mil.eb.decex.sisaluno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.Permissao;

@Repository
public interface Permissoes extends JpaRepository<Permissao, Long>{
	
	public Optional<Permissao> findByNome(String nome);

}
