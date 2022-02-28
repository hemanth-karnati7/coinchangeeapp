package com.coinchange.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coinchange.model.Coin;
import com.coinchange.model.CoinDTO;
import com.coinchange.service.CoinChangeService;

@RestController
public class CoinChangeController {

	@Autowired
	CoinChangeService coinChangeService;

	@GetMapping("/coins/{billAmount}")
	@ResponseBody
	public CoinDTO getCoinChange(@PathVariable String billAmount) {
		CoinDTO dto = new CoinDTO();
		try {
			List<Coin> coins = coinChangeService.getCoins(Integer.parseInt(billAmount));
			dto.setData(coins);
			dto.setError(null);
		} catch (NumberFormatException e) {
			dto.setData(null);
			dto.setError("Invalid input in bill amount");
		} catch (Exception e) {
			dto.setError("exception : " + e.getMessage());
		}
		return dto;
	}

}
