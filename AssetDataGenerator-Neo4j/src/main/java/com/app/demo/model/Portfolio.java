package com.app.demo.model;

public class Portfolio {
	int id;
	String name;
	int assetId;
	
	public Portfolio(int id,String name, int assetId) {
		super();
		this.id = id;
		this.name = name;
		this.assetId = assetId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	
	
}
