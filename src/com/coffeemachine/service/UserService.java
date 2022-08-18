package com.coffeemachine.service;

import com.coffeemachine.bean.User;
import com.coffeemachine.exceptions.NoCupException;
import com.coffeemachine.exceptions.NoCupToSpellException;
import com.coffeemachine.exceptions.UserNotFoundException;

public interface UserService {
	public String takeCoffee(int userId, int coffeeId);

	public void takeSip(int id) throws NoCupException, UserNotFoundException;

	public void spillCoffee() throws NoCupToSpellException, UserNotFoundException;

	public User getUserById(int id);

	public void addToWallet() throws UserNotFoundException;

	public String addUser(String name);

	public String addAdmin(String name, String password);

	public void showAllUsers();

	public boolean verifyAdmin(int id, String password);
}