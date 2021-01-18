package com.integra.colores.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.integra.colores.dominio.entidad.Color;
import com.integra.colores.dominio.service.ColorService;
import com.integra.colores.infraestructura.repository.ColorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ColorServiceTest {
	
	@Autowired
	private ColorService colorService;
	
	@MockBean
	private ColorRepository colorRepository;
	
	@Test
	public void debeTraerTodaLaListaColores() {
		Object[] objeto1 = {1L, "rojo", "#123456"};
		Object[] objeto2 = {2L, "amarillo", "#654321"};
		Object[] objeto3 = {3L, "celeste", "#999123"};
		Object[] objeto4 = {4L, "negro", "#000000"};
		Object[] objeto5 = {5L, "blanco", "#999999"};
		Object[] objeto6 = {6L, "marron", "#000123"};
		List<Object[]> coloresObj = Arrays.asList(objeto1, objeto2, objeto3, objeto4, objeto5, objeto6);
		
		when(colorRepository.listarColores()).thenReturn(coloresObj);
		
		assertEquals(6, colorService.obtenerLista().size());
	}
	
	@Test
	public void debeTraerLaColoresPorPaginado() {
		int paginaActual = 2;
		Object[] objeto1 = {1L, "rojo", "#123456"};
		Object[] objeto2 = {2L, "amarillo", "#654321"};
		Object[] objeto3 = {3L, "celeste", "#999123"};
		Object[] objeto4 = {4L, "negro", "#000000"};
		Object[] objeto5 = {5L, "blanco", "#999999"};
		Object[] objeto6 = {6L, "marron", "#000123"};
		List<Object[]> coloresObj = Arrays.asList(objeto1, objeto2, objeto3, objeto4, objeto5, objeto6);
		Page<Object[]> coloresPage = new PageImpl<>(coloresObj);
		
		when(colorRepository.listarColoresPorPagina(PageRequest.of(paginaActual - 1, ColorService.CANT_ELEMENTO_POR_PAGINA)))
			.thenReturn(coloresPage);
		
		assertEquals(ColorService.CANT_ELEMENTO_POR_PAGINA, colorService.obtenerListaColoresPorPagina(paginaActual).getCantElementoPorPagina());
		
	}
	
	@Test
	public void debeTraerInformacionColorAlConsultarPorId() {
		Long id = 5L;
		Color color = Color.builder().id(id).name("azul").year("2020").color("#123456").pantoneValue("15-4020").build();
		
		when(colorRepository.findById(id)).thenReturn(Optional.of(color));
		
		assertEquals("azul", colorService.obtenerColorById(id).getName());
		assertEquals("#123456", colorService.obtenerColorById(id).getColor());
	}
	
	@Test
	public void debeRegistrarUnColorEnDB() {
		Color color = Color.builder().id(3L).name("azul").year("2020").color("#123456").pantoneValue("15-4020").build();
		
		when(colorRepository.save(color)).thenReturn(Mockito.any());
		
		assertEquals("Registro Ok", colorService.guardarColor(color).getMensaje());
	}

}
