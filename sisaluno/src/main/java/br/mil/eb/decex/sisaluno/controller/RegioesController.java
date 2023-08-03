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
import br.mil.eb.decex.sisaluno.model.Regiao;
import br.mil.eb.decex.sisaluno.repository.Estados;
import br.mil.eb.decex.sisaluno.repository.Regioes;
import br.mil.eb.decex.sisaluno.repository.RegioesMilitares;
import br.mil.eb.decex.sisaluno.repository.filter.RegiaoFilter;
import br.mil.eb.decex.sisaluno.service.CadastroRegiaoService;
import br.mil.eb.decex.sisaluno.service.exception.NomeRegiaoJaCadastradaException;

@Controller
@RequestMapping("/regioes")
public class RegioesController {
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private Regioes regioes;
	
	@Autowired
	private RegioesMilitares rms;
	
	@Autowired
	private CadastroRegiaoService cadastroRegiaoService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Regiao regiao) {
		ModelAndView mv = new ModelAndView("regiao/CadastroRegiao");
		mv.addObject("regioes", regioes.findAll());
		mv.addObject("regioesMilitares", rms.findAll());
		mv.addObject("estados", estados.findAll());
		return mv;
	}
	
	@Cacheable(value = "regioes", key = "#codigoEstado")
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Regiao> pesquisarPorCodigoEstado(
		   @RequestParam(name = "regiao", defaultValue = "-1") Long codigoEstado) {	
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {}
		return regioes.findByEstadoCodigo(codigoEstado);
	}
	
	@PostMapping("/nova")
	@CacheEvict(value = "regioes", key = "#regiao.estado.codigo", condition = "#regiao.temEstado()")
	public ModelAndView salvar(@Valid Regiao regiao, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return nova(regiao);
		}
		
		try {
			cadastroRegiaoService.salvar(regiao);
		} catch (NomeRegiaoJaCadastradaException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(regiao);
		}
		
		attributes.addFlashAttribute("mensagem", "Região salva com sucesso!");
		return new ModelAndView("redirect:/regioes/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(RegiaoFilter regiaoFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("regiao/PesquisaRegioes");
		mv.addObject("estados", estados.findAll());
		mv.addObject("regioes", regioes.findAll());
		
		PageWrapper<Regiao> paginaWrapper = new PageWrapper<>(regioes.filtrar(regiaoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

}
