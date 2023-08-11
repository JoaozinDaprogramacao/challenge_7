package com.br.alura.challange7.domain.depoimentos;

import jakarta.validation.constraints.Size;

public record DepoimentoPutDto(
		byte[] foto,
		@Size(max = 255)
		String nomePessoa,
		@Size(max = 500)
		String depoimento
		) {

	public Depoimento toEntity() {
		return new Depoimento(this.foto, this.nomePessoa,
				this.depoimento);
	}

}
