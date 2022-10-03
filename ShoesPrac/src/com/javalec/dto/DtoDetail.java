package com.javalec.dto;

public class DtoDetail {

	// Fields
	int detail_no;
	int p_product_no;
	int size;
	int stock;
	
	// Constructor
	public DtoDetail() {
		// TODO Auto-generated constructor stub
	}
	
	public DtoDetail(int p_product_no, int size) {
		super();
		this.p_product_no = p_product_no;
		this.size = size;
	}

	public DtoDetail(int detail_no, int size, int stock) {
		super();
		this.detail_no = detail_no;
		this.size = size;
		this.stock = stock;
	}



	public int getDetail_no() {
		return detail_no;
	}

	public void setDetail_no(int detail_no) {
		this.detail_no = detail_no;
	}

	public int getP_product_no() {
		return p_product_no;
	}

	public void setP_product_no(int p__product_no) {
		this.p_product_no = p__product_no;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
