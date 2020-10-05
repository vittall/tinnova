package com.tinnova.processo.controller;

import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;

@RestController("/")
@AllArgsConstructor
public class VeiculoController {

	private VeiculoService veiculoService;
	
	@GetMapping("/veiculos")
	public List<Veiculos>getAllVehicles(){
		return this.veiculoService.getAllVehicles();
	}
	
}
