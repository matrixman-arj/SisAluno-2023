package br.mil.eb.decex.sisaluno.controller;

import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.mil.eb.decex.sisaluno.controller.page.PageWrapper;
import br.mil.eb.decex.sisaluno.controller.validator.MatriculaValidator;
import br.mil.eb.decex.sisaluno.enumerated.Ano;
import br.mil.eb.decex.sisaluno.enumerated.Periodo;
import br.mil.eb.decex.sisaluno.enumerated.SituacaoNoCurso;
import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.model.ItemMatricula;
import br.mil.eb.decex.sisaluno.model.Matricula;
import br.mil.eb.decex.sisaluno.repository.Alunos;
import br.mil.eb.decex.sisaluno.repository.Cursos;
import br.mil.eb.decex.sisaluno.repository.Matriculas;
import br.mil.eb.decex.sisaluno.repository.filter.MatriculaFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;
import br.mil.eb.decex.sisaluno.service.CadastroMatriculaService;
import br.mil.eb.decex.sisaluno.service.exception.CpfParaAnoLetivoJaCadastradoException;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.session.TabelasItensSession;


@Controller
@RequestMapping("/matriculas")
public class MatriculasController {
	
	@Autowired
	private Cursos cursos;
	
	@Autowired
	private TabelasItensSession tabelaItens;
	
	@Autowired
	private CadastroMatriculaService cadastroMatriculaService;
	
	@Autowired
	private MatriculaValidator matriculaValidator;
	
	@Autowired
	private Matriculas matriculas;
	
	
	@Autowired
	private Alunos alunos;
		
	@InitBinder("matricula")
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(matriculaValidator);
	}
	
	@GetMapping("/nova")
	public ModelAndView nova(Matricula matricula) {
		ModelAndView mv = new ModelAndView("matricula/MatriculaAluno");
		
//		if(StringUtils.isEmpty(matricula.getUuid())) {
//			matricula.setUuid(UUID.randomUUID().toString());
//		}
		setUuid(matricula);
		
//		mv.addObject("matriculas", matriculas.findAll());
		mv.addObject("itens", matricula.getItens());/*Esse objeto serve para preecher os itens da TabelaItensMatricula.html*/
		mv.addObject("situacoes", SituacaoNoCurso.values());
		mv.addObject("anosLetivo", Ano.values());
		mv.addObject("periodos", Periodo.values());
		
		return mv;
	}
	
	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST )
	public ModelAndView salvar (Matricula matricula, BindingResult result, Model model, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		matricula.adicionarItens(tabelaItens.getItens(matricula.getUuid()));
//		matricula.setItens(tabelaItens.getItens(matricula.getUuid()));
		
		
		matriculaValidator.validate(matricula, result);
		if (result.hasErrors()) {
			model.addAttribute(matricula);
			return nova(matricula);			
		}
		
		for(ItemMatricula item : matricula.getItens()) {
//			tabelaItens.adicionarItem(matricula.getUuid(), item.getCurso(), item.getQuantidade());
		
		
		matricula.setCurso(item.getCurso());
		
		
		}
		
		
		
//		if(!matricula.isNova()) {
//			Aluno aluno = new Aluno();
//			aluno = matriculas.findByAluno(matricula.getAluno());
//			matricula.setAluno(aluno);
//		}

		
		matricula.setOm(usuarioSistema.getUsuario().getOm());
		matricula.setUsuario(usuarioSistema.getUsuario());
		
		try {			
			cadastroMatriculaService.salvar(matricula);			
		} 	
		 catch (CpfParaAnoLetivoJaCadastradoException e) {
			result.rejectValue("cpfDoAluno", e.getMessage(), e.getMessage());
			return nova(matricula);
			
		} 
		
//		catch (DataMatriculaInferiorException e) {
//			result.rejectValue("cpfAluno", e.getMessage(), e.getMessage());
//			return nova(matricula);
//			
//		} 
		
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
	public ModelAndView excluirItem(@PathVariable("codigoCurso")Curso curso 
			, @PathVariable String uuid) {		
		tabelaItens.excluirItem(uuid, curso);
		return mvTabelaItensMatricula(uuid);
	}

	
	
	@GetMapping
	public ModelAndView pesquisar(Matricula matricula, MatriculaFilter matriculaFilter, CriteriaBuilder criteriaBuilder, CriteriaQuery<Matricula> query, List<Predicate> predicates,
			BindingResult result, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema sistema, Criteria criteria) {
		ModelAndView mv = new ModelAndView("matricula/PesquisaMatriculas");
		mv.addObject("itens", matricula.getItens());
		mv.addObject("alunos", alunos.findAll());
		mv.addObject("anosLetivo", Ano.values());
		
				
		PageWrapper<Matricula> paginaWrapper = new PageWrapper<>(matriculas.filtrar(matriculaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		PageWrapper<Matricula> paginaWrapper2 = new PageWrapper<>(matriculas.buscarMatriculasPorOM(matriculaFilter, pageable, sistema)
				, httpServletRequest);
		mv.addObject("paginaDaMesmaOm", paginaWrapper2);	
		
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {		
		Matricula matricula = matriculas.findOne(codigo);
		
		setUuid(matricula);
		for(ItemMatricula item : matricula.getItens()) {
			tabelaItens.adicionarItem(matricula.getUuid(), item.getCurso(), item.getQuantidade());
		}
		
		ModelAndView mv = nova(matricula);
		mv.addObject(matricula);
		
		return mv;
	}
	
	private ModelAndView mvTabelaItensMatricula(String uuid) {
		ModelAndView mv = new ModelAndView("matricula/TabelaItensMatricula");
		mv.addObject("itens", tabelaItens.getItens(uuid));
		return mv;
	}
	
	
	private void setUuid(Matricula matricula) {

		if(StringUtils.isEmpty(matricula.getUuid())) {
			matricula.setUuid(UUID.randomUUID().toString());
		}
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Matricula matricula){
		try {
			cadastroMatriculaService.excluir(matricula);
				
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();	
	}

}
