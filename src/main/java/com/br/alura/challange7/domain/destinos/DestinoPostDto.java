package com.br.alura.challange7.domain.destinos;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DestinoPostDto(
		@NotBlank
		byte[] foto,
		@NotBlank
		byte[] foto2,
		@NotBlank
		BigDecimal preco,
		@NotBlank
		@Size(max = 255)
		String nome,
		@Size(max = 160)
		@NotBlank
		String meta,
		@Size(max = 300)
		String textoDescritivo
		) {

	public Destino toEntity() throws IOException {
		return new Destino(this.foto, this.foto2, this.preco, 
				this.nome, this.meta, this.textoDescritivo);
	}

}
