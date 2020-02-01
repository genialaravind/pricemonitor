package com.aravind.com.pricemonitor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aravind.demo.pricemonitor.dao.PriceMonitorDB;
import com.aravind.demo.pricemonitor.listeners.BankPriceListener;
import com.aravind.demo.pricemonitor.listeners.ThirdPartyCompanyPriceListener;
import com.aravind.demo.pricemonitor.schedulers.AllSequenceGeneratorScheduler;
import com.aravind.demo.pricemonitor.schedulers.DataVerificationScheduler;

public class ValidatePriceMonitorTest extends PricemonitorApplicationTests {
	
	
	@Autowired
	BankPriceListener bankListener;
	
	@Autowired
	ThirdPartyCompanyPriceListener companyListener;
	
	@Autowired
	PriceMonitorDB priceMonitorDB;
	
	@Autowired
	AllSequenceGeneratorScheduler sequence;
	
	@Autowired
	DataVerificationScheduler verficationScheduler;
	
	
	// From Bank and from Company are same
	@Test
	public void checkValidationSuccess(){
		bankListener.priceUpdate("YesBank", 17.50);
		bankListener.priceUpdate("ICICI", 18.50);
		bankListener.priceUpdate("PNBBank", 19.50);
		
		companyListener.priceUpdate("YesBank", 17.50);
		companyListener.priceUpdate("ICICI", 18.50);
		companyListener.priceUpdate("PNBBank", 19.50);
		
		sequence.sequenceGenerator();

		assertTrue(verficationScheduler.verficationScheduler());		
	}
	
	// From Bank - Multiple Update / Company give latest
	@Test
	public void checkValidationSuccess1(){

		bankListener.priceUpdate("YesBank", 15.50);
		bankListener.priceUpdate("YesBank", 16.50);
		bankListener.priceUpdate("YesBank", 17.50);
		bankListener.priceUpdate("ICICI", 18.50);
		bankListener.priceUpdate("PNBBank", 19.50);
		
		companyListener.priceUpdate("YesBank", 17.50);
		companyListener.priceUpdate("ICICI", 18.50);
		companyListener.priceUpdate("PNBBank", 19.50);
		
		sequence.sequenceGenerator();

		assertTrue(verficationScheduler.verficationScheduler());		
	}
	
	
	// No data sent from bank / No data received from company
	@Test
	public void checkValidationSuccess2(){
		sequence.sequenceGenerator();
		assertTrue(verficationScheduler.verficationScheduler());		
	}
	
	// Data from bank and data from company are inconsistent
	@Test
	public void checkValidationFailure(){
		bankListener.priceUpdate("YesBank", 15.50);
		bankListener.priceUpdate("YesBank", 16.50);
		bankListener.priceUpdate("ICICI", 18.50);
		bankListener.priceUpdate("PNBBank", 19.50);
		
		companyListener.priceUpdate("YesBank", 17.50);
		companyListener.priceUpdate("ICICI", 18.50);
		companyListener.priceUpdate("PNBBank", 19.50);
		
		sequence.sequenceGenerator();
		
		assertFalse(verficationScheduler.verficationScheduler());		
	}

	
	// Data sent from bank but no data received from company
	@Test
	public void checkValidationFailure1(){
		bankListener.priceUpdate("YesBank", 15.50);
		bankListener.priceUpdate("YesBank", 16.50);
		bankListener.priceUpdate("ICICI", 18.50);
		bankListener.priceUpdate("PNBBank", 19.50);
		
		sequence.sequenceGenerator();
		
		assertFalse(verficationScheduler.verficationScheduler());		
	}
	
	// No Data sent from bank but data received from company
	@Test
	public void checkValidationFailure2(){
		companyListener.priceUpdate("YesBank", 17.50);
		companyListener.priceUpdate("ICICI", 18.50);
		companyListener.priceUpdate("PNBBank", 19.50);
		
		sequence.sequenceGenerator();
		
		assertFalse(verficationScheduler.verficationScheduler());		
	}
}
