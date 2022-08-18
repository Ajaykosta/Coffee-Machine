package com.coffeemachine.exceptions;

import com.coffeemachine.util.Constants;

public class CoffeeNotFountException extends Exception {
	public CoffeeNotFountException() {
		System.out.println(Constants.COFFEE_NOT_FOUND);
	}
}
