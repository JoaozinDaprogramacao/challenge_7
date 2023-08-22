package com.br.alura.challange7.domain.destinos;

import java.io.IOException;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "nome")
public class DestinoPostDto {
		@NotBlank
		private byte[] foto;
		@NotBlank
		private byte[] foto2;
		@NotBlank
		private BigDecimal preco;
		@NotBlank
		@Size(max = 255)
		private String nome;
		@Size(max = 16)
		@NotBlank
		private String meta;
		@Size(max = 300)
		private String textoDescritivo;
		 

	public Destino toEntity() throws IOException {
		return new Destino(this.foto, this.foto2, this.preco, 
				this.nome, this.meta, this.textoDescritivo);
	}

}
