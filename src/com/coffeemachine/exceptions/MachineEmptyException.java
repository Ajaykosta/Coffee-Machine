package com.coffeemachine.exceptions;

import com.coffeemachine.util.Constants;

public class MachineEmptyException extends Exception{
	public MachineEmptyException() {
		System.out.println(Constants.ERROR_MACHINE_EMPTY);
	}
}