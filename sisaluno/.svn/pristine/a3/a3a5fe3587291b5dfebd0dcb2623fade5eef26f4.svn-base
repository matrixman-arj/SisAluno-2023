package br.mil.eb.decex.sisaluno.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.mil.eb.decex.sisaluno.model.Permissao;
import br.mil.eb.decex.sisaluno.repository.Permissoes;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.PermissaoJaCadastradaException;
import br.mil.eb.decex.sisaluno.service.CadastroPermService;

@Controller
@RequestMapping("/permissoes")
public class PermissoesController {
		
	@Autowired
	private Permissoes permissoes;	
	
	@Autowired
	private CadastroPermService cadastroPermissaoService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Permissao permissao) {
		ModelAndView mv = new ModelAndView("permissao/CadastroPermissao");		
		mv.addObject("permissoes", permissoes.findAll());
		
		return mv;
	}
	
//	@RequestMapping(value= "/nova", method = RequestMethod.POST)
	@RequestMapping(value = { "/nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Permissao permissao, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			model.addAttribute(permissao);
			return nova(permissao);
			
		}
		
		try {
			cadastroPermissaoService.salvar(permissao);
		} catch (PermissaoJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(permissao);
			
		}		
		attributes.addFlashAttribute("mensagem", "permissao salva com sucesso! ");
		return new ModelAndView("redirect:/permissoes/nova");
		
	}
	
	@GetMapping
	public ModelAndView pesquisar(Permissao permissao) {
		ModelAndView mv = new ModelAndView("permissao/PesquisaPermissoes");			
		mv.addObject("permissoes", permissoes.findAll());		
		
		return mv;
				
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		try {
			cadastroPermissaoService.excluir(codigo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Permissao permissao = permissoes.findOne(codigo);
		ModelAndView mv = nova(permissao);		
		mv.addObject(permissao);
		return mv;
	}
	
}
