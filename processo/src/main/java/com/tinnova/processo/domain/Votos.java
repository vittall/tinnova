package com.tinnova.processo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Votos {

	Double totalDeEleitores;
	Double votosValidos;
	Double votosBrancos;
	Double votosNulos;
	
	public Votos() {
		this.setTotalDeEleitores(Double.parseDouble("1000"));
		this.setVotosValidos(Double.valueOf(800));
		this.setVotosBrancos(150d);
		this.setVotosNulos(Double.valueOf("50"));
	}
	
	public Double getPercentualDeVotosValidos() {
		return this.getVotosValidos() / this.getTotalDeEleitores();
	}
	
	public Double getPercentualDeVotosBrancos() {
		return this.getVotosBrancos() / this.getTotalDeEleitores();
	}
	
	public Double getPercentualDeVotosNulos() {
		return this.getVotosNulos() / this.getTotalDeEleitores();
	}
	
}
