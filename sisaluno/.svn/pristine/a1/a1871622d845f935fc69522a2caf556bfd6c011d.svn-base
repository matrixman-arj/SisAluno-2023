package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Permissao;
import br.mil.eb.decex.sisaluno.repository.Permissoes;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.PermissaoJaCadastradaException;

@Service
public class CadastroPermService {
	
	@Autowired
	private Permissoes permissoes;
	
	@Transactional
	public void salvar(Permissao permissao) {
		Optional<Permissao> permissaoOptional = permissoes.findByNome(permissao.getNome());
		if(permissaoOptional.isPresent()) {
			throw new PermissaoJaCadastradaException("Já existe uma permissão cadastrada com esse nome!");
		}
		
		permissoes.saveAndFlush(permissao);
	}
	
	@Transactional
	public void excluir(Long codigo ) { //Código para excluir quando não há foto.
		try {
			permissoes.delete(codigo);
			permissoes.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar unidade. Já foi usada em alguma entidade.");
		}
	}

}
