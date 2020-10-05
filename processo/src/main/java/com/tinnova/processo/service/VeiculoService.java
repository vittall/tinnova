package com.tinnova.processo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinnova.processo.domain.Marca;
import com.tinnova.processo.domain.Veiculo;
import com.tinnova.processo.repository.VeiculoRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VeiculoService {

	final VeiculoRepository veiculoRepository;
	private static final ObjectMapper mapper = new ObjectMapper();

	public List<Veiculo> getAllVehicles() {
		return veiculoRepository.findAll();
	}

	public Veiculo findById(Long id) {
		return veiculoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = '" + id.toString() + "' was not found"));
	}

	@Transactional
	public void saveVehicle(String vehicleJson) throws JsonProcessingException {
		final Veiculo veiculo = mapper.readValue(vehicleJson, Veiculo.class);
		this.veiculoRepository.save(veiculo);
	}

	@Transactional
	public void deleteVehicle(Long id) {
		this.veiculoRepository.deleteById(id);
	}

	@Transactional
	public void updateVehicle(Long id, String vehicleJson) throws JsonProcessingException {
		final Veiculo veiculoBancoDeDados = this.findById(id);
		final Veiculo veiculoModificado = mapper.readValue(vehicleJson, Veiculo.class);
		if (veiculoBancoDeDados.getId() != veiculoModificado.getId()) {
			throw new IllegalArgumentException("Id do banco não é a mesma Id do objeto modificado");
		} else {
			veiculoBancoDeDados.updateVehicle(veiculoModificado);
			this.veiculoRepository.save(veiculoBancoDeDados);
		}
	}

	@Transactional
	public void patchVehicle(Long id, String vehicleJson) throws JsonProcessingException {
		final Veiculo veiculoBancoDeDados = this.findById(id);
		final Veiculo veiculoModificado = mapper.readValue(vehicleJson, Veiculo.class);
		if (veiculoBancoDeDados.getId() != veiculoModificado.getId()) {
			throw new IllegalArgumentException("Id do banco não é a mesma Id do objeto modificado");
		} else {
			veiculoBancoDeDados.patchVehicle(veiculoModificado);
			this.veiculoRepository.save(veiculoBancoDeDados);
		}
	}

	public Map<?, ?> getAllVehiclesUsingParameter(String parameter) {
		Map<?, ?> map = new ConcurrentHashMap<>();
		switch (parameter) {
			case "vendidos":
				map = this.getSoldQuantity();
				break;
			case "decada":
				map = this.getDecadeDistribution();
				break;
			case "fabricante":
				map = this.getBrandDistribution();
				break;
			case "week":
				map = this.getSoldLastWeek();
				break;
			default:
				throw new IllegalArgumentException("Comando não suportado");
		}
		return map;

	}

	private Map<Marca, Long> getBrandDistribution() {
		final Map<Marca, Long> map = new ConcurrentHashMap<>();
		for (final Entry<Marca, List<Veiculo>> entry : this.getAllVehicles().stream().collect(Collectors.groupingBy(Veiculo::getMarca)).entrySet()) {
			map.put(entry.getKey(), Long.valueOf(entry.getValue().size()));
		}
		return map;
	}

	private Map<String, Long> getSoldLastWeek() {
		final Map<String, Long> map = new ConcurrentHashMap<>();
		final LocalDate pastWeekDate = LocalDate.now().plusDays(-7);
		final Long vendidoNaUltimaSemana = this.getAllVehicles().stream().filter(veiculo -> veiculo.isVendido() && veiculo.getCreationLocalDate().compareTo(pastWeekDate) > 0).count();
		map.put("vendidosNaUltimaSemana", vendidoNaUltimaSemana);
		return map;
	}

	private Map<String, Long> getDecadeDistribution() {
		final Map<String, Long> map = new ConcurrentHashMap<>();
		for (final Entry<String, List<Veiculo>> entry : this.getAllVehicles().stream().collect(Collectors.groupingBy(Veiculo::getDecada)).entrySet()) {
			map.put(entry.getKey(), Long.valueOf(entry.getValue().size()));
		}
		return map;
	}

	private Map<String, Long> getSoldQuantity() {
		final Map<String, Long> map = new ConcurrentHashMap<>();
		for (final Entry<Boolean, List<Veiculo>> entry : this.getAllVehicles().stream().collect(Collectors.groupingBy(Veiculo::isVendido)).entrySet()) {
			if (entry.getKey()) {
				map.put("vendido", Long.valueOf(entry.getValue().size()));
			} else {
				map.put("naoVendido", Long.valueOf(entry.getValue().size()));
			}
		}
		return map;
	}

}
