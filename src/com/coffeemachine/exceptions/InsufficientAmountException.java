package com.coffeemachine.exceptions;

import com.coffeemachine.util.Constants;

public class InsufficientAmountException extends Exception{
	public InsufficientAmountException() {
		System.out.println(Constants.INSUFFICIENT_AMOUNT);
	}
}
