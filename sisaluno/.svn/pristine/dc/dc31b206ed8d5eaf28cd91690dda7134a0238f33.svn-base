package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Estado;
import br.mil.eb.decex.sisaluno.repository.Estados;
import br.mil.eb.decex.sisaluno.service.exception.NomeEstadoJaCadastradoException;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private Estados estados;
	
	@Transactional
	public void salvar(Estado estado) {
		Optional<Estado> estadoExistente = estados.findByNomeAndRegiao(estado.getNome(), estado.getRegiao());
		if (estadoExistente.isPresent()&& !estadoExistente.get().equals(estado)) {
			throw new NomeEstadoJaCadastradoException("Nome de estado já cadastrado");
		}
		
		estados.save(estado);
	}

}
