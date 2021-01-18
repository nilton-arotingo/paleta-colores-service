package com.integra.colores.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.integra.colores.dominio.entidad.Color;
import com.integra.colores.dominio.entidad.request.ColorRequest;
import com.integra.colores.dominio.entidad.response.ColorResponse;
import com.integra.colores.dominio.entidad.response.PaginacionResponse;
import com.integra.colores.dominio.entidad.response.Resultado;
import com.integra.colores.dominio.service.ColorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ColorControllerTest {

	private static final String ENDPOINT_LISTAR_COLORES = "/colores";

	private static final String ENDPOINT_PAGINADO_LISTA_COLORES = "/colores/paginado/%s";

	private static final String ENDPOINT_CONSULTAR_POR_COLOR = "/colores/%s";

	private static final String ENDPOINT_REGISTRAR_COLOR = "/colores";
	
	@Autowired
	private MockMvc mvc;

	@MockBean
	private ColorService colorService;
	
	@Test
	public void debeObtenerListaDeTodoLosColores() throws Exception {
		List<ColorResponse> colores = Arrays.asList(
				ColorResponse.builder().id(1L).name("azul").color("#123456").build(),
				ColorResponse.builder().id(2L).name("blanco").color("#234567").build(),
				ColorResponse.builder().id(3L).name("amarillo").color("#345678").build(),
				ColorResponse.builder().id(4L).name("celeste").color("#456789").build());
		
		when(colorService.obtenerLista()).thenReturn(colores);
		
		buildRequestListar(ENDPOINT_LISTAR_COLORES).andExpect(status().isOk());
	}
	
	@Test
	public void debeObtenerListaDeColoresConPaginado() throws Exception {
		int paginaActual = 2;
		List<ColorResponse> colores = Arrays.asList(
				ColorResponse.builder().id(5L).name("amarillo").color("#345678").build(),
				ColorResponse.builder().id(6L).name("celeste").color("#456789").build());
		PaginacionResponse paginaResponse = PaginacionResponse.builder()
				.paginaActual(paginaActual)
				.numeroPaginas(2)
				.totalElementos(6L)
				.cantElementoPorPagina(ColorService.CANT_ELEMENTO_POR_PAGINA)
				.colores(colores)
				.build();
		
		when(colorService.obtenerListaColoresPorPagina(paginaActual)).thenReturn(paginaResponse);
		
		buildRequestListar(String.format(ENDPOINT_PAGINADO_LISTA_COLORES, paginaActual)).andExpect(status().isOk());
	}
	
	@Test
	public void debeConsultarColorPorId() throws Exception {
		Long id = 3L;
		Color color = Color.builder().id(id).name("azul").year("2020").color("#123456").pantoneValue("15-4020").build();

		when(colorService.obtenerColorById(id)).thenReturn(color);
		
		buildRequestListar(String.format(ENDPOINT_CONSULTAR_POR_COLOR, id)).andExpect(status().isOk());
	}
	
	@Test
	public void debeRegistrarColor() throws Exception {
		ColorRequest request = ColorRequest.builder().name("azul").year("2020").color("#123456").pantoneValue("15-4020").build();
		Color color = Color.builder()
				.name(request.getName()).year(request.getYear()).color(request.getColor()).pantoneValue(request.getPantoneValue())
				.build();
		when(colorService.guardarColor(color)).thenReturn(Resultado.builder().mensaje("Registro Ok").build());
		
		ObjectMapper mapper = new ObjectMapper();
		mvc.perform(post(ENDPOINT_REGISTRAR_COLOR).accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)));
	}
	
	private ResultActions buildRequestListar(String endpoint) throws Exception {
		return mvc.perform(get(endpoint).contentType(MediaType.APPLICATION_JSON));
	}
	
}
