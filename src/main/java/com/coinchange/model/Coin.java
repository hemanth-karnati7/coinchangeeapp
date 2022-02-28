package com.coinchange.model;

public class Coin {
	
	private String denomiation;
	private Integer count;
	
	
	public Coin() {
		super();
	}
	
	


	public Coin(String denomiation, Integer count) {
		super();
		this.denomiation = denomiation;
		this.count = count;
	}




	public String getDenomiation() {
		return denomiation;
	}


	public void setDenomiation(String denomiation) {
		this.denomiation = denomiation;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}




	@Override
	public String toString() {
		return "Coin [denomiation=" + denomiation + ", count=" + count + "]";
	}
	
	
	
}
