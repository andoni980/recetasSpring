package com.recetasSpring.dominio.servicios;

import org.springframework.dao.DuplicateKeyException;

import lombok.Getter;
import lombok.experimental.StandardException;

@StandardException
public class ClaveDuplicadaException extends ServiciosException{
	private static final long serialVersionUID = 1L;

	@Getter
	private String objeto, campo;
	
	public ClaveDuplicadaException(String mensaje, String objeto, String campo, DuplicateKeyException e) {
		this(mensaje, e);
		
		this.objeto = objeto;
		this.campo = campo;
	}
}
