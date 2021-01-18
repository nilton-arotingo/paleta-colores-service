package com.integra.colores.dominio.entidad.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ColorResponse {
	
	private Long id;
	private String name;
	private String color;

}
