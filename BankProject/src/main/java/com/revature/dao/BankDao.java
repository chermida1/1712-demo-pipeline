package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.BankUser;


public class BankDao implements BankDaoContract {
	private static String url = "jdbc:oracle:thin:@usfdbjava.c7el7cmpe2mh.us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "bankdb";
	private static String password = "pass1234";
	
	@Override
	public void addUser(BankUser u) {
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "{call insert_user_null_id(?,?,?,?,?)}"; //works with the {} but nothing gets returned
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, u.getFirstName());
			cs.setString(2, u.getLastName());
			cs.setString(3, u.getUsername());
			cs.setString(4, u.getUserPassword());
			cs.setDouble(5, u.getBalance());
			cs.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	/*
	 * PreparedStatement
	 * @see com.revature.dao.BankDaoContract#getUser(com.revature.model.BankUser)
	 */
	@Override
	public BankUser getUser(BankUser u) {
		BankUser user = new BankUser();
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "SELECT * FROM BANK WHERE USERNAME = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user.setFirstName(rs.getString(2));
				user.setLastName(rs.getString(3));
				user.setUsername(rs.getString(4));
				user.setUserPassword(rs.getString(5));
				user.setBalance(rs.getDouble(6));;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/*
	 * Callablestatement
	 * @see com.revature.dao.BankDaoContract#deleteUser(com.revature.model.BankUser, java.lang.Double)
	 */
	@Override
	public void deleteUser(BankUser u) {
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "{call delete_user(?)}"; //works with the {} but nothing gets returned
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, u.getUsername());
			cs.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<BankUser> getAllUsers() {
		BankUser user = new BankUser();
		List<BankUser> userList = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "SELECT * FROM BANK";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				userList.add(new BankUser(rs.getString(4),rs.getString(5),rs.getString(2),rs.getString(3),rs.getDouble(6)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	@Override
	public void updateBalance(BankUser u, Double newAmount) {
		try(Connection conn = DriverManager.getConnection(url, username, password))
		{
			String sql = "UPDATE BANK SET balance = ? WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, newAmount);
			ps.setString(2, u.getUsername());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
