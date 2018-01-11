package com.revature.model;

public class BankUser {
	private String username;
	private String userPassword;
	private String firstName;
	private String lastName;
	private Double balance;
	
	/**
	 * BankUser No-Args Constructor
	 */
	public BankUser() {
		
	}

	/**
	 * BankUser Constructor
	 * @param username
	 * @param userPassword
	 * @param firstName
	 * @param lastName
	 * @param balance
	 */
	public BankUser(String username, String userPassword, String firstName, String lastName, Double balance) {
		super();
		this.username = username;
		this.userPassword = userPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Bank user " + username+ " is " + firstName +" "+ lastName +" with balance: $"+ balance;
	}
	
	
}
