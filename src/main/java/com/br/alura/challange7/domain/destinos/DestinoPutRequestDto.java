package com.br.alura.challange7.domain.destinos;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Size;

public record DestinoPutRequestDto(
		MultipartFile foto, 
		MultipartFile foto2, 
		BigDecimal preco,
		@Size(max = 255)
		String nome,
		@Size(max = 160)
		String meta,
		@Size(max = 300)
		String textoDescritivo
		) {

	

}
