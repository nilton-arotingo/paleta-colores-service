package com.integra.colores.aplicacion.validacion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { ColorValidacion.class })
public @interface ColorValid {
	
	String message() default "{com.integra.validacion.color.message}";
    
    Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
