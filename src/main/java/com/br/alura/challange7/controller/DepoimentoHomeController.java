package com.br.alura.challange7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.br.alura.challange7.domain.depoimentos.DepoimentoGetDto;
import com.br.alura.challange7.domain.depoimentos.DepoimentoService;

@Controller
@RequestMapping("/depoimentos-home")
public class DepoimentoHomeController {

	@Autowired
	private DepoimentoService service;
	
	@GetMapping
	public ResponseEntity<List<DepoimentoGetDto>> getDepoimentosHome() {
		
		return service.get3Elements();
		 
	}
	
}
