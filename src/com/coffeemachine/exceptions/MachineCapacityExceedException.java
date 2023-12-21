package com.coffeemachine.exceptions;

import com.coffeemachine.util.Constants;

public class MachineCapacityExceedException extends Exception {
	public MachineCapacityExceedException() {
		System.out.println(Constants.ERROR_MACHINE_CAPACITY_EXCEEDED);
	}
}