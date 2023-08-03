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
import br.mil.eb.decex.sisaluno.enumerated.PostoGraduacao;
import br.mil.eb.decex.sisaluno.model.Usuario;
import br.mil.eb.decex.sisaluno.repository.Grupos;
import br.mil.eb.decex.sisaluno.repository.OMs;
import br.mil.eb.decex.sisaluno.repository.Usuarios;
import br.mil.eb.decex.sisaluno.repository.filter.UsuarioFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;
import br.mil.eb.decex.sisaluno.service.CadastroUsuarioService;
import br.mil.eb.decex.sisaluno.service.exception.IdentidadeJaCadastradaException;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;
import br.mil.eb.decex.sisaluno.service.exception.SenhaObrigatoriaUsuarioException;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private OMs oms;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private Grupos grupos;
	
	@Autowired
	private Usuarios usuarios;
	
	
	@RequestMapping("/novo")	
	public ModelAndView novo(Usuario usuario,  @AuthenticationPrincipal UsuarioSistema sistema) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");		
		mv.addObject("postos", PostoGraduacao.values());
		mv.addObject("organizacoesMilitares", oms.findAll());
		mv.addObject("grupos", grupos.findAll());
		mv.addObject("usuarios", usuarios.findAll());
		
		
		return mv;
	}
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST )
	public ModelAndView salvar (@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes, @AuthenticationPrincipal UsuarioSistema sistema) {
		if (result.hasErrors()) {
			model.addAttribute(usuario);
			return novo(usuario, sistema);			
		}
		
		try {			
			cadastroUsuarioService.salvar(usuario);			
		} catch (IdentidadeJaCadastradaException e) {
			result.rejectValue("identidade", e.getMessage(), e.getMessage());
			return novo(usuario, sistema);
			
		} catch(SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario, sistema);
			
		}	
		
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso! ");
		
		return new ModelAndView ("redirect:/usuarios/novo");
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter, BindingResult result, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("usuario/PesquisaUsuarios");
		mv.addObject("postos", PostoGraduacao.values());
		mv.addObject("organizacoesMilitares", oms.findAll());
		mv.addObject("grupos", grupos.findAll());
//		mv.addObject("usuarios", usuarios.filtrar(usuarioFilter));
		
		PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(usuarios.filtrar(usuarioFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		
		return mv;
	}
	
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo")  Long codigo, @AuthenticationPrincipal UsuarioSistema sistema) {
		Usuario usuario = usuarios.buscarComGrupos(codigo);
		ModelAndView mv = novo(usuario, sistema);
		mv.addObject(usuario);		
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Usuario usuario){
		try {
			cadastroUsuarioService.excluir(usuario);
				
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();	
	}
}
