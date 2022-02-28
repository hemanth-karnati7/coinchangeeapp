package com.coinchange.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coinchange.model.Coin;
import com.coinchange.utils.CoinChangeUtil;
import com.coinchange.utils.CoinStore;

@Service
public class CoinChangeService {

	@Autowired
	CoinStore coinStore;
	
	private static final int SALT = 100;

	private static final int[] denoms = { 1, 5, 10, 25 };
	private static Map<Integer, String> coinMapping = new HashMap<>();
	
	static {
		coinMapping.put(1, "0.001");
		coinMapping.put(5, "0.005");
		coinMapping.put(10, "0.10");
		coinMapping.put(25, "0.25");
	}

	public List<Coin> getCoins(int bill) throws Exception {

		int billToCheck = bill * SALT;

		int changedCoins[] = CoinChangeUtil.minChange(denoms, billToCheck);
		Map<Integer, Integer> coinMap = getMap(changedCoins);

		
		Map<Integer, Integer> storeMap = coinStore.getCoinStore();
		if (storeMap == null)
			coinStore.buildCoinStore();

		for (Integer coinKey : coinMap.keySet()) {
			if (coinStore.useCoin(coinKey, coinMap.get(coinKey)) == -1) {
				throw new Exception("Not enough coin to change the bill");
			}
		}
		return formatResponse(coinMap);
	}

	public List<Coin> formatResponse(Map<Integer, Integer> coinMap) {
		List<Coin> coins = new ArrayList<>();

		for (Map.Entry<Integer, Integer> e : coinMap.entrySet()) {
			Coin c = new Coin();
			c.setDenomiation(coinMapping.get(e.getKey()));
			c.setCount(e.getValue());
			coins.add(c);
		}
		return coins;
	}

	private Map<Integer, Integer> getMap(int changedCoins[]) {

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Integer c : changedCoins) {
			if (map.get(c) != null) {
				map.put(c, map.get(c) + 1);
			} else {
				map.put(c, 1);
			}
		}
		return map;
	}

}
