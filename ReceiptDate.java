package com.decoratorproject.src;

import java.util.Date;

public class ReceiptDate {

	Date date;
	String stateCode;
	public ReceiptDate() {
	}
	public void setDate(Date date) {
		this.date=date;
		
		
	}
	
	public Date getDate() {
		return this.date;
		
	}
	
	public void setStateCode(String stateCode) {
		this.stateCode=stateCode;
		
		
	}
	
	public String getStateCode() {
		return this.stateCode;
		
	}
	
}
