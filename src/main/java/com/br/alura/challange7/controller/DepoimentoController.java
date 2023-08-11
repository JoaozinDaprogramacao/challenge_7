package com.br.alura.challange7.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.br.alura.challange7.domain.depoimentos.DepoimentoGetDto;
import com.br.alura.challange7.domain.depoimentos.DepoimentoPutDto;
import com.br.alura.challange7.domain.depoimentos.DepoimentoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/depoimentos")
public class DepoimentoController {

	@Autowired
	private DepoimentoService service;

	@PostMapping
	public ResponseEntity<DepoimentoGetDto> saveDepoimento(@RequestParam("nomePessoa") String nomePessoa,
			@RequestParam("depoimento") String depoimento, @RequestParam("foto") MultipartFile foto)
			throws IOException {
		return service.add(nomePessoa, depoimento, foto.getBytes());
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable(value = "id") String id) {
		return service.getElementById(Long.parseLong(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") String id) {
		return service.deleteElementById(Long.parseLong(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@PathVariable(value = "id") String id,
			@Valid DepoimentoPutDto dto){

		return service.updateElementById(Long.parseLong(id), dto);
	}

}
