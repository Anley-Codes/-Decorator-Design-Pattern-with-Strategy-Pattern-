package com.decoratorproject.src;

import java.util.ArrayList;
import java.util.Date;

public class BasicReceipt implements Receipt {
	
	private String storeInfo; // store number, store address, phone number
	private String stateCode; // MD, DE, CA or MA
	private PurchasedItems items;
	private Date date;
	private TaxComputation tc;
	public BasicReceipt(PurchasedItems items) {
		this.items = items;
	}
	public void setTaxComputation(TaxComputation tc) { 
		this.tc = tc; 
	}
	public void setDate(Date date) { this.date = date; }
	
	public Date getDate() { return this.date; }
	
	public String getStateCode() { return this.stateCode; }
	
	public void setStoreInfo(String storeInfo) {
		this.storeInfo=storeInfo;
	}
	
	
	
	public void prtReceipt() {
		System.out.println("");
		System.out.println(this.storeInfo);
		System.out.println(this.date);
		System.out.println("Item Code\t\tItem Description\t\tItem Price");
		ArrayList items=this.items.getItems();
		
		double tax=0;
		double itemPrice=0;
		for(int i=0;i<items.size();i++) {
			
			StoreItem item=(StoreItem) items.get(i);
			

			itemPrice=Double.parseDouble(item.getItemPrice());
			
			String carriage="\t";

			if(item.getItemDescription().length()<22){
				carriage+="\t";
			}

			if(item.getItemDescription().length()<20){
				carriage+="\t";

			}
			
			System.out.println(item.getItemCode()+"\t\t"+item.getItemDescription()+carriage+Double.toString(itemPrice));
		}
		MDTaxComputation tx=(MDTaxComputation) tc;
		double taxCalculate=tc.computeTax(this.items, tx.date);



		double final_cost=this.items.getTotalCost();

		double final_price=final_cost+(taxCalculate*final_cost);

		
		System.out.println("Total Sale (Without Tax): "+Double.toString(final_cost));
		System.out.println("Amount Due (With Tax): "+Double.toString(final_price));



	}

}
