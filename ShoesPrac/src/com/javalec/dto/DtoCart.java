package com.javalec.dto;

public class DtoCart {
	int cart_no;
	String cart_date;
	String order_date;
	String delete_date;
	int price;
	int amount;
	
	//FK
	String c_guest_id;
	int cd_detail_no;
	int cd_product_no;
	
	
	public DtoCart() {
		
	}
	
	public DtoCart( int cart_no, int cd_detail_no, int cd_product_no,int price, int amount) {
		super();
		this.cart_no = cart_no;
		this.price = price;
		this.amount = amount;
		this.cd_detail_no = cd_detail_no;
		this.cd_product_no = cd_product_no;
	}

	public int getCart_no() {
		return cart_no;
	}

	public void setCart_no(int cart_no) {
		this.cart_no = cart_no;
	}

	public String getCart_date() {
		return cart_date;
	}

	public void setCart_date(String cart_date) {
		this.cart_date = cart_date;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getDelete_date() {
		return delete_date;
	}

	public void setDelete_date(String delete_date) {
		this.delete_date = delete_date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getC_guest_id() {
		return c_guest_id;
	}

	public void setC_guest_id(String c_guest_id) {
		this.c_guest_id = c_guest_id;
	}

	public int getCd_detail_no() {
		return cd_detail_no;
	}

	public void setCd_detail_no(int cd_detail_no) {
		this.cd_detail_no = cd_detail_no;
	}

	public int getCd_product_no() {
		return cd_product_no;
	}

	public void setCd_product_no(int cd_product_no) {
		this.cd_product_no = cd_product_no;
	}
	
	
}
