package com.coinchange.model;

import java.util.List;

public class CoinDTO {
	
	private List<Coin> data;
	private String error;
	public List<Coin> getData() {
		return data;
	}
	public void setData(List<Coin> data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	

}
