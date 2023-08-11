package com.br.alura.challange7.infra.exceptions;

public class InternalEntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InternalEntityNotFoundException(String message) {
        super(message);
    }
}