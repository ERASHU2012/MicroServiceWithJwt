package com.kinexcs.demo.common.dto;


public class Order {

	private String purchaseDate;

	private int qty;

	private float revenueGenerated;

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public float getRevenueGenerated() {
		return revenueGenerated;
	}

	public void setRevenueGenerated(float revenueGenerated) {
		this.revenueGenerated = revenueGenerated;
	}
	
	

}
