package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.base.Panel1;
import com.javalec.base.Panel3;
import com.javalec.base.Panel4;
import com.javalec.dto.DtoCart;
import com.javalec.util.DBConnect;

public class DaoCart {
	private int price;
	private int amount;
	private int cart_no;
	
	public DaoCart() {
		
	}
	
	public DaoCart(int cart_no) {
		this.cart_no = cart_no;
	}
	
	public DaoCart(int price, int amount) {
		this.price = price;
		this.amount = amount;
	}
	
	public int insertToCartAction() {
		PreparedStatement ps = null;
		int i = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql); 
			String query = "insert into cart (c_guest_id,cd_detail_no,cd_product_no,cart_date,price,amount) ";
			String query1 = "values (?,?,?,now(),?,?)";

			ps = conn_mysql.prepareStatement(query + query1);
			ps.setString(1,Panel1.id);
			ps.setInt(2,Panel4.detail_no);
			ps.setInt(3,Panel3.clickNo);
			ps.setInt(4, price);
			ps.setInt(5, amount);
			
			i = ps.executeUpdate(); 

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return i;
	}
	
	// 모델명, 사이즈, 수량, 브랜드, 가격
	public ArrayList<DtoCart> searchAction() {
		ArrayList<DtoCart> dto = new ArrayList<>();

		String whereStatement = "select cart_no, cd_detail_no, cd_product_no, price, amount from cart";
		String whereStatement2 = " where c_guest_id = '" + Panel1.id + "' and order_date is null and delete_date is null";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql); 
			Statement stmt_mysql = conn_mysql.createStatement(); 
			
			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);

			while (rs.next()) {
				dto.add(new DtoCart(rs.getInt(1) ,rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5)));
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); // 개발 할 때는 이렇게, product를 만들 때는 경고문장을 넣어주면 된다.
		}
		return dto;
	}
	
	public int deleteAction() {
		PreparedStatement ps = null;
		int i = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // package 이름과 class 이름을 정해준 것이다.
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql); 
			String query = "update cart set delete_date = now() where cart_no = ? and delete_date is null";

			ps = conn_mysql.prepareStatement(query);
			ps.setInt(1, cart_no); 
																
			i = ps.executeUpdate(); 

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); // 개발 할 때는 이렇게, product를 만들 때는 경고문장을 넣어주면 된다.
		}
		return i;
	}
	
	
	public int updateAction() {
		PreparedStatement ps = null;
		int i = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			String query = "update cart set order_date = now()";
			String query2 = "where c_guest_id = ? and order_date is null and delete_date is null";

			ps = conn_mysql.prepareStatement(query + query2);
			ps.setString(1,Panel1.id);


			i = ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int insertToPayAction() {
		PreparedStatement ps = null;
		int i = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql); 
			String query = "insert into "+ "pay " + "(p_guest_id,pd_detail_no,pd_product_no,date,time,amount,price) ";
			String query1 = "select c_guest_id,cd_detail_no,cd_product_no,curdate(),curtime(),amount,price "
					+ "from cart "
					+ "where c_guest_id = ? and order_date is null and delete_date is null";

			ps = conn_mysql.prepareStatement(query + query1);
			ps.setString(1,Panel1.id);
			
			i = ps.executeUpdate(); 

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return i;
	}
}
