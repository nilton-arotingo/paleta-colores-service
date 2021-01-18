package com.integra.colores.dominio.entidad.request;

import com.integra.colores.aplicacion.validacion.ColorValid;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ColorValid
public class ColorRequest {
	
	private String name;
	private String year;
	private String color;
	private String pantoneValue;
	
}
