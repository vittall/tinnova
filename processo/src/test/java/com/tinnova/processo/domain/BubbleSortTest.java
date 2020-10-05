package com.tinnova.processo.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BubbleSortTest {

	@Test
	public void bubbleSortTest() {
		int[] arrayDeInt = {9,6,4,5,7,8,2,3,1};
		int[] arrayOrdenado = {1,2,3,4,5,6,7,8,9};
		BubbleSort.bubbleSort(arrayDeInt);
		int i;
		for (i = 0; i < arrayDeInt.length; i++) {
			assertEquals(arrayOrdenado[i], arrayDeInt[i]);
		}
		assertEquals(i, arrayDeInt.length);
	}
	
}
