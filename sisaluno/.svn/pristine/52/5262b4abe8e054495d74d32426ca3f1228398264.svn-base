package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Regiao;
import br.mil.eb.decex.sisaluno.repository.Regioes;
import br.mil.eb.decex.sisaluno.service.exception.NomeRegiaoJaCadastradaException;

@Service
public class CadastroRegiaoService {
	
	@Autowired
	private Regioes regioes;
	
	@Transactional
	public void salvar(Regiao regiao) {
//		Optional<Regiao> regiaoExistente = regioes.findByNome(regiao.getNome());
		Optional<Regiao> regiaoExistente = regioes.findByNomeAndEstado(regiao.getNome(), regiao.getEstado());
		if (regiaoExistente.isPresent()&& !regiaoExistente.get().equals(regiao)) {
			throw new NomeRegiaoJaCadastradaException("Nome de regiao já cadastrado");
		}
		
		regioes.save(regiao);
	}

}
