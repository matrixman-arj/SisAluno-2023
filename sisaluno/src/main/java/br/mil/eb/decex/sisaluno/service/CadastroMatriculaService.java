package br.mil.eb.decex.sisaluno.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.repository.Matriculas;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;


@Service
public class CadastroMatriculaService {
		
	@Autowired
	private Matriculas matriculas;
	
	
	UsuarioSistema usuarioSistema;
	
	@Transactional
	public void salvar(Matricula matricula) {
		
		if(matricula.isNova()) {
			
			
			matricula.setDataCriacao(LocalDate.now());
						
		}
		
		BigDecimal notaTFM = (Optional.ofNullable(matricula.getTfm()).orElse(BigDecimal.ZERO))
				.add(Optional.ofNullable(matricula.getTfm2()).orElse(BigDecimal.ZERO))
				.add(Optional.ofNullable(matricula.getTfm3()).orElse(BigDecimal.ZERO));
		
			
		BigDecimal divisaoTFM = new BigDecimal("3")
				.add(Optional.ofNullable(matricula.getTfm()).get())
				.add(Optional.ofNullable(matricula.getTfm2()).get())
				.add(Optional.ofNullable(matricula.getTfm3()).get());
		
//		BigDecimal divisaoTFM = new BigDecimal("1")
//				.add(matricula.getTfm())
//				.add(Optional.ofNullable(matricula.getTfm2()).orElse(BigDecimal.ZERO))
//				.add(Optional.ofNullable(matricula.getTfm3()).orElse(BigDecimal.ZERO));
				
		BigDecimal notaFinalTFM = notaTFM.divide(divisaoTFM, 2, RoundingMode.HALF_UP);
		
		matricula.setTotalTFM(notaFinalTFM);
		
		BigDecimal notaAtitudinal = (Optional.ofNullable(matricula.getAtitudinal()).orElse(BigDecimal.ZERO))
				 .add(Optional.ofNullable(matricula.getAtitudinalLateral()).orElse(BigDecimal.ZERO))
				 .add(Optional.ofNullable(matricula.getAtitudinalVertical()).orElse(BigDecimal.ZERO));
		
		BigDecimal divisaoAtitudinal = new BigDecimal("3");
		BigDecimal notaFinalAtitudinal = notaAtitudinal.divide(divisaoAtitudinal, 2, RoundingMode.HALF_UP);
		matricula.setTotalAtitudinal(notaFinalAtitudinal);
		
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
