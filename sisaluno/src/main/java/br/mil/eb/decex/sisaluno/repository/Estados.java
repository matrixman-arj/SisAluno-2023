package br.mil.eb.decex.sisaluno.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.Estado;
import br.mil.eb.decex.sisaluno.model.Regiao;
import br.mil.eb.decex.sisaluno.repository.helper.estado.EstadosQueries;

@Repository
public interface Estados extends JpaRepository<Estado, Long>, EstadosQueries {
	

	public Optional<Estado> findByNomeAndRegiao(String nome, Regiao regiao);

	public List<Estado> findByRegiaoCodigo(Long codigoRegiao);

	public Optional<Estado> findByNome(String nome);

}
