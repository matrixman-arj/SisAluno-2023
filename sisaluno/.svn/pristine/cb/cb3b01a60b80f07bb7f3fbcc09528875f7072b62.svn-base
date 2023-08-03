package br.mil.eb.decex.sisaluno.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Aluno;
import br.mil.eb.decex.sisaluno.repository.Alunos;
import br.mil.eb.decex.sisaluno.service.event.aluno.AlunoSalvoEvent;
import br.mil.eb.decex.sisaluno.service.exception.CpfAlunoJaCadastradoException;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroAlunoService {
	
	@Autowired
	private Alunos alunos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
//	@Autowired
//	private FotoStorage fotoStorage;
//	
	@Transactional
	public void salvar(Aluno aluno) {
		Optional<Aluno> alunoOptional = alunos.findByCpf(aluno.getCpf().replaceAll("\\.|-", ""));
		if (alunoOptional.isPresent() && !alunoOptional.get().equals(aluno)) {
			throw new CpfAlunoJaCadastradoException("Já existe um aluno cadastrado com o cpf informado");
		}
		
		if (aluno.getAtivo() == null) {
			aluno.setAtivo(true);
		}		
		alunos.saveAndFlush(aluno);
		
		publisher.publishEvent(new AlunoSalvoEvent(aluno));
	}
	
	@Transactional
	public void excluir(Long codigo ) { //Código para excluir quando não há foto.
		try {
			alunos.delete(codigo);
			alunos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar unidade. Já foi usada em alguma entidade.");
		}
	}

}
