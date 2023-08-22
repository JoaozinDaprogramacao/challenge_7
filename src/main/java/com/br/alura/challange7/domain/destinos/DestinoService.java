package com.br.alura.challange7.domain.destinos;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.alura.challange7.infra.exceptions.InternalEntityNotFoundException;
import com.br.alura.challange7.infra.exceptions.UniqueException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class DestinoService {

	@Autowired
	private DestinoRepository repository;

	public ResponseEntity<DestinoGetDto> save(byte[] foto, byte[] foto2, BigDecimal preco, 
			String nome, String meta, String textoDescritivo) throws UniqueException, IOException {
		DestinoPostDto dto = new DestinoPostDto(foto, foto2, preco,
				nome, meta, textoDescritivo);
		validaDto(dto);

		if (repository.existsByNome(nome)) {
			throw new UniqueException(
					"Não foi possivel cadastrar pois já existe um destino " + "cadastrado com esse nome!");
		}

		Destino destino = dto.toEntity();
		repository.save(destino);

		String uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(destino.getId())
				.toUriString();

		return ResponseEntity.created(URI.create(uri))
				.body(new DestinoGetDto(
						destino.getId(), destino.getFoto(), destino.getFoto2(),
						destino.getPreco(), destino.getNome(), destino.getMeta(),
						destino.getTextoDescritivo()));

	}

	private void validaDto(@Valid DestinoPostDto dto) {
	}

	public ResponseEntity<?> getAll() {

		List<Destino> allEntitys = repository.findAll();

		if (allEntitys.isEmpty()) {
			return ResponseEntity.badRequest().body("Nenhum elemento encontrado!");
		}

		List<DestinoGetDto> allDtos = new ArrayList<DestinoGetDto>();
		allEntitys.forEach(e -> allDtos.add(new DestinoGetDto(e)));

		return ResponseEntity.ok(allDtos);
	}

	public ResponseEntity<?> getReferenceById(Long id) {
		if (!repository.existsById(id)) {
			throw new InternalEntityNotFoundException("Nenhum destino foi encontrado");
		}

		DestinoGetDto dto = new DestinoGetDto(repository.getReferenceById(id));

		return ResponseEntity.ok().body(dto);
	}

	public ResponseEntity<?> getReferenceByNome(String nome) {
		if (!repository.existsByNome(nome)) {
			throw new InternalEntityNotFoundException("Nenhum destino foi encontrado");
		}

		DestinoGetDto dto = new DestinoGetDto(repository.getReferenceByNome(nome));

		return ResponseEntity.ok().body(dto);
	}

	@Transactional
	public ResponseEntity<DestinoGetDto> updateReferenteById(Long id, DestinoPutRequestDto dtoRequest)
			throws IOException {
		if (!repository.existsById(id)) {
			throw new InternalEntityNotFoundException("Nenhum destino foi encontrado");
		}

		DestinoPutDto dto = new DestinoPutDto(dtoRequest);
		validaDto(dto);
		Destino entity = repository.getReferenceById(id);

		if (!(dto.preco() == null)) {
			entity.setPreco(dto.preco());
		}

		if (!(dto.foto() == null)) {
			entity.setFoto(dto.foto());
		}

		if (!(dto.nome() == null)) {
			entity.setNome(dto.nome());
		}

		return ResponseEntity.ok().body(new DestinoGetDto(entity));

	}

	private void validaDto(@Valid DestinoPutDto dto) {}

	public ResponseEntity<?> deleteElementById(long id) {
		if (!repository.existsById(id)) {
			throw new InternalEntityNotFoundException("Nenhum depoimento foi encontrado");
		}

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
