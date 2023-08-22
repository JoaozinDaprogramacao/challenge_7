package com.br.alura.challange7.domain.destinos;

import java.math.BigDecimal;

public record DestinoGetDto(byte[] foto, byte[] foto2, 
		BigDecimal preco, String nome, String meta, String textoDescritivo) {

	public DestinoGetDto(Destino destino) {
		this(destino.getFoto(), destino.getFoto2(),
				destino.getPreco(), destino.getNome(), destino.getMeta(),
				destino.getTextoDescritivo());
	}

}
