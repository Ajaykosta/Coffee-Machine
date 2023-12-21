package com.coffeemachine.exceptions;

import com.coffeemachine.util.Constants;

public class NoCupException extends Exception{
	public NoCupException() {
		System.out.println(Constants.NO_CUP);
	}
}
