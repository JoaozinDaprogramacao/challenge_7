package com.br.alura.challange7.domain.depoimentos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "depoimentos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Depoimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] foto;
	@Column(length = 500)
	private String depoimento;
	@Column(length = 255, name = "nome_pessoa")
	private String nomePessoa;
	
	public Depoimento(byte[] foto, String depoimento, String nomePessoa) {
		this.foto = foto;
		this.depoimento = depoimento;
		this.nomePessoa = nomePessoa;
	}
	
	
}
