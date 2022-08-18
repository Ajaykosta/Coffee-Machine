package com.coffeemachine.service;

import java.util.List;

import com.coffeemachine.bean.Coffee;
import com.coffeemachine.exceptions.CoffeeNotFountException;

public interface BeverageService {
	public String addBeverage(String name, int milkRequired, int costOfCoffee);

	public void removeBeverage(int id) throws CoffeeNotFountException;

	public String setCostPerCup(int beverageId, int costOfCoffee);

	public Coffee getBeverageById(int id);

	public List<Coffee> getAllBeverages();
}
