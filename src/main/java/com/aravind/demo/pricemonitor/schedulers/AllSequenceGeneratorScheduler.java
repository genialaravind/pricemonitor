package com.aravind.demo.pricemonitor.schedulers;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.aravind.demo.pricemonitor.dao.PriceMonitorDB;

@Component
public class AllSequenceGeneratorScheduler {
	
   private static final Logger logger = LoggerFactory.getLogger(AllSequenceGeneratorScheduler.class);

	
	@Autowired
	PriceMonitorDB priceMonitorDB;
	
    private AtomicLong value = new AtomicLong(1);

	@Scheduled(fixedRate=30000)
	public Long sequenceGenerator(){
		
		logger.info("Sequence generator running");
		priceMonitorDB.addSquence(value.getAndIncrement());
		logger.info("Generated Seq No : "+priceMonitorDB.getSquence());
		return priceMonitorDB.getSquence();
	}

}
