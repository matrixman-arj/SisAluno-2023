package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Cidade;
import br.mil.eb.decex.sisaluno.repository.Cidades;
import br.mil.eb.decex.sisaluno.service.exception.NomeCidadeJaCadastradoException;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private Cidades cidades;
	
	@Transactional
	public void salvar(Cidade cidade) {
		Optional<Cidade> cidadeExistente = cidades.findByNomeAndRegiao(cidade.getNome(), cidade.getRegiao());
		if (cidadeExistente.isPresent()&& !cidadeExistente.get().equals(cidade)) {
			throw new NomeCidadeJaCadastradoException("Nome de cidade já cadastrado");
		}
		
		cidades.save(cidade);
	}

}
