package com.coinchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoinchangeappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinchangeappApplication.class, args);
		System.out.println("Coin change application started");
	}

}
