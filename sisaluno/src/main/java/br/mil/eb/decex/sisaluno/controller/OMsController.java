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
import br.mil.eb.decex.sisaluno.model.OrganizacaoMilitar;
import br.mil.eb.decex.sisaluno.model.Regiao;
import br.mil.eb.decex.sisaluno.repository.Estados;
import br.mil.eb.decex.sisaluno.repository.OMs;
import br.mil.eb.decex.sisaluno.repository.Regioes;
import br.mil.eb.decex.sisaluno.repository.RegioesMilitares;
import br.mil.eb.decex.sisaluno.repository.filter.OMFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;
import br.mil.eb.decex.sisaluno.service.CadastroOMService;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.OMJaCadastradaException;

@Controller
@RequestMapping("/oms")
public class OMsController {
	
	@Autowired
	private Regioes regioes;
	
	@Autowired
	private RegioesMilitares rms;
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private OMs oms;	
	
	@Autowired
	private CadastroOMService cadastroOMService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(OrganizacaoMilitar organizacaoMilitar, @AuthenticationPrincipal UsuarioSistema sistema) {
		ModelAndView mv = new ModelAndView("organizacaoMilitar/CadastroOM");		
		mv.addObject("organizacoesMilitares", oms.findAll());
		mv.addObject("regioes", regioes.findAll());
		mv.addObject("regioesMilitares", rms.findAll());
		mv.addObject("estados", estados.findAll());
		
		return mv;
	}
	
//	@RequestMapping(value= "/nova", method = RequestMethod.POST)
	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid OrganizacaoMilitar organizacaoMilitar, BindingResult result, Model model, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema sistema) {
		if (result.hasErrors()) {
			model.addAttribute(organizacaoMilitar);
			return nova(organizacaoMilitar, sistema);
			
		}
		
		try {
			cadastroOMService.salvar(organizacaoMilitar);
		} catch (OMJaCadastradaException e) {
			result.rejectValue("sigla", e.getMessage(), e.getMessage());
			return nova(organizacaoMilitar, sistema);
			
		}		
		attributes.addFlashAttribute("mensagem", "Organização Militar salva com sucesso! ");
		return new ModelAndView("redirect:/oms/nova");
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(OMFilter omFilter, BindingResult result
			, @PageableDefault(size = 7) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("organizacaoMilitar/PesquisaOMs");			
		mv.addObject("organizacoesMilitares", oms.findAll());
	
		PageWrapper<OrganizacaoMilitar> paginaWrapper = new PageWrapper<>(oms.filtrar(omFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
	
		return mv;
				
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		try {
			cadastroOMService.excluir(codigo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo, @AuthenticationPrincipal UsuarioSistema sistema) {
		OrganizacaoMilitar organizacaoMilitar = oms.findOne(codigo);
		Regiao regiao = regioes.findOne(codigo);
		ModelAndView mv = nova(organizacaoMilitar, sistema);
		
		mv.addObject(organizacaoMilitar);
		organizacaoMilitar.setRegiao(regiao);
		organizacaoMilitar.setEstado(organizacaoMilitar.getEstado());		
		return mv;
	}
	
}
