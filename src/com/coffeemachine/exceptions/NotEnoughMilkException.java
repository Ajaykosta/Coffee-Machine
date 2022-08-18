package com.coffeemachine.exceptions;

import com.coffeemachine.util.Constants;

public class NotEnoughMilkException extends Exception{
	public NotEnoughMilkException() {
		System.out.println(Constants.NOT_ENOUGH_MILK);
	}
}
