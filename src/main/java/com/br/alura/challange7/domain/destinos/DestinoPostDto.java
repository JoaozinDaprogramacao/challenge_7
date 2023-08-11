package com.br.alura.challange7.domain.destinos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DestinoPostDto(
		@NotBlank
		byte[] foto,
		@NotBlank
		BigDecimal preco,
		@NotBlank
		@Size(max = 255)
		String nome
		) {

	public Destino toEntity() {
		return new Destino(this.foto, this.preco, this.nome);
	}

}
