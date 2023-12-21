package com.coffeemachine.util;

import java.util.Scanner;

import com.coffeemachine.bean.Coffee;
import com.coffeemachine.bean.User;
import com.coffeemachine.exceptions.AdminVerifierException;
import com.coffeemachine.exceptions.CoffeeNotFountException;
import com.coffeemachine.exceptions.InsufficientAmountException;
import com.coffeemachine.exceptions.MachineCapacityExceedException;
import com.coffeemachine.exceptions.MachineEmptyException;
import com.coffeemachine.exceptions.MachineFullException;
import com.coffeemachine.exceptions.NoCupException;
import com.coffeemachine.exceptions.NoCupToSpellException;
import com.coffeemachine.exceptions.NotEnoughMilkException;
import com.coffeemachine.exceptions.UserNotFoundException;
import com.coffeemachine.service.AdminService;
import com.coffeemachine.service.AdminServiceImpl;
import com.coffeemachine.service.BeverageService;
import com.coffeemachine.service.BeverageServiceImpl;
import com.coffeemachine.service.UserService;
import com.coffeemachine.service.UserServiceImpl;

public class MenuController {
	Scanner scanner = new Scanner(System.in);
	AdminService adminService = new AdminServiceImpl();
	UserService userService = new UserServiceImpl();
	BeverageService beverageService = new BeverageServiceImpl();

	public MenuController() {
		System.out.println(userService.addAdmin("Admin", "Password"));
	}

	public void mainMenu() {
		System.out.println("\t\t\tWelcome to Coffee cafe!!!");
		System.out.println("1. Admin");
		System.out.println("2. User");
		System.out.println("0. Exit");
		System.out.print("Enter your choice: ");
		int choice = scanner.nextInt();
		mainMenuControl(choice);
	}

	public void adminMenu() throws AdminVerifierException {
		System.out.print("Enter Id: ");
		int adminId = scanner.nextInt();
		System.out.print("Enter Password: ");
		String adminPassword = scanner.next();
		boolean verified = userService.verifyAdmin(adminId, adminPassword);
		if (!verified) {
			throw new AdminVerifierException();
		}
		while (verified) {
			System.out.println("\t\t\tAdmin");
			System.out.println("1. Fill Coffee Machine");
			System.out.println("2. Empty Coffee Machine");
			System.out.println("3. Add User");
			System.out.println("4. Show All Users");
			System.out.println("5. Add Coffee");
			System.out.println("6. Remove Coffee");
			System.out.println("7. Get Machine Status");
			System.out.println("8. Main Menu");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			adminControl(choice);
		}
	}

	public void userMenu() {
		while (true) {
			System.out.println("\t\t\tUser");
			System.out.println("1. Take Coffee");
			System.out.println("2. Take Sip");
			System.out.println("3. Spill Coffee");
			System.out.println("4. Add To Wallet");
			System.out.println("5. Main Menu");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			int choice = scanner.nextInt();
			userControl(choice);
		}
	}

	public void mainMenuControl(int choice) {
		switch (choice) {
		case 1:
			try {
				adminMenu();
			} catch (AdminVerifierException e) {
				e.getCause();
			}
			break;
		case 2:
			userMenu();
			break;
		case 0:
			System.exit(0);
		default:
			System.out.println(Constants.WRONG_CHOICE);
		}
	}

	public void adminControl(int choice) {
		switch (choice) {
		case 1:
			fillCoffee();
			break;
		case 2:
			try {
				adminService.emptyCoffeeMachine();
			} catch (MachineEmptyException me) {
				me.getCause();
			}
			break;
		case 3:
			addUser();
			break;
		case 4:
			userService.showAllUsers();
			break;
		case 5:
			addBeverage();
			break;
		case 6:
			removeBeverage();
			break;
		case 7:
			adminService.getMachineStatus();
			break;
		case 8:
			mainMenu();
			break;
		case 0:
			System.exit(0);
		default:
			System.out.println(Constants.WRONG_CHOICE);
		}
	}

	public void fillCoffee() {
		System.out.println(
				"Space Remaining: " + (Constants.COFFEE_MACHINE_MAX_CAPACITY - adminService.getRemainingCoffee()));
		System.out.print("Enter capacity in Litres(Max 5): ");
		float capacity = scanner.nextFloat();
		try {
			adminService.fillCoffeeMachine(capacity);
		} catch (MachineFullException mfe) {
			mfe.getCause();
		} catch (MachineCapacityExceedException mce) {
			mce.getCause();
		}
	}

	public void addUser() {
		Scanner userScanner = new Scanner(System.in);
		System.out.print("Enter Name: ");
		String name = userScanner.nextLine();
		System.out.println(userService.addUser(name));
	}

	private void addBeverage() {
		System.out.println("Enter Coffee Name: ");
		String name = scanner.next();
		System.out.println("Enter Milk Required in mL: ");
		int milkRequired = scanner.nextInt();
		System.out.println("Enter Cost of Coffee: ");
		int cost = scanner.nextInt();
		System.out.println(beverageService.addBeverage(name, milkRequired, cost));
	}

	public void removeBeverage() {
		System.out.print("Enter Beverage Id: ");
		int id = scanner.nextInt();
		try {
			beverageService.removeBeverage(id);
		} catch (CoffeeNotFountException cnf) {
			cnf.getCause();
		}
	}

	public void userControl(int choice) {
		switch (choice) {
		case 1:
			try {
				takeCoffee();
			} catch (MachineEmptyException me) {
				me.getCause();
			} catch (NotEnoughMilkException neme) {
				neme.getCause();
			} catch (InsufficientAmountException iae) {
				iae.getCause();
			} catch (CoffeeNotFountException cnfe) {
				cnfe.getCause();
			} catch (UserNotFoundException unfe) {
				unfe.getCause();
			}
			break;
		case 2:
			takeSip();
			break;
		case 3:
			try {
				userService.spillCoffee();
			} catch (NoCupToSpellException nctse) {
				nctse.getCause();
			} catch (UserNotFoundException unfe) {
				unfe.getCause();
			}
			break;
		case 4:
			try {
				userService.addToWallet();
			} catch (UserNotFoundException unfe) {
				unfe.getCause();
			}
			break;
		case 5:
			mainMenu();
		case 0:
			System.exit(0);
		default:
			System.out.println(Constants.WRONG_CHOICE);
		}
	}

	public void takeCoffee() throws MachineEmptyException, NotEnoughMilkException, InsufficientAmountException,
			CoffeeNotFountException, UserNotFoundException {
		if (adminService.getRemainingCoffee() == 0) {
			throw new MachineEmptyException();
		} else {
			System.out.println("Enter User id: ");
			int id = scanner.nextInt();
			User user = userService.getUserById(id);
			if (user != null) {
				for (Coffee coffee : beverageService.getAllBeverages()) {
					System.out.println(coffee);
				}
				System.out.print("Please enter coffee Id: ");
				int coffeeId = scanner.nextInt();
				Coffee coffee = beverageService.getBeverageById(coffeeId);
				if (coffee != null) {
					if ((coffee.getMilkRequired() / Constants.IN_ML) > adminService.getRemainingCoffee()) {
						throw new NotEnoughMilkException();
					} else if (coffee.getCostOfCoffee() > user.getWallet()) {
						throw new InsufficientAmountException();
					} else {
						System.out.println(userService.takeCoffee(id, coffeeId));
					}
				} else {
					throw new CoffeeNotFountException();
				}
			} else {
				throw new UserNotFoundException();
			}
		}
	}

	public void takeSip() {
		System.out.println("Enter ID: ");
		int id = scanner.nextInt();
		try {
			userService.takeSip(id);
		} catch (NoCupException nce) {
			System.out.println(Constants.NO_CUP);
		} catch (UserNotFoundException unfe) {
			System.out.println(Constants.USER_NOT_FOUND);
		}
	}
}