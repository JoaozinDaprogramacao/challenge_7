package com.br.alura.challange7.infra.exceptions;

public class InternalValidationDtoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InternalValidationDtoException(String message) {
        super(message);
    }
}
