package com.decoratorproject.src;

public class StoreItem {
	private String itemCode; // e.g., 3010
	private String itemDescription; // e.g., Dell Laptop
	private String itemPrice;
	public StoreItem(String code, String descript, String price)
	{  
		
		// appropriate getters and setters
		this.itemCode=code;
		this.itemDescription=descript;
		this.itemPrice=price;
		
	}

	public void setItemCode(String itemCode){
		this.itemCode=itemCode;


	}
	public void setItemDescription(String itemDescription){
		this.itemDescription=itemDescription;


	}
	public void setItemPrice(String itemPrice){
		this.itemPrice=itemPrice;


	}



	public String getItemCode() {
		
		return this.itemCode;
	
	}
	
	public String getItemDescription() {
		
		return this.itemDescription;
	
	}
	
	public String getItemPrice() {
		
		return this.itemPrice;
	
	}
	
}