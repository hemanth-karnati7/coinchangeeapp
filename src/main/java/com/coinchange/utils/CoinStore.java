package com.coinchange.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CoinStore {

	@Value("${coin.numbers}")
	private String countCountStr;

	private static ConcurrentHashMap<Integer, Integer> map;

	public void buildCoinStore() {
		map = new ConcurrentHashMap<>();
		String coinsCnt[] = countCountStr.split(",");
		for (String c : coinsCnt) {
			String[] ccount = c.split("=");
			map.put(Integer.parseInt(ccount[0]), Integer.parseInt(ccount[1]));
		}
	}

	public int useCoin(int coin, int requestedCount) {
		int canUse = 0;
		int rem = map.get(coin);
		if (requestedCount > rem) {
			canUse = -1;
		} else {
			map.put(coin, map.get(coin) - requestedCount);
		}
		return canUse;
	}

	public Map<Integer, Integer> getCoinStore() {
		return map;
	}

}
