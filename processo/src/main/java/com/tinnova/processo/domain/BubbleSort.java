package com.tinnova.processo.domain;

public class BubbleSort {

	void bubbleSort(int arrayDeInt[]) {  
        for (int i = 0; i < arrayDeInt.length-1; i++) { 
            for (int j = 0; j < arrayDeInt.length-i-1; j++) { 
                if (arrayDeInt[j] > arrayDeInt[j+1]) { 
                    int temporarioParaSwap = arrayDeInt[j]; 
                    arrayDeInt[j] = arrayDeInt[j+1]; 
                    arrayDeInt[j+1] = temporarioParaSwap; 
                }
            }
        }
    } 
	
}
