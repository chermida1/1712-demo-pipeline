package com.revature.dao;

import java.util.List;

import com.revature.model.BankUser;

public interface BankDaoContract {
	/*CREATE
	 * 	-add user
	 */
	public void addUser(BankUser u);
	/*READ
	 * 	-get user
	 */
	public BankUser getUser(BankUser u);
	public List<BankUser> getAllUsers();
	/*UPDATE
	 * 	-deposit
	 * 	-withdraw
	 */
	public void updateBalance(BankUser u, Double amount);
	/*DELETE
	 * 	-delete user
	 */
	public void deleteUser(BankUser u);
	
	
}
