package com.aravind.demo.pricemonitor.schedulers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aravind.demo.pricemonitor.dao.Entity;
import com.aravind.demo.pricemonitor.dao.PriceEntity;
import com.aravind.demo.pricemonitor.dao.PriceMonitorDB;

@Component
public class DataVerificationScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(DataVerificationScheduler.class);

	@Autowired
	PriceMonitorDB priceMonitorDB;
	
	@Scheduled(fixedRate=31000)
	public boolean verficationScheduler() {
		
		logger.info("verficationScheduler Starting");
		
		boolean isVerfied = false;

		Long key = priceMonitorDB.getSquence() - 1;
		
		Map<Long,Entity> dataMap = priceMonitorDB.getDataMap();
		
		logger.info("Data Map = "+dataMap);
		
		Entity  data  = dataMap.get(key);
		
		if(!Objects.isNull(data)){
			List<PriceEntity> bankEntity = data.getBankPriceEntity();
			List<PriceEntity> companyEntity = data.getCompanyPriceEntity();
			
			Map<String,Double> tempMap = new HashMap<>();
			bankEntity.stream().forEach(x->tempMap.put(x.getSymbol(),x.getPrice()));
			
			List<PriceEntity> uniqueBankEntityList = tempMap.entrySet().stream()
					.map(e -> new PriceEntity(e.getKey(), e.getValue())).collect(Collectors.toList()); 	
			
			logger.info("Final Bank Entity List :"+uniqueBankEntityList);
			logger.info("Final Company Entity List :"+companyEntity);
			
			if((companyEntity.size()!=uniqueBankEntityList.size()) || !companyEntity.containsAll(uniqueBankEntityList)){
				// Alert interface can be called here
				logger.warn("##### Alert : Data Inconsistency ##### for SeqNo :"+key);
			}else{
				logger.info("##### Verfication Success ###### for SeqNo :"+key);
				isVerfied = true;
			}
		}else{
			logger.info("No data found for seqNo :"+key);
			isVerfied = true;
		}
		
		return isVerfied;
	}
}
