package com.coffeemachine.bean;

public class CoffeeMachine {
	private static float currentCapacity;

	public float getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(float currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	@Override
	public String toString() {
		return "Coffee Machine [ Current Capacity = " + currentCapacity + "]";
	}

}