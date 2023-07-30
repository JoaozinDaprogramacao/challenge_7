package com.br.alura.challange7.domain.depoimentos;

public record DepoimentoGetDto(byte[] foto, String nomePessoa, String depoimento) {
	
	public Depoimento toEntity(Depoimento depoimento) {
		return new Depoimento(this.foto, this.depoimento, this.nomePessoa);
	}

	public DepoimentoGetDto(Depoimento depoimento) {
		this(depoimento.getFoto(),
				depoimento.getNomePessoa(), depoimento.getDepoimento());
	}

	public boolean equals(DepoimentoGetDto dto) {
		return dto.depoimento.equals(this.depoimento);
	}

}
