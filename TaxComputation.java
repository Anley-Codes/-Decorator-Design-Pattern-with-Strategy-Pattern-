package com.decoratorproject.src;

public abstract class TaxComputation {

	public abstract double computeTax(PurchasedItems items, ReceiptDate date);
	protected abstract boolean taxHoliday(ReceiptDate date);
}
