package com.coffeemachine.service;

import java.util.List;
import java.util.Scanner;

import com.coffeemachine.bean.Coffee;
import com.coffeemachine.bean.CoffeeMachine;
import com.coffeemachine.exceptions.MachineCapacityExceedException;
import com.coffeemachine.exceptions.MachineEmptyException;
import com.coffeemachine.exceptions.MachineFullException;
import com.coffeemachine.util.Constants;

public class AdminServiceImpl implements AdminService {
	Scanner scanner = new Scanner(System.in);
	CoffeeMachine coffeeMachine = new CoffeeMachine();
	BeverageService beverageService = new BeverageServiceImpl();
	Coffee coffee = new Coffee();

	public void fillCoffeeMachine(float quantity) throws MachineFullException, MachineCapacityExceedException {
		if (coffeeMachine.getCurrentCapacity() == Constants.COFFEE_MACHINE_MAX_CAPACITY) {
			throw new MachineFullException();
		} else {
			if ((coffeeMachine.getCurrentCapacity() + quantity) > Constants.COFFEE_MACHINE_MAX_CAPACITY) {
				throw new MachineCapacityExceedException();
			} else {
				coffeeMachine.setCurrentCapacity(coffeeMachine.getCurrentCapacity() + quantity);
				System.out.println(Constants.MILK_ADDED);
			}
		}
	}

	public void emptyCoffeeMachine() throws MachineEmptyException{
		if (coffeeMachine.getCurrentCapacity() == 0) {
			throw new MachineEmptyException();
		} else {
			coffeeMachine.setCurrentCapacity(0);
			System.out.println(Constants.MACHINE_EMPTY);
		}
	}

	public void getMachineStatus() {
		System.out.println("Number of Milk Left: " + coffeeMachine.getCurrentCapacity() + " Litres");
		System.out.println("Types of Coffee available: ");
		System.out.println("\tName" + "\t\tCost");
		List<Coffee> coffeeList = beverageService.getAllBeverages();
		for (Coffee coffee : coffeeList) {
			System.out.println("\t"+coffee.getCoffeeName() + "\t" + coffee.getCostOfCoffee());
		}
	}

	@Override
	public float getRemainingCoffee() {
		return coffeeMachine.getCurrentCapacity();
	}

	public void reduceCoffeeMachineMilkCapacity(int coffeeId) {
		Coffee coffee = beverageService.getBeverageById(coffeeId);
		try {
			coffeeMachine.setCurrentCapacity(coffeeMachine.getCurrentCapacity() - (coffee.getMilkRequired() / Constants.IN_ML));
		}catch(ArithmeticException exception) {
			System.out.println("getCause: ");
			System.out.println(exception.getCause());
		}
	}
}