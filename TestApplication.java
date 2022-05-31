package com.decoratorproject.src;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TestApplication {
	public static void main(String[] args)
	{
	// 1. Creates a PurchasedItems object.
	// 2. Constructs ReceiptFactory object.
	// 3. Prompts user for items to purchase, storing each in PurchasedItems.
	// 4. Calls the getReceipt method of the factory to obtain constructed receipt.
	// 5. Prints receipt by method call to constructed receipt.
	// get receipt date
	// (prompt user for current date)
	// display all available products to user
//	(to be implemented)
	// get all user selections
//	(to be implemented)
		PurchasedItems items=new PurchasedItems();

		Scanner prompt=new Scanner(System.in);

		Date date=new Date();
	
		ReceiptFactory factory = new ReceiptFactory();

		ArrayList<StoreItem> catalog= factory.getCatalog();

		System.out.println("Select item to store in cart: (Item number)");
		System.out.println("Enter done to cash out");


		for(int i=0;i<catalog.size();i++){
			System.out.println((i*1+1)+". "+catalog.get(i).getItemDescription()+"+ "+catalog.get(i).getItemPrice());
		}

		boolean close=false;
		while(!close) {
			String itemCode = prompt.nextLine();

			try{
				int choice=Integer.parseInt(itemCode);

				StoreItem chosen=catalog.get(choice-1);

				items.addItem(chosen);
				System.out.println("You have added "+chosen.getItemDescription());
				System.out.println("Please select more items? (Enter done to Checkout");


			}
			catch(Exception e){
				if(itemCode.equals("done")){
				}
				else {
					System.out.println("Invalid choice.  Select a valid choice");
				}
			}



			if(itemCode.equals("done")){
				close=true;
			}


		}

		Receipt r=factory.getReceipt(items);

		r.prtReceipt();


	
	}
}
