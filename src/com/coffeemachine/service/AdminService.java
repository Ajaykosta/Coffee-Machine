package com.coffeemachine.service;

import com.coffeemachine.exceptions.MachineCapacityExceedException;
import com.coffeemachine.exceptions.MachineEmptyException;
import com.coffeemachine.exceptions.MachineFullException;

public interface AdminService {
	public void fillCoffeeMachine(float quantity) throws MachineFullException, MachineCapacityExceedException;

	public void emptyCoffeeMachine() throws MachineEmptyException;

	public void getMachineStatus();

	public float getRemainingCoffee();

	public void reduceCoffeeMachineMilkCapacity(int quantity);
}