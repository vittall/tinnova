package com.tinnova.processo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FatorialTest {

	@Test
	public void getFatorialDeZeroTest() {
		final Fatorial fatorial = new Fatorial();
		assertEquals(BigDecimal.ONE, fatorial.getFatorial(BigDecimal.ZERO));
	}
	
	@Test
	public void getFatorialDeDezTest() {
		final Fatorial fatorial = new Fatorial();
		assertEquals(BigDecimal.valueOf(3628800l), fatorial.getFatorial(BigDecimal.TEN));
	}
	
	@Test()
	public void getFatorialDeNúmeroNegativoTest() {
		final Fatorial fatorial = new Fatorial();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
		    fatorial.getFatorial(new BigDecimal("-1"));
		  });
	}
	
}
