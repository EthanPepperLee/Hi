package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.DtoProduct;
import com.javalec.util.DBConnect;

public class DaoProduct {

	int no;
	String brand;
	String model;
	int price;
	
	String conname;
	String condata;
	
	public DaoProduct() {
		// TODO Auto-generated constructor stub
	}
	
	public DaoProduct(int no, String brand, String model, int price) {
		super();
		this.no = no;
		this.brand = brand;
		this.model = model;
		this.price = price;
	}
	
	public DaoProduct(String conname, String condata) {
		super();
		this.conname = conname;
		this.condata = condata;
	}
	
	public DaoProduct(int no) {
		super();
		this.no = no;
	}
	
	public ArrayList<DtoProduct> selectList(){
		
		ArrayList<DtoProduct> dtoList = new ArrayList<DtoProduct>();

		String whereStatement = "select no, brand, model, price from product";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql, DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
	
			ResultSet rs = stmt_mysql.executeQuery(whereStatement);
	
			while (rs.next()) { // true값일때만 가져온다
				
				int wkNo = rs.getInt(1); 
				String wkBrand = rs.getString(2);
				String wkModel = rs.getString(3);
				int wkPrice = rs.getInt(4);
			
				DtoProduct dto = new DtoProduct(wkNo, wkBrand, wkModel, wkPrice);
				dtoList.add(dto);
			}
	
			conn_mysql.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return dtoList;

	}

	// 조건 검색 결과를 Table로
	public ArrayList<DtoProduct> conditionList(){
		
		ArrayList<DtoProduct> dtoList = new ArrayList<DtoProduct>();
		
		String whereStatement = "select no, brand, model, price from product ";
		String whereStatement2 = "where " + conname + " like '%" + condata + "%'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql, DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
	
			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);
	
			while (rs.next()) { // true값일때만 가져온다
				
				int wkNo = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkModel = rs.getString(3);
				int wkPrice = rs.getInt(4);
			
				DtoProduct dto = new DtoProduct(wkNo, wkBrand, wkModel, wkPrice);
				dtoList.add(dto);
			}
	
			conn_mysql.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;

	}

	public DtoProduct tableClick() {
		
		DtoProduct dto = null;
		
		String whereStatement = "select no, brand, model, price from product "; // 마지막 띄워주기
		String whereStatement2 = "where no = " + no;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql, DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
	
			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);
	
			if (rs.next()) { // true값일때만 가져온다
				
				int wkNo = rs.getInt(1);
				String wkBrand = rs.getString(2);
				String wkModel = rs.getString(3);
				int wkPrice = rs.getInt(4);
				
				dto = new DtoProduct(wkNo, wkBrand, wkModel, wkPrice);

			}
	
			conn_mysql.close();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
		
	}

}
