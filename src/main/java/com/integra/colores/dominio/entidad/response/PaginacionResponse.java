package com.integra.colores.dominio.entidad.response;

import java.util.List;

import com.integra.colores.dominio.service.ColorService;

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
public class PaginacionResponse {
		
	private int paginaActual;
	private int numeroPaginas;
	private Long totalElementos;
	private int cantElementoPorPagina;
	private List<ColorResponse> colores;
	
	public int getNumeroPaginas() {
		numeroPaginas = (int) Math.ceil((float) totalElementos / ColorService.CANT_ELEMENTO_POR_PAGINA);
		return numeroPaginas;
	}
	
	public int getCantElementoPorPagina() {
		cantElementoPorPagina = ColorService.CANT_ELEMENTO_POR_PAGINA;
		return cantElementoPorPagina;
	}

}
