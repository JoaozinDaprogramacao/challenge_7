package com.br.alura.challange7.domain.destinos;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.validation.constraints.Size;

public record DestinoPutDto(
		byte[] foto, 
		BigDecimal preco,
		@Size(max = 255)
		String nome
		) {

	public DestinoPutDto(DestinoPutRequestDto dtoRequest) throws IOException {
		this(dtoRequest.foto().getBytes(), dtoRequest.preco(), dtoRequest.nome());
	}

}
