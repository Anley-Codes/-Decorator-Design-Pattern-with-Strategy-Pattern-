package com.decoratorproject.src;


public interface AddOn {
	public boolean applies(PurchasedItems items);
	public String getLines();
}
