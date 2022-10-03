package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.javalec.util.DBConnect;

public class DaoLogin {
	private String id;
	private String password;
	private String address;
	private String name;
	private String phone;
	private String email;
	

	public DaoLogin(String id) {
		super();
		this.id = id;
	}
	
	public DaoLogin(String id, String password) {
		this.id = id;
		this.password=password;
	}
	
	public DaoLogin(String id, String password, String address, String name, String phone, String email) {
		super();
		this.id = id;
		this.password = password;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	

	// 1번 메소드
	public String loginAction() {
		String whereStatement = "select count(id and password) from guest where id = '" + id + "' and password = '" + password + "'";
		int i = 0;
		String loginId = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql); 
			Statement stmt_mysql = conn_mysql.createStatement(); 

			ResultSet rs = stmt_mysql.executeQuery(whereStatement);
			
			while(rs.next()) {
				i = rs.getInt(1);
				if(i == 1) {
					loginId = id;
				}
			}
			
			
			conn_mysql.close();
			
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return loginId;
	}
	
	// 2번 메소드
	public boolean overlapCheck() {
		String whereStatement = "select count(id) from guest where id = '" + id + "'";
		boolean result = false;
		int i = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql); 
			Statement stmt_mysql = conn_mysql.createStatement(); 

			ResultSet rs = stmt_mysql.executeQuery(whereStatement);
			
			if(rs.next()) {
				i = rs.getInt(1);
			}
			
			
			if( i == 0) {
				result = true;
			}else {
				result = false;
			}
			

			conn_mysql.close();
			
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
		return result;
	}
	
	// 3번 메소드
	public boolean makeAccount() {
		boolean result = false;
		PreparedStatement ps = null;
		int i = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql); 
			String query = "insert into guest (id,password,address,name,phone,email,init_date) ";
			String query1 = "values (?,?,?,?,?,?,now())";

			ps = conn_mysql.prepareStatement(query + query1);
			ps.setString(1, id);
			ps.setString(2, password);
			ps.setString(3, address);
			ps.setString(4, name);
			ps.setString(5, phone);
			ps.setString(6, email);
			i = ps.executeUpdate(); 

			conn_mysql.close();
			
			if(i == 1) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
		
		return result;
	}
	
	
}
