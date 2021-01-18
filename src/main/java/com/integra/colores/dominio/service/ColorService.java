package com.integra.colores.dominio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.integra.colores.dominio.entidad.Color;
import com.integra.colores.dominio.entidad.response.ColorResponse;
import com.integra.colores.dominio.entidad.response.PaginacionResponse;
import com.integra.colores.dominio.entidad.response.Resultado;
import com.integra.colores.dominio.exception.ColorServiceException;
import com.integra.colores.infraestructura.repository.ColorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ColorService {
	
	public static final int CANT_ELEMENTO_POR_PAGINA = 4;
	
	@Autowired
	private ColorRepository colorRepository;
	
	public List<ColorResponse> obtenerLista() {
		List<ColorResponse> colores = new ArrayList<>();
		try {
			List<Object[]> listaObj = colorRepository.listarColores();
			for(Object[] color : listaObj) {
				colores.add(ColorResponse.builder()
						.id(Long.valueOf(String.valueOf(color[0]))).name(String.valueOf(color[1])).color(String.valueOf(color[2]))
						.build());
			}
			
			return colores;
		} catch (Exception e) {
			log.error("Error al obtener lista de colores: ", e);
			throw new ColorServiceException("Error al obtener lista de colores");
		}
	}
	
	public PaginacionResponse obtenerListaColoresPorPagina(int paginaActual) {
		List<ColorResponse> colores = new ArrayList<>();
		try {
			Page<Object[]> listaPaginado = colorRepository.listarColoresPorPagina(PageRequest.of(paginaActual - 1, CANT_ELEMENTO_POR_PAGINA));
			for(Object[] color : listaPaginado) {
				colores.add(ColorResponse.builder()
						.id(Long.valueOf(String.valueOf(color[0]))).name(String.valueOf(color[1])).color(String.valueOf(color[2]))
						.build());
			}
			
			return PaginacionResponse.builder()
					.paginaActual(paginaActual)
					.numeroPaginas(listaPaginado.getTotalPages())
					.totalElementos(listaPaginado.getTotalElements())
					.colores(colores)
					.build();
		} catch (Exception e) {
			log.error("Error al obtener lista de colores: ", e);
			throw new ColorServiceException("Error al obtener lista de colores");
		}
	}
	
	public Color obtenerColorById(Long id) {
		try {
			Optional<Color> colorOpt = colorRepository.findById(id);
			return colorOpt.isPresent() ? colorOpt.get() : null;
		} catch (Exception e) {
			log.error("Error al consultar color por su id: ", e);
			throw new ColorServiceException("Error al obtener lista de colores");
		}
		
	}
	
	public Resultado guardarColor(Color color){		
		try {
			colorRepository.save(color);
			return Resultado.builder().mensaje("Registro Ok").build();
		} catch (Exception e) {
			log.error("Error al registrar color: ", e);
			throw new ColorServiceException("Error al registrar color");
		}
	}

}
