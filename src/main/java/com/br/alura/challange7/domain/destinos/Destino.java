package com.br.alura.challange7.domain.destinos;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "destinos")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Destino {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] foto;
	@Column(precision = 10, scale = 2)
	private BigDecimal preco;
	private String nome;
	
	public Destino(byte[] foto, BigDecimal preco, String nome) {
		this.foto = foto;
		this.preco = preco;
		this.nome = nome;
	}
	
	
	
}
