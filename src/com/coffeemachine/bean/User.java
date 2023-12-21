package com.coffeemachine.bean;

import com.coffeemachine.util.Constants;

public class User {
	private int noOfCup;
	private int noOfSip;
	private String name;
	private int id;
	private static int idCounter;
	private int wallet;
	private String password;
	private boolean isAdmin = Constants.IS_DEFAULT_ADMIN;

	public void setName(String name) {
		this.name = name;
	}

	public void setNoOfCup(int noOfCup) {
		this.noOfCup = noOfCup;
	}

	public void setNoOfSip(int noOfSip) {
		this.noOfSip = noOfSip;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setPassword(String password) {
		if (isAdmin) {
			this.password = password;
		}
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getNoOfCup() {
		return noOfCup;
	}

	public int getNoOfSip() {
		return noOfSip;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public int getWallet() {
		return wallet;
	}

	public String toString() {
		return "Id: " + id + "\nName: " + name + "\nNumber of Cup: " + noOfCup + "\nNumber of Sip: " + noOfSip
				+ "\nWallet: " + wallet;
	}

	public User(String name) {
		++idCounter;
		this.id = idCounter;
		this.name = name;
	}

	public User() {
		++idCounter;
		this.id = idCounter;
	}
}