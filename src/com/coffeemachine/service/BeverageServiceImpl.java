package com.coffeemachine.service;

import java.util.ArrayList;
import java.util.List;

import com.coffeemachine.bean.Coffee;
import com.coffeemachine.exceptions.CoffeeNotFountException;
import com.coffeemachine.util.Constants;

public class BeverageServiceImpl implements BeverageService {
	static List<Coffee> coffeeList = new ArrayList<Coffee>();

	@Override
	public String addBeverage(String name, int milkRequired, int costOfCoffee) {
		Coffee coffee = new Coffee(name, milkRequired, costOfCoffee);
		coffeeList.add(coffee);
		return Constants.COFFEE_ADDED;
	}

	@Override
	public void removeBeverage(int id) throws CoffeeNotFountException {
		Coffee coffee = getBeverageById(id);
		if (coffee != null) {
			coffeeList.remove(coffee);
		} else {
			throw new CoffeeNotFountException();
		}
		System.out.println(Constants.COFFEE_REMOVED);
	}

	@Override
	public String setCostPerCup(int beverageId, int costOfCoffee) {
		Coffee coffee = getBeverageById(beverageId);
		coffee.setCostOfCoffee(costOfCoffee);
		return Constants.COFFEE_PRICE_UPDATE;
	}

	@Override
	public Coffee getBeverageById(int id) {
		Coffee tempCoffee = null;
		for (Coffee coffee : coffeeList) {
			if (coffee.getId() == id) {
				tempCoffee = coffee;
			}
		}
		return tempCoffee;
	}

	@Override
	public List<Coffee> getAllBeverages() {
		return coffeeList;
	}

}
