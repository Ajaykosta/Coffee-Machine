package com.coffeemachine.bean;

public class Coffee {
	private String coffeeName;
	private float milkRequired;
	private int costOfCoffee;
	private int id;

	private static int idCount;

	public Coffee() {
		super();
		this.id = idCount;
	}

	public Coffee(String coffeeName, int milkRequired, int costOfCoffee) {
		super();
		++idCount;
		this.coffeeName = coffeeName;
		this.milkRequired = milkRequired;
		this.costOfCoffee = costOfCoffee;
		this.id = idCount;
	}

	public String getCoffeeName() {
		return coffeeName;
	}

	public void setCoffeeName(String coffeeName) {
		this.coffeeName = coffeeName;
	}

	public float getMilkRequired() {
		return milkRequired;
	}

	public void setMilkRequired(float milkRequired) {
		this.milkRequired = milkRequired;
	}

	public int getCostOfCoffee() {
		return costOfCoffee;
	}

	public void setCostOfCoffee(int costOfCoffee) {
		this.costOfCoffee = costOfCoffee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Coffee Id: "+id+"\nCoffee Name: "+coffeeName+"\nMilk Required: "+milkRequired+"\nCost: "+costOfCoffee+"\n";
	}
}