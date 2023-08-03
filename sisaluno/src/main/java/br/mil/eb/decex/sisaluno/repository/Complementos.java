package br.mil.eb.decex.sisaluno.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.Complemento;
import br.mil.eb.decex.sisaluno.repository.helper.complemento.ComplementosQueries;

@Repository
public interface Complementos extends JpaRepository<Complemento, Long>, ComplementosQueries, JpaSpecificationExecutor<Complemento> {
	
	public Optional<Complemento> findByCpf(String cpf);
}
 