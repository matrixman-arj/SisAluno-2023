package br.mil.eb.decex.sisaluno.matricula;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import br.mil.eb.decex.sisaluno.model.Curso;


public class TabelaMatriculasCursoTest {
	
	private TabelaMatriculasCurso tabelaMatriculasCurso;
	
	@Before
	public void setUp() {
		this.tabelaMatriculasCurso = new TabelaMatriculasCurso();
	}
	
	@Test
	public void deveRetornarNotaFinalSemNota() throws Exception {
		assertEquals(BigDecimal.ZERO, tabelaMatriculasCurso.getNotaTfm());
	}
	
//	@Test
//	public void deveCalcularNotaComUmTfm() throws Exception {
//		Curso curso = new Curso();
//		BigDecimal nota = new BigDecimal("8.9");
//		curso.setNotaTfm(nota);
//		
//		tabelaMatriculasCurso.adicionarCurso(curso, nota, BigDecimal.ZERO, BigDecimal.ZERO );
//		
//		assertEquals(nota, tabelaMatriculasCurso.getNotaTfm());
//	}
	
	@Test
	public void deveCalcularNotaComVariosTfm() throws Exception {
		Curso curso = new Curso();
		BigDecimal nota = new BigDecimal("8.9");
//		curso.setTfm(nota);
		
		Curso curso1 = new Curso();
		BigDecimal nota2 = new BigDecimal("4.9");
//		curso1.setTfm2(nota2);
		
		Curso curso2 = new Curso();
		BigDecimal nota3 = new BigDecimal("7.9");
//		curso2.setTfm3(nota3);
		
		tabelaMatriculasCurso.adicionarCurso(curso, nota, BigDecimal.ZERO, BigDecimal.ZERO);
		
		tabelaMatriculasCurso.adicionarCurso(curso1, nota2, BigDecimal.ZERO, BigDecimal.ZERO );
		
		tabelaMatriculasCurso.adicionarCurso(curso2, nota3, BigDecimal.ZERO, BigDecimal.ZERO );
		
		assertEquals(new BigDecimal("21.70"), tabelaMatriculasCurso.getNotaTfm());
	}
	
	

}
