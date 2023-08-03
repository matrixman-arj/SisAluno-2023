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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.mil.eb.decex.sisaluno.controller.page.PageWrapper;
import br.mil.eb.decex.sisaluno.enumerated.ColegioMiltar;
import br.mil.eb.decex.sisaluno.enumerated.Escolaridade;
import br.mil.eb.decex.sisaluno.enumerated.Etnia;
import br.mil.eb.decex.sisaluno.enumerated.OrigemAeronautica;
import br.mil.eb.decex.sisaluno.enumerated.OrigemCivilOuMilitar;
import br.mil.eb.decex.sisaluno.enumerated.OrigemEscolar;
import br.mil.eb.decex.sisaluno.enumerated.OrigemExercito;
import br.mil.eb.decex.sisaluno.enumerated.OrigemMarinha;
import br.mil.eb.decex.sisaluno.enumerated.Paises;
import br.mil.eb.decex.sisaluno.enumerated.Religiao;
import br.mil.eb.decex.sisaluno.enumerated.RendaFamiliar;
import br.mil.eb.decex.sisaluno.enumerated.ResideCom;
import br.mil.eb.decex.sisaluno.model.Aluno;
import br.mil.eb.decex.sisaluno.repository.Alunos;
import br.mil.eb.decex.sisaluno.repository.Estados;
import br.mil.eb.decex.sisaluno.repository.filter.AlunoFilter;
import br.mil.eb.decex.sisaluno.security.UsuarioSistema;
import br.mil.eb.decex.sisaluno.service.CadastroAlunoService;
import br.mil.eb.decex.sisaluno.service.exception.CpfAlunoJaCadastradoException;
import br.mil.eb.decex.sisaluno.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
	
	@Autowired
	private CadastroAlunoService cadastroAlunoService;
	
	@Autowired
	private Alunos alunos;
	
	@Autowired
	private Estados estados;
	

		
	@RequestMapping("/novo")
	public ModelAndView novo(Aluno aluno) {
		ModelAndView mv = new ModelAndView("aluno/CadastroAluno");
		mv.addObject("naturalidades", estados.findAll());
		mv.addObject("religioes", Religiao.values());
		mv.addObject("origens", OrigemEscolar.values());
		mv.addObject("colegiosMilitares", ColegioMiltar.values());
		mv.addObject("etnias", Etnia.values());
		mv.addObject("origensCivOuMil", OrigemCivilOuMilitar.values());
		mv.addObject("origensEB", OrigemExercito.values());
		mv.addObject("origensMB", OrigemMarinha.values());
		mv.addObject("origensFAB", OrigemAeronautica.values());
		mv.addObject("escolaridades", Escolaridade.values());
		
		mv.addObject("paisess", Paises.values());
		mv.addObject("rendas", RendaFamiliar.values());		
		mv.addObject("resides", ResideCom.values());		
		return mv;
	}	
	
	@RequestMapping(value = { "/novo", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Aluno aluno, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			model.addAttribute(aluno);
			return novo(aluno);
		}
				
		try {
			cadastroAlunoService.salvar(aluno);
		}catch(CpfAlunoJaCadastradoException e) {
			result.rejectValue("cpf", e.getMessage(), e.getMessage());
			return novo(aluno);
		}
		
		attributes.addFlashAttribute("mensagem", "Aluno salvo com sucesso!");
		return new ModelAndView ("redirect:/alunos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(AlunoFilter alunoFilter, BindingResult result, @PageableDefault(size = 12) Pageable pageable, HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema sistema ) { 
		ModelAndView mv = new ModelAndView("aluno/PesquisaAlunos");
		
		PageWrapper<Aluno> paginaWrapper  = new PageWrapper<>(alunos.filtrar(alunoFilter, pageable), httpServletRequest);		
		mv.addObject("pagina", paginaWrapper );		
		
		PageWrapper<Aluno> paginaWrapper2 = new PageWrapper<>(alunos.filtrarPelaOmUsuLogado(alunoFilter, pageable, sistema), httpServletRequest);
		mv.addObject("paginaOmUsuLogado", paginaWrapper2);
		
		return mv;
	}
	
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Aluno> pesquisar(String cpf) {
		validarTamanhoCpf(cpf);
		return alunos.findByCpfStartingWithIgnoreCase(cpf);
	}

	private void validarTamanhoCpf(String cpf) {
		if (StringUtils.isEmpty(cpf) || cpf.length() < 3) {
			throw new IllegalArgumentException();
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}

//	@GetMapping
//	public ModelAndView pesquisar(AlunoFilter alunoFilter, Criteria criteria, String cpf, BindingResult result,@PageableDefault(size = 12) Pageable pageable, HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema sistema ) { 
//		ModelAndView mv = new ModelAndView("aluno/PesquisaAlunos");
//		
//		PageWrapper<Aluno> paginaWrapper  = new PageWrapper<>(alunos.filtrar(alunoFilter, pageable, criteria, cpf), httpServletRequest);		
//		mv.addObject("pagina", paginaWrapper );		
//		
//		PageWrapper<Aluno> paginaWrapper2 = new PageWrapper<>(alunos.filtrarPelaOmUsuLogado(alunoFilter, pageable, sistema), httpServletRequest);
//		mv.addObject("paginaOmUsuLogado", paginaWrapper2);
//		
//		return mv;
//	}
			
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Long codigo) {
		try {
			cadastroAlunoService.excluir(codigo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo, @AuthenticationPrincipal UsuarioSistema sistema) {
		Aluno aluno = alunos.findOne(codigo);
		
		ModelAndView mv = novo(aluno);		
		mv.addObject(aluno);
		
		return mv;
	}
	
		
}
