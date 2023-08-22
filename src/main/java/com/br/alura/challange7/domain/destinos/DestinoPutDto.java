package com.br.alura.challange7.domain.destinos;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.validation.constraints.Size;

public record DestinoPutDto(
		byte[] foto, 
		byte[] foto2, 
		BigDecimal preco,
		@Size(max = 255)
		String nome,
		@Size(max = 160)
		String meta,
		@Size(max = 300)
		String textoDescritivo
		) {

	public DestinoPutDto(DestinoPutRequestDto dtoRequest) throws IOException {
		this(dtoRequest.foto().getBytes(), dtoRequest.foto2().getBytes(),
				dtoRequest.preco(), dtoRequest.nome(), dtoRequest.meta(),
				dtoRequest.textoDescritivo());
	}

}
