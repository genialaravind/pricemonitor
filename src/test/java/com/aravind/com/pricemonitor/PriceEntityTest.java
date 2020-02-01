package com.aravind.com.pricemonitor;

import static org.junit.Assert.*;

import org.junit.Test;

import com.aravind.demo.pricemonitor.dao.PriceEntity;

public class PriceEntityTest extends PricemonitorApplicationTests{
	
	@Test
	public void checkEqualsPositive(){
		
		PriceEntity p1 = new PriceEntity("Infosys",19.5);
		
		PriceEntity p2 = new PriceEntity("Infosys",19.5);
		
		assertTrue(p1.equals(p2));
		
	}
	
	@Test
	public void checkEqualsPositive1(){
		
		PriceEntity p1 = new PriceEntity("Infosys",19.4);
		
		PriceEntity p2 = new PriceEntity("Infosys",new Double(19.4));
		
		assertTrue(p1.equals(p2));
		
	}
	
	@Test
	public void checkEqualsNegative(){
		
		PriceEntity p1 = new PriceEntity("Infosys",19.5);
		
		PriceEntity p2 = new PriceEntity("Infosy",19.5);
		
		assertFalse(p1.equals(p2));
		
	}
	
	@Test
	public void checkEqualsNegative1(){
		
		PriceEntity p1 = new PriceEntity(null,19.5);
		
		PriceEntity p2 = new PriceEntity("Infosys",19.5);
		
		assertFalse(p1.equals(p2));
		
	}
	
	@Test
	public void checkEqualsNegative2(){
		
		PriceEntity p1 = new PriceEntity("Infosys",19.4);
		
		PriceEntity p2 = new PriceEntity("Infosys",19.5);
		
		assertFalse(p1.equals(p2));
		
	}
	
	@Test
	public void checkEqualsNegative3(){
		
		PriceEntity p1 = new PriceEntity("Infosys",19);
		
		PriceEntity p2 = new PriceEntity("Infosys",19.5);
		
		assertFalse(p1.equals(p2));
		
	}
}
