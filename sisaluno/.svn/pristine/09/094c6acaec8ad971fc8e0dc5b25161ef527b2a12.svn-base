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
import br.mil.eb.decex.sisaluno.model.Npor;
import br.mil.eb.decex.sisaluno.repository.Npors;
import br.mil.eb.decex.sisaluno.repository.Regioes;
import br.mil.eb.decex.sisaluno.repository.RegioesMilitares;
import br.mil.eb.decex.sisaluno.repository.filter.NporFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;
import br.mil.eb.decex.sisaluno.service.CadastroNporService;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.NPORJaCadastradoException;

@Controller
@RequestMapping("/npors")
public class NporsController {
	
	@Autowired
	private Regioes regioes;
	
	@Autowired
	private Npors npors;
	
	@Autowired
	private RegioesMilitares regioesMilitares;
	
	@Autowired
	private CadastroNporService cadastroNporService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Npor npor, @AuthenticationPrincipal UsuarioSistema sistema) {
		ModelAndView mv = new ModelAndView("npor/CadastroNpor");		
		mv.addObject("oficiaisReserva", npors.findAll());
		mv.addObject("regioes", regioes.findAll());

		
		return mv;
	}
	
//	@RequestMapping(value= "/nova", method = RequestMethod.POST)
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Npor npor, BindingResult result, Model model, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema sistema) {
		if (result.hasErrors()) {
			model.addAttribute(npor);
			return novo(npor, sistema);
			
		}
		
		try {
			cadastroNporService.salvar(npor);
		} catch (NPORJaCadastradoException e) {
			result.rejectValue("sigla", e.getMessage(), e.getMessage());
			return novo(npor, sistema);
			
		}		
		attributes.addFlashAttribute("mensagem", "NPOR salvo com sucesso! ");
		return new ModelAndView("redirect:/npors/novo");
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(NporFilter nporFilter, BindingResult result
			, @PageableDefault(size = 7) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("npor/PesquisaNpors");			
		mv.addObject("OficiaisRserva", npors.findAll());
		mv.addObject("rms", regioesMilitares.findAll());
		PageWrapper<Npor> paginaWrapper = new PageWrapper<>(npors.filtrar(nporFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
				
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		try {
			cadastroNporService.excluir(codigo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo, @AuthenticationPrincipal UsuarioSistema sistema) {
		Npor npor = npors.findOne(codigo);
		ModelAndView mv = novo(npor, sistema);		
		mv.addObject(npor);
		return mv;
	}
	
}
