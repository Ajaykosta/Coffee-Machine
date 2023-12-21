package com.coffeemachine.exceptions;

import com.coffeemachine.util.Constants;

public class MachineFullException extends Exception{
	public MachineFullException() {
		System.out.println(Constants.ERROR_MACHINE_FULL);
	}
}