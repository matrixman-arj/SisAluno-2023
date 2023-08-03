package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Complemento;
import br.mil.eb.decex.sisaluno.repository.Complementos;
import br.mil.eb.decex.sisaluno.service.exception.CpfComplementoJaCadastradoException;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroComplementoService {
	
	@Autowired
	private Complementos complementos;
	
	@Transactional
	public void salvar(Complemento complemento) {
		Optional<Complemento> complementoOptional = complementos.findByCpf(complemento.getCpf().replaceAll("\\.|-", ""));
		if (complementoOptional.isPresent() && !complementoOptional.get().equals(complemento)) {
			throw new CpfComplementoJaCadastradoException("Já existe um aluno cadastrado com o cpf informado");
		}
		
		if (complemento.getAtivo() == null) {
			complemento.setAtivo(true);
		}		
		complementos.saveAndFlush(complemento);
	}
	
	@Transactional
	public void excluir(Long codigo ) { //Código para excluir quando não há foto.
		try {
			complementos.delete(codigo);
			complementos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar unidade. Já foi usada em alguma entidade.");
		}
	}

}
