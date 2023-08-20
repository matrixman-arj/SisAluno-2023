package br.mil.eb.decex.sisaluno.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.mil.eb.decex.sisaluno.model.Matricula;

@Component
public class MatriculaValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Matricula.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {		
		ValidationUtils.rejectIfEmpty(errors, "aluno.codigo","", "Selecione um aluno na pesquisa rápida");
		
		Matricula matricula = (Matricula) target;		
		ValidarSeDataFinalEMenorQueInicial(errors, matricula);
		validarSeInformouUmCurso(errors, matricula);
		
		verificaSeInformouPeriodo(errors, matricula);
		verificaSeInformouDataDeInicio(errors, matricula);
		verificaSeInformouDataDeConclusão(errors, matricula);
		verificaSeInformouPeriodo(errors, matricula);
		verificaSeInformouAnoLetivo(errors, matricula);
	}

	
	private void validarSeInformouUmCurso(Errors errors, Matricula matricula) {
		if(matricula.getItens().isEmpty()) {
			errors.reject("", "Adicione um curso para continuar com a matricula!");
		}
	}

	private void ValidarSeDataFinalEMenorQueInicial(Errors errors, Matricula matricula) {
		if(matricula.getDataFinalCurso() != null && matricula.getDataFinalCurso().isBefore(matricula.getDataInicioCurso())){
			errors.rejectValue("dataFinalCurso", "", "A data de previsão de conclusão, não pode ser menor que a data de inicio do curso.");
		}
	}
	
	private void verificaSeInformouPeriodo(Errors errors, Matricula matricula) {
		if(matricula.getPeriodo() == null) {
			errors.reject("periodo", "O Campo período é obrigatório");
		}
	}
	
	private void verificaSeInformouDataDeInicio(Errors errors, Matricula matricula) {
		if(matricula.getDataInicioCurso() == null) {
			errors.reject("dataInicioCurso", "A data de inicio do curso na aba previsão é obrigatória");
		}
	}
	
	private void verificaSeInformouDataDeConclusão(Errors errors, Matricula matricula) {
		if(matricula.getDataFinalCurso() == null) {
			errors.reject("dataFinalCurso", "A data previsão de conclusão na aba previsão é obrigatória");
		}
	}
	
	private void verificaSeInformouAnoLetivo(Errors errors, Matricula matricula) {
		if(matricula.getAnoLetivo() == null) {
			errors.reject("anoLetivo", "O campo ano letivo na aba previsão é obrigatório");
		}
	}

}
