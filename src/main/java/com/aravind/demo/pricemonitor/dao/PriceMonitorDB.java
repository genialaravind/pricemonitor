package com.aravind.demo.pricemonitor.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class PriceMonitorDB {
	
	LinkedList<Long> sequence = new LinkedList<Long>();
	
	Map<Long,Entity> dataMap = new HashMap<>();
	
	public Long getSquence(){
		return sequence.peekLast();
	}
	
	public void addSquence(Long value){
		 sequence.add(value);
	}

	public Map<Long, Entity> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<Long, Entity> dataMap) {
		this.dataMap = dataMap;
	}
}
