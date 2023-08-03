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
import br.mil.eb.decex.sisaluno.enumerated.CFGOCurso;
import br.mil.eb.decex.sisaluno.enumerated.CFGSCurso;
import br.mil.eb.decex.sisaluno.enumerated.CPORCurso;
import br.mil.eb.decex.sisaluno.enumerated.Categoria;
import br.mil.eb.decex.sisaluno.enumerated.ColegioMiltar;
import br.mil.eb.decex.sisaluno.enumerated.Escolaridade;
import br.mil.eb.decex.sisaluno.enumerated.Etnia;
import br.mil.eb.decex.sisaluno.enumerated.MatBelCurso;
import br.mil.eb.decex.sisaluno.enumerated.MedicoCurso;
import br.mil.eb.decex.sisaluno.enumerated.ODONTOCurso;
import br.mil.eb.decex.sisaluno.enumerated.OficiaisCurso;
import br.mil.eb.decex.sisaluno.enumerated.OrigemAeronautica;
import br.mil.eb.decex.sisaluno.enumerated.OrigemCivilOuMilitar;
import br.mil.eb.decex.sisaluno.enumerated.OrigemEscolar;
import br.mil.eb.decex.sisaluno.enumerated.OrigemExercito;
import br.mil.eb.decex.sisaluno.enumerated.OrigemMarinha;
import br.mil.eb.decex.sisaluno.enumerated.Paises;
import br.mil.eb.decex.sisaluno.enumerated.Periodo;
import br.mil.eb.decex.sisaluno.enumerated.QCOCurso;
import br.mil.eb.decex.sisaluno.enumerated.Religiao;
import br.mil.eb.decex.sisaluno.enumerated.RendaFamiliar;
import br.mil.eb.decex.sisaluno.enumerated.ResideCom;
import br.mil.eb.decex.sisaluno.enumerated.TAF;
import br.mil.eb.decex.sisaluno.model.Complemento;
import br.mil.eb.decex.sisaluno.repository.Anos;
import br.mil.eb.decex.sisaluno.repository.Complementos;
import br.mil.eb.decex.sisaluno.repository.Estados;
import br.mil.eb.decex.sisaluno.repository.Npors;
import br.mil.eb.decex.sisaluno.repository.OMs;
import br.mil.eb.decex.sisaluno.repository.Situacoes;
import br.mil.eb.decex.sisaluno.repository.Uetes;
import br.mil.eb.decex.sisaluno.repository.filter.ComplementoFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;
import br.mil.eb.decex.sisaluno.service.CadastroComplementoService;
import br.mil.eb.decex.sisaluno.service.exception.CpfComplementoJaCadastradoException;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/complementos")
public class ComplementosController {
	
	@Autowired
	private CadastroComplementoService cadastroComplementoService;
	
	@Autowired
	private Complementos complementos;
	
	@Autowired
	private OMs oms;
	
	@Autowired
	private Npors npors;
	
	@Autowired
	private Uetes uetes;
	
	@Autowired
	private Estados estados;
	
	@Autowired
	private Anos anos;
	
	@Autowired
	private Situacoes situacoes;
		
	@RequestMapping("/novo")
	public ModelAndView novo(Complemento complemento) {
		ModelAndView mv = new ModelAndView("complemento/CadastroComplemento");
		mv.addObject("etnias", Etnia.values());
		mv.addObject("origens", OrigemEscolar.values());
		mv.addObject("origensCivOuMil", OrigemCivilOuMilitar.values());
		mv.addObject("origensEB", OrigemExercito.values());
		mv.addObject("origensMB", OrigemMarinha.values());
		mv.addObject("origensFAB", OrigemAeronautica.values());
		mv.addObject("paisess", Paises.values());
		mv.addObject("rendas", RendaFamiliar.values());
		mv.addObject("resides", ResideCom.values());
		mv.addObject("situacoesNoCurso", situacoes.findAll());
		mv.addObject("colegiosMilitares", ColegioMiltar.values());
		mv.addObject("uetes", uetes.findAll());
		mv.addObject("cporCursos", CPORCurso.values());
		mv.addObject("nporCursos", CFGSCurso.values());
		mv.addObject("organizacoesMilitares",oms.findAll());
		mv.addObject("OficiaisReserva",npors.findAll());
		mv.addObject("categorias", Categoria.values());
		mv.addObject("matBelcursos", MatBelCurso.values());
		mv.addObject("oficiaisCursos", OficiaisCurso.values());
		mv.addObject("cfgsCursos", CFGSCurso.values());
		mv.addObject("cfgoCursos", CFGOCurso.values());
		mv.addObject("medicoCursos", MedicoCurso.values());
		mv.addObject("odontoCursos", ODONTOCurso.values());
		mv.addObject("qcoCursos", QCOCurso.values());
		mv.addObject("tafs", TAF.values());
		mv.addObject("religioes", Religiao.values());
		mv.addObject("escolaridades", Escolaridade.values());
		mv.addObject("naturalidades", estados.findAll());
//		mv.addObject("anos", Ano.values());
		mv.addObject("anoais", anos.findAll());
		mv.addObject("periodos", Periodo.values());
		
		
		return mv;
	}	
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Complemento complemento, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			model.addAttribute(complemento);
			return novo(complemento);
		}
				
		try {
			cadastroComplementoService.salvar(complemento);
		}catch(CpfComplementoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return novo(complemento);
		}
		
		attributes.addFlashAttribute("mensagem", "Aluno salvo com sucesso!");
		return new ModelAndView ("redirect:/complementos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(ComplementoFilter complementoFilter, BindingResult result, @PageableDefault(size = 12) Pageable pageable, HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema sistema ) { 
		ModelAndView mv = new ModelAndView("complemento/PesquisaComplementos");
		
		PageWrapper<Complemento> paginaWrapper  = new PageWrapper<>(complementos.filtrar(complementoFilter, pageable), httpServletRequest);		
		mv.addObject("pagina", paginaWrapper );		
		
		PageWrapper<Complemento> paginaWrapper2 = new PageWrapper<>(complementos.filtrarPelaOmUsuLogado(complementoFilter, pageable, sistema), httpServletRequest);
		mv.addObject("paginaOmUsuLogado", paginaWrapper2);
		
		return mv;
	}
	
//	@GetMapping
//	public ModelAndView pesquisar(ComplementoFilter complementoFilter, Criteria criteria, String cpf, BindingResult result,@PageableDefault(size = 12) Pageable pageable, HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema sistema ) { 
//		ModelAndView mv = new ModelAndView("complemento/PesquisaComplementos");
//		
//		PageWrapper<Complemento> paginaWrapper  = new PageWrapper<>(complementos.filtrar(complementoFilter, pageable, criteria, cpf), httpServletRequest);		
//		mv.addObject("pagina", paginaWrapper );		
//		
//		PageWrapper<Complemento> paginaWrapper2 = new PageWrapper<>(complementos.filtrarPelaOmUsuLogado(complementoFilter, pageable, sistema), httpServletRequest);
//		mv.addObject("paginaOmUsuLogado", paginaWrapper2);
//		
//		return mv;
//	}
			
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		try {
			cadastroComplementoService.excluir(codigo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo, @AuthenticationPrincipal UsuarioSistema sistema) {
		Complemento complemento = complementos.findOne(codigo);
		
		ModelAndView mv = novo(complemento);		
		mv.addObject(complemento);
		
		return mv;
	}
	
		
}
