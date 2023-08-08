package br.mil.eb.decex.sisaluno.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.ItemMatricula;
import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.repository.Matriculas;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;


@Service
public class CadastroMatriculaService {
		
	@Autowired
	private Matriculas matriculas;
	
	
	@Transactional
	public void salvar(Matricula matricula) {
		
		if(matricula.isNova()) {
			matricula.setDataCriacao(LocalDate.now());
		}
		
		
			BigDecimal notaTFM = Optional.ofNullable(matricula.getTfm()).orElse(BigDecimal.ZERO)
							.add(Optional.ofNullable(matricula.getTfm2()).orElse(BigDecimal.ZERO)
							.add(Optional.ofNullable(matricula.getTfm3()).orElse(BigDecimal.ZERO)));
			BigDecimal d = new BigDecimal("3");
			notaTFM = notaTFM.divide(d);
			matricula.setTotalTFM(notaTFM);
			
			BigDecimal notaAtitudi = Optional.ofNullable(matricula.getAtitudinal()).orElse(BigDecimal.ZERO)
							.add(Optional.ofNullable(matricula.getAtitudinalLateral()).orElse(BigDecimal.ZERO)
							.add(Optional.ofNullable(matricula.getAtitudinalVertical()).orElse(BigDecimal.ZERO)));
			BigDecimal da = new BigDecimal("3");
			notaAtitudi = notaAtitudi.divide(da);
			matricula.setTotalAtitudinal(notaAtitudi);
		
		
		matriculas.save(matricula);				
		
	}
		
	
	@Transactional
	public void excluir(Matricula matricula ) {
		try {	
			
			matriculas.delete(matricula);
			matriculas.flush();
			
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar matricula. Já foi usada em alguma entidade.");
		}
	}

}
