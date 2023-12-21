package com.coffeemachine.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.coffeemachine.bean.User;
import com.coffeemachine.exceptions.NoCupException;
import com.coffeemachine.exceptions.NoCupToSpellException;
import com.coffeemachine.exceptions.UserNotFoundException;
import com.coffeemachine.util.Constants;

public class UserServiceImpl implements UserService {
	Scanner scanner = new Scanner(System.in);
	static Map<Integer, User> userList = new HashMap<Integer, User>();
	AdminService adminService = new AdminServiceImpl();
	BeverageService beverageService = new BeverageServiceImpl();

	public String takeCoffee(int userId, int coffeeId) {
		User user = getUserById(userId);
		user.setNoOfCup(user.getNoOfCup() + 1);
		adminService.reduceCoffeeMachineMilkCapacity(coffeeId);
		user.setWallet(user.getWallet() - beverageService.getBeverageById(coffeeId).getCostOfCoffee());
		return Constants.DISBURSED;
	}

	public void takeSip(int id) throws NoCupException, UserNotFoundException {
		User user = getUserById(id);
		if (user != null) {
			if (user.getNoOfCup() == 0) {
				throw new NoCupException();
			} else {
				if (user.getNoOfSip() == Constants.COFFEE_CUP_MAX_CAPACITY && user.getNoOfCup() >= 1) {
					user.setNoOfCup(user.getNoOfCup() - 1);
					user.setNoOfSip(0);
				} else {
					user.setNoOfSip(user.getNoOfSip() + 1);
				}
				System.out.print("Want to take more sip(Y/N): ");
				String ch = scanner.next();
				if (ch.equalsIgnoreCase("N")) {
					return;
				}
				takeSip(id);
			}
		} else {
			throw new UserNotFoundException();
		}
	}

	public void spillCoffee() throws NoCupToSpellException, UserNotFoundException {
		System.out.println("Enter ID: ");
		int id = scanner.nextInt();
		User user = getUserById(id);
		if (user != null) {
			if (user.getNoOfCup() == 0) {
				throw new NoCupToSpellException();
			} else {
				user.setNoOfCup(user.getNoOfCup() - 1);
				user.setNoOfSip(0);
			}
		} else {
			throw new UserNotFoundException();
		}
	}

	public void showAllUsers() {
		for (int i = 0; i <= userList.size(); i++) {
			System.out.println(userList.get(i));
		}
		/*
		 * for (Entry<Integer, User> entry:userList.entrySet()) {
		 * System.out.println(entry.getValue()); } userList.forEach((k,v) ->
		 * System.out.println("Key = "+ k + ", Value = " + v));
		 */
	}

	public User getUserById(int id) {
		return userList.get(id);
	}

	public String addUser(String name) {
		User user = new User();
		user.setName(name);
		userList.put(user.getId(), user);
		return Constants.USER_ADDED;
	}

	public String addAdmin(String name, String password) {
		User user = new User();
		user.setName(name);
		user.setIsAdmin(true);
		user.setPassword(password);
		userList.put(user.getId(), user);
		return Constants.USER_ADDED;
	}

	public boolean verifyAdmin(int id, String password) {
		boolean value = false;
		User adminUser = getUserById(id);
		if (adminUser != null) {
			if (adminUser.getIsAdmin()) {
				if (password.equals(adminUser.getPassword())) {
					value = true;
				} else {
					System.out.println("Incorrect password");
				}
			} else {
				System.out.println("you are not admin");
			}
		} else {
			System.out.println("User not found");
		}
		return value;
	}

	public void addToWallet() throws UserNotFoundException {
		System.out.println("Enter ID: ");
		int id = scanner.nextInt();
		User user = getUserById(id);
		if (user != null) {
			System.out.print("Enter Amount: ");
			int amount = scanner.nextInt();
			user.setWallet(amount);
		} else {
			throw new UserNotFoundException();
		}
	}

}