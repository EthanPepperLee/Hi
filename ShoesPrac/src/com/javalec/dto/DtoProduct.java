package com.javalec.dto;

public class DtoProduct {

	int no;
	String brand;
	String model;
	int price;
	
	public DtoProduct() {
		// TODO Auto-generated constructor stub
	}

	

	public DtoProduct(int no, String brand, String model, int price) {
		super();
		this.no = no;
		this.brand = brand;
		this.model = model;
		this.price = price;
	}



	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
