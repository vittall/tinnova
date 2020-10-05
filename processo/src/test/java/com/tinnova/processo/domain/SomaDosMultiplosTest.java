package com.tinnova.processo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SomaDosMultiplosTest {

	@Test
	public void getSomaDosMultiplosTest(){
		final SomaDosMultiplos somaDosMultiplos = new SomaDosMultiplos();
		assertEquals(33, somaDosMultiplos.getSomaDosMultiplos(10));
	}
	
}
