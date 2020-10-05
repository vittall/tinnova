package com.tinnova.processo.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Data;

@Data
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String veiculo;

	@Enumerated(EnumType.STRING)
	private Marca marca;

	private Integer ano;

	private String descricao;

	private boolean vendido;

	private Date created;

	private Date updated;

	public LocalDate getCreationLocalDate() {
		return LocalDateTime.ofInstant(this.getCreated().toInstant(), ZoneId.systemDefault()).toLocalDate();
	}

	public String getDecada() {
		return this.getAno().toString().charAt(2) + "0";
	}

	@PrePersist
	private void prePersist() {
		this.setCreated(new Date());
	}

	@PreUpdate
	private void preUpdate() {
		this.setUpdated(new Date());
	}

	public void patchVehicle(Veiculo veiculo) {
		if (veiculo.getVeiculo() != null) {
			this.setVeiculo(veiculo.getVeiculo());
		}

		if (veiculo.getMarca() != null) {
			this.setMarca(veiculo.getMarca());
		}

		if (veiculo.getAno() != null) {
			this.setAno(veiculo.getAno());
		}

		if (veiculo.getDescricao() != null) {
			this.setDescricao(veiculo.getDescricao());
		}

		if (veiculo.getCreated() != null) {
			this.setCreated(veiculo.getCreated());
		}

		this.setVeiculo(veiculo.getVeiculo());
	}

	public void updateVehicle(Veiculo veiculo) {
		this.setVeiculo(veiculo.getVeiculo());
		this.setMarca(veiculo.getMarca());
		this.setAno(veiculo.getAno());
		this.setDescricao(veiculo.getDescricao());
		this.setVendido(veiculo.isVendido());
		this.setCreated(veiculo.getCreated());
	}
}