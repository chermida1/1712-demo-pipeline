package com.revature.service;

import java.util.List;

import com.revature.model.BankUser;

public interface BankServiceContract {
	//add method sigs here that are sim
	public void addUser(BankUser u);
	
	public BankUser getUser(BankUser u);
	
	public List<BankUser> getAllUsers();
	
	public void withdraw(BankUser u, Double amount);
	
	public void deposit(BankUser u, Double amount);
	
	public void deleteUser(BankUser u, Double amount);
	
	public void mainMenuUi();
	
	public void loginUi();
	
	public void createUserUi();
	
	public void homeUi(BankUser u);
	
	public void depositUi(BankUser u);
	
	public void withdrawUi(BankUser u);
}
