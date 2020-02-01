package com.aravind.demo.pricemonitor.dao;

import java.util.LinkedList;
import java.util.List;

public class Entity {
	
	List<PriceEntity> bankPriceEntity = new LinkedList<>();
	List<PriceEntity> companyPriceEntity = new LinkedList<>();
	
	public List<PriceEntity> getBankPriceEntity() {
		return bankPriceEntity;
	}
	public void setBankPriceEntity(List<PriceEntity> bankPriceEntity) {
		this.bankPriceEntity = bankPriceEntity;
	}

	public List<PriceEntity> getCompanyPriceEntity() {
		return companyPriceEntity;
	}
	
	public void setCompanyPriceEntity(List<PriceEntity> companyPriceEntity) {
		this.companyPriceEntity = companyPriceEntity;
	}

	@Override
	public String toString() {
		return "Entity [bankPriceEntity=" + bankPriceEntity + ", companyPriceEntity=" + companyPriceEntity + "]";
	}
}
