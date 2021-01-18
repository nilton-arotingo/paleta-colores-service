package com.integra.colores.aplicacion.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.integra.colores.applicacion.configuration.SwaggerConfig;
import com.integra.colores.dominio.entidad.Color;
import com.integra.colores.dominio.entidad.request.ColorRequest;
import com.integra.colores.dominio.entidad.response.ColorResponse;
import com.integra.colores.dominio.entidad.response.PaginacionResponse;
import com.integra.colores.dominio.entidad.response.Resultado;
import com.integra.colores.dominio.service.ColorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/colores")
@Api(tags = SwaggerConfig.COLORES_CONTROLLER)
public class ColorController {
	
	@Autowired
	private ColorService colorService;
	
	@GetMapping
	@ApiOperation(value = "Obtiene la lista completa de colores", response = ColorResponse.class)
	public List<ColorResponse> listarColores() {
		return colorService.obtenerLista();
	}
	
	@GetMapping(value = "/paginado/{pagina}")
	@ApiOperation(value = "Obtiene lista de colores que devuelve 4 elementos por pagina", response = PaginacionResponse.class)
	public PaginacionResponse listarColoresPorPaginado(@PathVariable int pagina) {
		return colorService.obtenerListaColoresPorPagina(pagina);
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Obtiene datos de un color al consultar por el id", response = Color.class)
	public Color obtenerColorById(@PathVariable Long id) {
		return colorService.obtenerColorById(id);
	}
	
	@PostMapping
	@ApiOperation(value = "Registrar color en la base de datos", response = Resultado.class)
	public Resultado registrarColor(@Valid @RequestBody ColorRequest colorRequest) {
		Color color = Color.builder()       
				.name(colorRequest.getName()).year(colorRequest.getYear()).color(colorRequest.getColor()).pantoneValue(colorRequest.getPantoneValue())
				.build();
		return colorService.guardarColor(color);
	}

}
