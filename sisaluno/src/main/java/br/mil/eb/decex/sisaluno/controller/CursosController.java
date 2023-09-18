package br.mil.eb.decex.sisaluno.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.mil.eb.decex.sisaluno.controller.page.PageWrapper;
import br.mil.eb.decex.sisaluno.dto.CursoDTO;
import br.mil.eb.decex.sisaluno.enumerated.CPOREstado;
import br.mil.eb.decex.sisaluno.enumerated.EspecialidadeArma;
import br.mil.eb.decex.sisaluno.enumerated.Linha;
import br.mil.eb.decex.sisaluno.enumerated.Modalidade;
import br.mil.eb.decex.sisaluno.enumerated.TipoVinculo;
import br.mil.eb.decex.sisaluno.enumerated.Universo;
import br.mil.eb.decex.sisaluno.enumerated.Vinculo;
import br.mil.eb.decex.sisaluno.model.Curso;
import br.mil.eb.decex.sisaluno.repository.Cursos;
import br.mil.eb.decex.sisaluno.repository.Uetes;
import br.mil.eb.decex.sisaluno.repository.filter.CursoFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;
import br.mil.eb.decex.sisaluno.service.CadastroCursoService;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.SkuCursoJaCadastradoException;

@Controller
@RequestMapping("/cursos")
public class CursosController {
	
	@Autowired
	private CadastroCursoService cadastroCursoService;
	
	@Autowired
	private Cursos cursos;
	
	@Autowired
	private Uetes uetes;
	
	

		
	@RequestMapping("/novo")
	public ModelAndView novo(Curso curso) {
		ModelAndView mv = new ModelAndView("curso/CadastroCurso");				
		mv.addObject("modalidades", Modalidade.values());
		mv.addObject("universos", Universo.values());
		mv.addObject("linhas",Linha.values());
		mv.addObject("armas",EspecialidadeArma.values());
		mv.addObject("vinculos",Vinculo.values());
		mv.addObject("tiposVinculo",TipoVinculo.values());
		mv.addObject("uetes", uetes.findAll());
		mv.addObject("cporEstados",CPOREstado.values());
		
		return mv;
	}	
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Curso curso, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			model.addAttribute(curso);
			return novo(curso);
		}
				
		try {
			cadastroCursoService.salvar(curso);
		}catch(SkuCursoJaCadastradoException e) {
			result.rejectValue("sku", e.getMessage(), e.getMessage());
			return novo(curso);
		}
		
		attributes.addFlashAttribute("mensagem", "Curso salvo com sucesso!");
		return new ModelAndView ("redirect:/cursos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CursoFilter cursoFilter, BindingResult result, @PageableDefault(size = 12) Pageable pageable, HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema sistema ) { 
		ModelAndView mv = new ModelAndView("curso/PesquisaCursos");
//		mv.addObject("cursos",cursos.findAll());
		PageWrapper<Curso> paginaWrapper  = new PageWrapper<>(cursos.filtrar(cursoFilter, pageable), httpServletRequest);		
		mv.addObject("pagina", paginaWrapper );		
//		
//		PageWrapper<Curso> paginaWrapper2 = new PageWrapper<>(cursos.filtrarPelaOmUsuLogado(cursoFilter, pageable, sistema), httpServletRequest);
//		mv.addObject("paginaOmUsuLogado", paginaWrapper2);
		
		return mv;
	}
	
//	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
//	public @ResponseBody List<Curso> pesquisar(String cpf) {
//		validarTamanhoCpf(cpf);
//		return cursos.findByCpfStartingWithIgnoreCase(cpf);
//	}
//
//	private void validarTamanhoCpf(String cpf) {
//		if (StringUtils.isEmpty(cpf) || cpf.length() < 3) {
//			throw new IllegalArgumentException();
//		}
//	}
//	
//	@ExceptionHandler(IllegalArgumentException.class)
//	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
//		return ResponseEntity.badRequest().build();
//	}

//	@GetMapping
//	public ModelAndView pesquisar(CursoFilter cursoFilter, Criteria criteria, String cpf, BindingResult result,@PageableDefault(size = 12) Pageable pageable, HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema sistema ) { 
//		ModelAndView mv = new ModelAndView("curso/PesquisaCursos");
//		
//		PageWrapper<Curso> paginaWrapper  = new PageWrapper<>(cursos.filtrar(cursoFilter, pageable, criteria, cpf), httpServletRequest);		
//		mv.addObject("pagina", paginaWrapper );		
//		
//		PageWrapper<Curso> paginaWrapper2 = new PageWrapper<>(cursos.filtrarPelaOmUsuLogado(cursoFilter, pageable, sistema), httpServletRequest);
//		mv.addObject("paginaOmUsuLogado", paginaWrapper2);
//		
//		return mv;
//	}
			
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		try {
			cadastroCursoService.excluir(codigo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo, @AuthenticationPrincipal UsuarioSistema sistema) {
		Curso curso = cursos.findOne(codigo);
		
		ModelAndView mv = novo(curso);		
		mv.addObject(curso);
		
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDTO> pesquisar(String skuOuDescr){
		return cursos.porSkuOuDescricao(skuOuDescr);
	}
}
