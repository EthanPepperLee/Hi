package com.javalec.dto;

public class DtoPay {
	int order_no;
	String date;
	String time;
	int amount;
	int price;
	
	// FK
	String p_guest_id;
	int pd_detail_no;
	int pd_product_no;
}
