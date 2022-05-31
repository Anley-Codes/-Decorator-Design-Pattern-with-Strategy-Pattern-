package com.decoratorproject.src;

import java.util.ArrayList;

public class PurchasedItems {
	private ArrayList<StoreItem> items; 
	public PurchasedItems() { 
		items = new ArrayList();
	}
	
	
	public ArrayList getItems() {
		return this.items;
	}
	
	public void addItem(StoreItem item) { 
		items.add(item);
		
	}
	public double getTotalCost() { 
		double totalCost=0;

		StoreItem currentItem;

		for(int i=0;i<items.size();i++) {
			currentItem=items.get(i);
			
			totalCost+=Double.parseDouble(currentItem.getItemPrice());
			
		}
		return totalCost;

		
	}
	public boolean containsItem(String itemCode) { 
		
		StoreItem currentItem;
		for(int i=0;i<items.size();i++) {
			currentItem=items.get(i);
			
			if(currentItem.getItemCode().equals(itemCode)) {
				
				return true;
			}
			
		}
		
		
		return false;
	}
}
