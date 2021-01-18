package com.integra.colores.aplicacion.validacion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.integra.colores.dominio.entidad.request.ColorRequest;

public class ColorValidacion implements ConstraintValidator<ColorValid, ColorRequest> {
	
	@Override
	public boolean isValid(ColorRequest value, ConstraintValidatorContext context) {
		
		if (StringUtils.isEmpty(value.getName())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{com.integra.validacion.color..color.nameRequired.message}").addConstraintViolation();
			return false;
		}
		
		if (value.getName().length() > 20) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{com.integra.validacion.color.nameInvalid.message}").addConstraintViolation();
			return false;
		}
		
		if (StringUtils.isEmpty(value.getYear())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{com.integra.validacion.color.yearRequired.message}").addConstraintViolation();
			return false;
		}
		
		if (value.getYear().length() != 4) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{com.integra.validacion.color.yearInvalid.message}").addConstraintViolation();
			return false;
		}
		
		if (StringUtils.isEmpty(value.getColor())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{com.integra.validacion.color.colorRequired.message}").addConstraintViolation();
			return false;
		}
		
		if (value.getColor().length() != 7) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{com.integra.validacion.color.colorInvalid.message}").addConstraintViolation();
			return false;
		}
		
		if (!value.getColor().startsWith("#")) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{com.integra.validacion.color.colorInvalid.message}").addConstraintViolation();
			return false;
		}
		
		if (StringUtils.isEmpty(value.getPantoneValue())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{com.integra.validacion.color.pantoneValueRequired.message}").addConstraintViolation();
			return false;
		}
		
		if (value.getPantoneValue().length() != 7) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("{com.integra.validacion.color.pantoneValueInvalid.message}").addConstraintViolation();
			return false;
		}
		
		return true;
	}

}
