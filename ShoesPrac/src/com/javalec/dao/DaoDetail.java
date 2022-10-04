package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.javalec.base.Panel3;
import com.javalec.base.Panel4;
import com.javalec.dto.DtoCart;
import com.javalec.dto.DtoDetail;
import com.javalec.util.DBConnect;

public class DaoDetail {
	private int detail_no;
	
	public DaoDetail() {
		
	}
	
	public DaoDetail(int detail_no) {
		this.detail_no = detail_no;
	}
	
	public ArrayList<DtoDetail> searchAction() {
		ArrayList<DtoDetail> dtoDetail = new ArrayList<>();

		String whereStatement = "select detail_no , size, stock from detail";
		String whereStatement2 = " where p_product_no = " + Panel3.clickNo;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql); 
			Statement stmt_mysql = conn_mysql.createStatement(); 
			
			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);

			while (rs.next()) {
				dtoDetail.add(new DtoDetail(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace(); // 개발 할 때는 이렇게, product를 만들 때는 경고문장을 넣어주면 된다.
		}
		return dtoDetail;
	}
	
	// 클릭한 거 가격, 브랜드, 모델명 , 사이즈 보여주기
	public DtoDetail tableClick() {
		DtoDetail dto = null;
		
		String whereStatement = "select detail_no, p_product_no, size, stock from detail "; // 마지막 띄워주기
		String whereStatement2 = "where detail_no = " + detail_no;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql, DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
	
			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);
	
			if (rs.next()) { // true값일때만 가져온다
				int wkDetailNo = rs.getInt(1);
				int wkNo = rs.getInt(2);
				int wkSize = rs.getInt(3);
				int wkStock = rs.getInt(4);
				dto = new DtoDetail(wkDetailNo,wkNo,wkSize,wkStock);
			}
			
			conn_mysql.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	
	public int updateDetailAction() {
		PreparedStatement ps = null;
		DaoCart dao = new DaoCart();
		ArrayList<DtoCart> dto = dao.searchAction();
		
		int i = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			for(DtoCart a : dto) {
				String query = "update detail set stock = stock - ? ";
				String query2 = "where detail_no = ? and stock - ? > 0";
				
				ps = conn_mysql.prepareStatement(query + query2);
				ps.setInt(1, a.getAmount());
				ps.setInt(2, a.getCd_detail_no());
				ps.setInt(3, a.getAmount());

				i = ps.executeUpdate();
				if(i == 0) {
					break;
				}
			}
			
			

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
