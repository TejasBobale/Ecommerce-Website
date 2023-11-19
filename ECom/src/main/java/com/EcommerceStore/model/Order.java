package com.EcommerceStore.model;

public class Order extends Product{
	private int orderID;
	private int uid;
	private int quantity;
	private String date;
	
	public Order() {}

	public Order(int uid, int quantity, String date) {
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
	}

	public Order(int orderID, int uid, int quantity, String date) {
		this.orderID = orderID;
		this.uid = uid;
		this.quantity = quantity;
		this.date = date;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", uid=" + uid + ", quantity=" + quantity + ", date=" + date + "]";
	}
	
	
	
	
}
