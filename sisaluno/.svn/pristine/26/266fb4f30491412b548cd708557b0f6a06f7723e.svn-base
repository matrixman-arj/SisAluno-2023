package br.mil.eb.decex.sisaluno.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "(\\d{3}.?\\d{3}.?\\d{3}-?\\d{1}.?)?")
public @interface IDT {
	
	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "Identidade deve seguir o padrão 999.999.999-9";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};

}
