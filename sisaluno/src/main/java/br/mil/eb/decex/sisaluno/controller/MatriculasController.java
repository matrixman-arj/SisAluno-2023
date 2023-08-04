package br.mil.eb.decex.sisaluno.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.repository.Cursos;
import br.mil.eb.decex.sisaluno.repository.Matriculas;
import br.mil.eb.decex.sisaluno.service.CadastroMatriculaService;
import br.mil.eb.decex.sisaluno.service.exception.IdentidadeJaCadastradaException;
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
	
	@Autowired
	private CadastroMatriculaService cadastroMatriculaService;
	
	@GetMapping("/nova")
	public ModelAndView nova(Matricula matricula) {
		ModelAndView mv = new ModelAndView("matricula/MatriculaAluno");
		mv.addObject("uuid", UUID.randomUUID().toString());
		mv.addObject("matriculas", matriculas.findAll());
		return mv;
	}
	
	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST )
	public ModelAndView salvar (@Valid Matricula matricula, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			model.addAttribute(matricula);
			return nova(matricula);			
		}
		
		try {			
			cadastroMatriculaService.salvar(matricula);			
		} catch (IdentidadeJaCadastradaException e) {
			result.rejectValue("identidade", e.getMessage(), e.getMessage());
			return nova(matricula);
			
		} 
		
		attributes.addFlashAttribute("mensagem", "Matricula realizada com sucesso! ");
		
		return new ModelAndView ("redirect:/matriculas/nova");
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
