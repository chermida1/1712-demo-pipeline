package com.revature.service;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.BankDao;
import com.revature.dao.BankDaoContract;
import com.revature.model.BankUser;

public class BankService implements BankServiceContract{
	private BankDaoContract bankDaoContract = new BankDao();
	@Override
	public void addUser(BankUser u) {
		List<BankUser> allUsers = bankDaoContract.getAllUsers();
		System.out.println(u.getUsername());
		for(BankUser user: allUsers) {
			String uname = user.getUsername();
			if(uname.equals(u.getUsername())){
				//throw exception here
				System.out.println("USERNAME IS ALREADY USED");
				createUserUi();
			}
		}
		bankDaoContract.addUser(u);
		mainMenuUi();
	}

	@Override
	public BankUser getUser(BankUser u) {
		// check to see if user exist
		List<BankUser> allUsers = bankDaoContract.getAllUsers();
		for(BankUser user: allUsers) {
			if(user.getUsername().equals(u.getUsername())) {
				return user;
			}
			
		}
		System.out.println("USERS DOES NOT EXIST");
		//mainMenuUi();
		return null;
	}

	@Override
	public List<BankUser> getAllUsers() {
		return bankDaoContract.getAllUsers();
	}

	@Override
	public void withdraw(BankUser u, Double amount) {
		BankUser user = bankDaoContract.getUser(u);
		Double balance = user.getBalance();
		if ((balance - amount) >= 0) {
			Double newAmount = balance - amount;
			bankDaoContract.updateBalance(user, newAmount);
			homeUi(bankDaoContract.getUser(user));
		}
		else {
			System.out.println("CAN NOT WITHDRAW THAT AMOUNT");
			homeUi(user);
		}
		
	}

	@Override
	public void deposit(BankUser u, Double amount) {
		BankUser user = bankDaoContract.getUser(u);
		Double balance = user.getBalance();
		Double newAmount = balance + amount;
		bankDaoContract.updateBalance(user, newAmount);
		homeUi(bankDaoContract.getUser(user));
	}

	@Override
	public void deleteUser(BankUser u, Double amount) {
		// check to see if user exist
		//then delete 
		List<BankUser> allUsers = bankDaoContract.getAllUsers();
		for(BankUser user: allUsers) {
			if(user.getUsername() == u.getUsername()) {
				bankDaoContract.deleteUser(user);
			}
			else {
				//throw exception
			}
		}
		
	}

	@Override
	public void mainMenuUi() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("WELCOME TO BANK APP");
		System.out.println("--------------------");
		System.out.println("Enter 1: Login");
		System.out.println("Enter 2: Create Account");
		int userInput = scanner.nextInt();
		if(userInput == 1) {
			loginUi();
		}else if (userInput == 2) {
			createUserUi();
		}
		else
		{
			System.out.println("ENTER VALID INPUT");
			mainMenuUi();
		}
		
		
	}

	@Override
	public void loginUi() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("LOGIN ");
		System.out.println("------------------");
		System.out.println("Enter Username:");
		String username = scanner.next();
		System.out.println("Enter Password:");
		String password = scanner.next();
		BankUser userLi = new BankUser();
		userLi.setUsername(username);
		userLi.setUserPassword(password);
		//check if user exists and if it does login to homeUi with user object
		BankUser user = getUser(userLi);
		if(user.getUsername().equals(null))
		{
			loginUi();
		}
		else {
			homeUi(user);
		}
		
	
		
	}

	@Override
	public void createUserUi() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("CREATE USER ");
		System.out.println("------------------");
		System.out.println("Enter First Name:");
		String firstName = scanner.next();
		System.out.println("Enter Last Name:");
		String lastName = scanner.next();
		System.out.println("Enter Username:");
		String username = scanner.next();
		System.out.println("Enter Password:");
		String password = scanner.next();
		BankUser user = new BankUser();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setUserPassword(password);
		user.setBalance(0.00);
		System.out.println(user);
		addUser(user);
	}

	@Override
	public void homeUi(BankUser u) {
		Scanner scanner = new Scanner(System.in);
		//account info
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Welcome " + u.getFirstName() + " " + u.getLastName() + " Username: " + u.getUsername());
		System.out.println("Your Balance: " + u.getBalance());
		System.out.println("Enter 1: Deposit");
		System.out.println("Enter 2: Withdraw");
		int userInput = scanner.nextInt();
		if(userInput == 1) {
			depositUi(u);
		}else if (userInput == 2) {
			withdrawUi(u);
		}
		else
		{
			System.out.println("ENTER VALID INPUT");
			mainMenuUi();
		}
		
		
	}

	@Override
	public void depositUi(BankUser u) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Deposit Amount: ");
		Double amount = scanner.nextDouble();
		deposit(u,amount);
		
	}

	@Override
	public void withdrawUi(BankUser u) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Withdraw Amount: ");
		Double amount = scanner.nextDouble();
		withdraw(u,amount);
		
	}

}
