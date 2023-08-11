package com.br.alura.challange7.infra.exceptions;

public class UniqueException extends Exception {

	private static final long serialVersionUID = 1L;

	public UniqueException(String message) {
		super(message);
	}

	
}
