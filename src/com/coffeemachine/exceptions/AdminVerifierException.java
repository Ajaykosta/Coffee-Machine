package com.coffeemachine.exceptions;

import com.coffeemachine.util.Constants;

public class AdminVerifierException extends Exception {
	public AdminVerifierException() {
		System.out.println(Constants.IS_NOT_ADMIN);
	}
}