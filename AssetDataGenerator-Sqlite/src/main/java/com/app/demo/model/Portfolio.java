package com.app.demo.model;

public class Portfolio {
	String name;
	int customerId;
	int assetId;
	
	public Portfolio(String name, int customerId, int assetId) {
		super();
		this.name = name;
		this.customerId = customerId;
		this.assetId = assetId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	
	
}
