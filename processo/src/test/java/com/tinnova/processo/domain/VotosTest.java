package com.tinnova.processo.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class VotosTest {

	@Test
	public void getPercentualDeVotosValidosTest() {
		final Votos votos = new Votos();
		assertTrue(votos.getPercentualDeVotosValidos() == 0.8d);
	}
	
	@Test
	public void getPercentualDeVotosBrancosTest() {
		final Votos votos = new Votos();
		assertTrue(votos.getPercentualDeVotosBrancos() == 0.15d);
	}
	
	@Test
	public void getPercentualDeVotosNulosTest() {
		final Votos votos = new Votos();
		assertTrue(votos.getPercentualDeVotosNulos() == 0.05d);
	}
	
}
