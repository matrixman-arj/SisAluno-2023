package br.mil.eb.decex.sisaluno.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.repository.Cursos;
import br.mil.eb.decex.sisaluno.repository.Matriculas;
import br.mil.eb.decex.sisaluno.session.TabelasItensSession;


@Controller
@RequestMapping("/matriculas")
public class MatriculasController {
	
	@Autowired
	private Cursos cursos;
	
	@Autowired
	private TabelasItensSession tabelaItens;
	
	@Autowired
	private Matriculas matriculas;
	
	@GetMapping("/nova")
	public ModelAndView nova() {
		ModelAndView mv = new ModelAndView("matricula/MatriculaAluno");
		mv.addObject("uuid", UUID.randomUUID().toString());
		mv.addObject("matriculas", matriculas.findAll());
		return mv;
	}
	
	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoCurso, String uuid) {
		Curso curso = cursos.findOne(codigoCurso);
		tabelaItens.adicionarItem(uuid, curso, 1);
		return mvTabelaItensMatricula(uuid);
	}
	
	
	@DeleteMapping("/item/{uuid}/{codigoCurso}")
	public ModelAndView excluirItem(@PathVariable("codigoCurso")Curso curso, @PathVariable String uuid) {		
		tabelaItens.excluirItem(uuid, curso);
		return mvTabelaItensMatricula(uuid);
	}

	private ModelAndView mvTabelaItensMatricula(String uuid) {
		ModelAndView mv = new ModelAndView("matricula/TabelaItensMatricula");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		return mv;
	}
	
	

}
