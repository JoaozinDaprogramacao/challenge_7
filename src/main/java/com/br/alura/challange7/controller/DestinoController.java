package com.br.alura.challange7.controller;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.br.alura.challange7.domain.destinos.DestinoGetDto;
import com.br.alura.challange7.domain.destinos.DestinoPutRequestDto;
import com.br.alura.challange7.domain.destinos.DestinoService;
import com.br.alura.challange7.infra.exceptions.UniqueException;

@RestController
@RequestMapping("/destinos")
public class DestinoController {

	@Autowired
	private DestinoService service;

	@PostMapping
	public ResponseEntity<DestinoGetDto> post(@RequestParam("foto") MultipartFile file,
	        @RequestParam("preco") String precoParam,
	        @RequestParam("nome") String nome) throws IOException, UniqueException {
		
		return service.save(file.getBytes(), new BigDecimal(precoParam), nome);
	}
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String idParam) {
		return service.getReferenceById(Long.parseLong(idParam));
	}
	
	@GetMapping(params = "nome")
	public ResponseEntity<?> getByNome(@RequestParam("nome") String nome) {
		return service.getReferenceByNome(nome);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DestinoGetDto> put(@PathVariable("id") String idParam, 
			DestinoPutRequestDto dto) throws NumberFormatException, IOException{
		
		return service.updateReferenteById(Long.parseLong(idParam), dto);
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String idParam) {
		return service.deleteElementById(Long.parseLong(idParam));
	}
}
