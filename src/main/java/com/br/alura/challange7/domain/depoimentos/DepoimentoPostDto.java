package com.br.alura.challange7.domain.depoimentos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DepoimentoPostDto(
		@NotBlank
		byte[] foto,
		@NotBlank
		@Size(max = 255)
		String nomePessoa,
		@NotBlank
		@Size(max = 500)
		String depoimento
		) {

	public Depoimento toEntity() {
		return new Depoimento(this.foto, this.nomePessoa,
				this.depoimento);
	}

}
