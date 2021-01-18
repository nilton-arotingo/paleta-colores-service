package com.integra.colores.dominio.exception;

public class ColorServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ColorServiceException(String mensaje) {
		super(mensaje);
	}

}
