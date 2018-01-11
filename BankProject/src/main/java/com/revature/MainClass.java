package com.revature;

import com.revature.dao.BankDao;
import com.revature.model.BankUser;
import com.revature.service.BankService;
import com.revature.service.BankServiceContract;

public class MainClass {
	public static void main(String[] args) {
		BankServiceContract bankService = new BankService();
		bankService.mainMenuUi();
	}
}
