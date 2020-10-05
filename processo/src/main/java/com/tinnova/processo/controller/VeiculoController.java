package com.tinnova.processo.controller;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.tinnova.processo.domain.Veiculo;
import com.tinnova.processo.service.VeiculoService;
import lombok.AllArgsConstructor;

@RestController("/")
@AllArgsConstructor
public class VeiculoController {

	private VeiculoService veiculoService;

	@GetMapping("/veiculos")
	public List<Veiculo> getAllVehicles() {
		return this.veiculoService.getAllVehicles();
	}

	@GetMapping("/veiculos/find")
	public Map<?, ?> getAllVehicles(@PathVariable("q") String parameter) {
		return this.veiculoService.getAllVehiclesUsingParameter(parameter);
	}

	@GetMapping("/veiculos/{id}")
	public ResponseEntity<Veiculo> getVehicle(@PathVariable Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(veiculoService.findById(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@PostMapping("/veiculos")
	public ResponseEntity<?> addVehicle(@RequestBody final String vehicleJson) {
		try {
			veiculoService.saveVehicle(vehicleJson);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/veiculos/{id}")
	public ResponseEntity<?> deleteAircraft(@PathVariable final Long id) {
		veiculoService.deleteVehicle(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/veiculos/{id}")
	public ResponseEntity<?> updateAircraft(@PathVariable final Long id, @RequestBody final String vehicleJson) {
		try {
			veiculoService.updateVehicle(id, vehicleJson);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (JsonProcessingException | IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
	}

	@PatchMapping("/veiculos/{id}")
	public ResponseEntity<?> patchAircraft(@PathVariable final Long id, @RequestBody final String vehicleJson) {
		try {
			veiculoService.patchVehicle(id, vehicleJson);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (JsonProcessingException | IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
	}

}
