package br.mil.eb.decex.sisaluno.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import br.mil.eb.decex.sisaluno.model.Uete;
import br.mil.eb.decex.sisaluno.repository.Regioes;
import br.mil.eb.decex.sisaluno.repository.Uetes;
import br.mil.eb.decex.sisaluno.repository.filter.UeteFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;
import br.mil.eb.decex.sisaluno.service.CadastroUeteService;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.UETEJaCadastradoException;

@Controller
@RequestMapping("/uetes")
public class UetesController {
	
	@Autowired
	private Regioes regioes;
	
	@Autowired
	private Uetes uetes;	
	
	@Autowired
	private CadastroUeteService cadastroUeteService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Uete uete, @AuthenticationPrincipal UsuarioSistema sistema) {
		ModelAndView mv = new ModelAndView("uete/CadastroUete");		
		mv.addObject("oficiaisReserva", uetes.findAll());
		mv.addObject("regioes", regioes.findAll());
		mv.addObject(uete);
		
		return mv;
	}
	
//	@RequestMapping(value= "/nova", method = RequestMethod.POST)
	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Uete uete, BindingResult result, Model model, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema sistema) {
		ModelAndView mv = new ModelAndView("uete/CadastroUete");
		mv.addObject(uete);		
		if (result.hasErrors()) {			
			return nova(uete, sistema);
			
		}if (!uete.isNova()) {
//			uete.setRegiao(uete.getEstado().getRegiao());
//			uete.setEstado(uete.getEstado());
			
			System.out.println(">>> região: " );
			System.out.println(">>> estado: " );
		}
		
		try {
			cadastroUeteService.salvar(uete);
		} catch (UETEJaCadastradoException e) {
			result.rejectValue("sigla", e.getMessage(), e.getMessage());
			return nova(uete, sistema);
			
		}		
		attributes.addFlashAttribute("mensagem", "UETE salvo com sucesso! ");
		return new ModelAndView("redirect:/uetes/nova");
		
	}
	
	@GetMapping
	public ModelAndView pesquisar( UeteFilter ueteFilter, BindingResult result
			, @PageableDefault(size = 7) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("uete/PesquisaUetes");			
		mv.addObject("basicos", uetes.findAll());
		mv.addObject(ueteFilter);
		PageWrapper<Uete> paginaWrapper = new PageWrapper<>(uetes.filtrar(ueteFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
			
		return mv;
				
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		try {
			cadastroUeteService.excluir(codigo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo, @AuthenticationPrincipal UsuarioSistema sistema) {
		Uete uete = uetes.findOne(codigo);
		ModelAndView mv = nova(uete, sistema);		
		mv.addObject(uete);		
		
		return mv;
	}
	
}
