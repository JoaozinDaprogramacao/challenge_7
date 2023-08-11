package com.br.alura.challange7.domain.depoimentos;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.alura.challange7.infra.exceptions.InternalEntityNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class DepoimentoService {

	@Autowired
	private DepoimentoRepository repository;

	public ResponseEntity<List<DepoimentoGetDto>> get3Elements() {
		List<Depoimento> depoimentosEntity = repository.findAll();
		List<DepoimentoGetDto> response = new ArrayList<DepoimentoGetDto>();

		depoimentosEntity.forEach(d -> response.add(new DepoimentoGetDto(d)));

		if (response.size() <= 3) {
			return ResponseEntity.ok(response);
		} else {

			List<DepoimentoGetDto> depoimentosDto = new ArrayList<DepoimentoGetDto>();
			Random rand = new Random();

			while (depoimentosDto.size() < 3) {
				int randomIndex = rand.nextInt(response.size());

				DepoimentoGetDto dto = response.get(randomIndex);

				if (!depoimentosDto.contains(dto)) {
					depoimentosDto.add(dto);
				}

			}

			return ResponseEntity.ok(depoimentosDto);

		}

	}

	public ResponseEntity<DepoimentoGetDto> add(String pessoaNome, String depoimento, byte[] bytes) {
		DepoimentoPostDto dto = new DepoimentoPostDto(bytes, pessoaNome, depoimento);
		ValidDepoimento(dto);
		Depoimento depoimentoEntity = dto.toEntity();
		repository.save(depoimentoEntity);

		String uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(depoimentoEntity.getId()).toUriString();

		return ResponseEntity.created(URI.create(uri))
				.body(new DepoimentoGetDto(dto.foto(), dto.nomePessoa(), dto.depoimento()));
	}

	private DepoimentoPostDto ValidDepoimento(@Valid DepoimentoPostDto dto) {
		return dto;
	}

	public ResponseEntity<?> getAll() {

		List<Depoimento> allEntitys = repository.findAll();

		if (allEntitys.isEmpty()) {
			return ResponseEntity.badRequest().body("Nenhum elemento encontrado!");
		}

		List<DepoimentoGetDto> allDtos = new ArrayList<DepoimentoGetDto>();
		allEntitys.forEach(e -> allDtos.add(new DepoimentoGetDto(e)));

		return ResponseEntity.ok(allDtos);
	}

	public ResponseEntity<?> getElementById(long id) {
		if (!repository.existsById(id)) {
			throw new InternalEntityNotFoundException("Nenhum depoimento foi encontrado");
		}

		Depoimento depoimento = repository.getReferenceById(id);

		return ResponseEntity.ok().body(new DepoimentoGetDto(depoimento));
	}

	public ResponseEntity<?> deleteElementById(long id) {
		if (!repository.existsById(id)) {
			throw new InternalEntityNotFoundException("Nenhum depoimento foi encontrado");
		}

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

	@Transactional
	public ResponseEntity<?> updateElementById(long id, 
			DepoimentoPutDto dto) {
		if (!repository.existsById(id)) {
			throw new InternalEntityNotFoundException("Nenhum depoimento foi encontrado");
		}

		Depoimento entity = repository.getReferenceById(id);

		if (!(dto.depoimento() == null)) {
			entity.setDepoimento(dto.depoimento());
		}

		if (!(dto.foto() == null)) {
			entity.setFoto(dto.foto());
		}

		if (!(dto.nomePessoa() == null)) {
			entity.setNomePessoa(dto.nomePessoa());
		}

		return ResponseEntity.ok().body(new DepoimentoGetDto(entity));
	}

}
