package com.aravind.demo.pricemonitor.listeners;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aravind.demo.pricemonitor.dao.Entity;
import com.aravind.demo.pricemonitor.dao.PriceEntity;
import com.aravind.demo.pricemonitor.dao.PriceMonitorDB;

@Component
public class ThirdPartyCompanyPriceListener implements PriceListener {
	
	@Autowired
	PriceMonitorDB priceMonitorDB;

	@Override
	public void priceUpdate(String symbol, double price) {
		
		PriceEntity priceEntity = new PriceEntity(symbol,price);
		Long key = priceMonitorDB.getSquence();
		
		Map<Long, Entity> dataMap = priceMonitorDB.getDataMap();
		
		if(dataMap.containsKey(key)){
			Entity entity = dataMap.get(key);
			entity.getCompanyPriceEntity().add(priceEntity);
		}else{
			Entity entity = new Entity();
			entity.getCompanyPriceEntity().add(priceEntity);
			dataMap.put(key,entity);
			priceMonitorDB.setDataMap(dataMap);
		}
	}
}
