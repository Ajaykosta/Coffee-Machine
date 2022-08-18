package com.coffeemachine.exceptions;

import com.coffeemachine.util.Constants;

public class NoCupToSpellException extends Exception{
	public NoCupToSpellException() {
		System.out.println(Constants.NO_CUP_TO_SPILL);
	}
}
