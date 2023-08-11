package com.br.alura.challange7.domain.destinos;

import java.math.BigDecimal;

public record DestinoGetDto(Long id, byte[] foto, BigDecimal preco, String nome) {

	public DestinoGetDto(Destino destino) {
		this(destino.getId(), destino.getFoto(), destino.getPreco(), destino.getNome());
	}

}
