package br.mil.eb.decex.sisaluno.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
import br.mil.eb.decex.sisaluno.model.Grupo;
import br.mil.eb.decex.sisaluno.repository.Grupos;
import br.mil.eb.decex.sisaluno.repository.Permissoes;
import br.mil.eb.decex.sisaluno.repository.filter.GrupoFilter;
import br.mil.eb.decex.sisaluno.service.CadastroGrupoService;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.NomeJaCadastradoException;

@Controller
@RequestMapping("/grupos")
public class GruposController {
	
	@Autowired
	private Permissoes permissoes;
	
	@Autowired
	private Grupos grupos;
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Grupo grupo) {
		ModelAndView mv = new ModelAndView("grupo/CadastroGrupo");
		mv.addObject("permissoes", permissoes.findAll());
		mv.addObject("grupos", grupos.findAll());
		
		return mv;
	}
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
//	@RequestMapping(value= "/novo", method = RequestMethod.POST)
	public ModelAndView salvar (@Valid Grupo grupo, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			model.addAttribute(grupo);
			return novo(grupo);			
		}
		
		try {
			cadastroGrupoService.salvar(grupo);
		} catch (NomeJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(grupo);
		}		
					
		attributes.addFlashAttribute("mensagem", "Grupo salvo com sucesso! ");
		return new ModelAndView("redirect:/grupos/novo");		
	}
	
	@GetMapping
	public ModelAndView pesquisar(GrupoFilter grupoFilter, BindingResult result
			, @PageableDefault(size = 3) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("grupo/PesquisaGrupos");		
		mv.addObject("permissoes", permissoes.findAll() );		
		
		PageWrapper<Grupo> paginaWrapper = new PageWrapper<>(grupos.filtrar(grupoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;				
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable  Long codigo) {
		Grupo grupo = grupos.buscarComPermissoes(codigo);
		ModelAndView mv = novo(grupo);
		mv.addObject(grupo);
		return mv;
	}
	
		
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		try {
			cadastroGrupoService.excluir(codigo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}	
}
