package br.mil.eb.decex.sisaluno.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.mil.eb.decex.sisaluno.model.NotificacaoDiaria;
import br.mil.eb.decex.sisaluno.model.Usuario;

@Repository
public interface Notificacoes extends JpaRepository<NotificacaoDiaria, Long>{	

	
	public Optional<NotificacaoDiaria> findByUsuario(Usuario usuario);

	public List<NotificacaoDiaria> findByCodigoIn(Long[] codigos);	
}
