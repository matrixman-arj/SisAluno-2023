package br.mil.eb.decex.sisaluno.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.mil.eb.decex.sisaluno.controller.page.PageWrapper;
import br.mil.eb.decex.sisaluno.model.Estado;
import br.mil.eb.decex.sisaluno.repository.Estados;
import br.mil.eb.decex.sisaluno.repository.Regioes;
import br.mil.eb.decex.sisaluno.repository.RegioesMilitares;
import br.mil.eb.decex.sisaluno.repository.filter.EstadoFilter;
import br.mil.eb.decex.sisaluno.service.CadastroEstadoService;
import br.mil.eb.decex.sisaluno.service.exception.NomeEstadoJaCadastradoException;

@Controller
@RequestMapping("/estados")
public class EstadosController {
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private Regioes regioes;
	
	@Autowired
	private RegioesMilitares rms;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Estado estado) {
		ModelAndView mv = new ModelAndView("estado/CadastroEstado");
		mv.addObject("regioes", regioes.findAll());
		mv.addObject("regioesMilitares", rms.findAll());
		mv.addObject("estados", estados.findAll());
		return mv;
	}
	
	@Cacheable(value = "estados", key = "#codigoRegiao")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Estado> pesquisarPorCodigoRegiao(
		   @RequestParam(name = "regiao", defaultValue = "-1") Long codigoRegiao) {	
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		return estados.findByRegiaoCodigo(codigoRegiao);
	}
	
	@PostMapping("/novo")
	@CacheEvict(value = "estados", key = "#estado.regiao.codigo", condition = "#estado.temRegiao()")
	public ModelAndView salvar(@Valid Estado estado, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(estado);
		}
		
		try {
			cadastroEstadoService.salvar(estado);
		} catch (NomeEstadoJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(estado);
		}
		
		attributes.addFlashAttribute("mensagem", "Estado salvo com sucesso!");
		return new ModelAndView("redirect:/estados/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(EstadoFilter estadoFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("estado/PesquisaEstados");
		mv.addObject("estados", estados.findAll());
		mv.addObject("regioes", regioes.findAll());
		
		PageWrapper<Estado> paginaWrapper = new PageWrapper<>(estados.filtrar(estadoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

}
