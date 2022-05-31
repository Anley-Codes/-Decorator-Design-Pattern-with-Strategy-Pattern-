package com.decoratorproject.src;

public class MDTaxComputation extends TaxComputation {
	
		
	double tax=0;
	String stateCode="";
	ReceiptDate date;
	
	
	public MDTaxComputation(String stateCode) {
		
		double tax=0;
		
		this.stateCode=stateCode;
		
		if(stateCode.equals("CA")) {
			tax=0.075;
		}
		else if(stateCode.equals("MD")) {
			tax=0.06;
		}
		else if(stateCode.equals("MA")) {
			tax=0.0625;
		}
		this.tax=tax;
		
		ReceiptDate dt=new ReceiptDate();
		dt.stateCode=stateCode;
		this.date=dt;

	}
	
	public double getTax() {
		
		return this.tax;

	}
	
	
	
	
	public double computeTax(PurchasedItems items, ReceiptDate date) {
		// calls private method taxHoliday as part of this computation
		
			double tax=0;
			
			double cost=items.getTotalCost();

			
		if(taxHoliday(date)) {
			String stateCode=date.getStateCode();
			
			if(stateCode.equals("CA")) {
				tax=0.075;
			}
			else if(stateCode.equals("MD")) {
				tax=0.06;
			}
			else if(stateCode.equals("MA")) {
				tax=0.0625;
			}
			
			
		}

		return tax;
	}
	protected boolean taxHoliday(ReceiptDate date) {

		
		if((date.stateCode.equals("DE"))){
			return false;
			
			
		}
		
		return true;
		
		
		
		
		
		// returns true if date of receipt within the state�s tax free holiday,
		// else returns false. Supporting method of method computeTax.
	}

}
