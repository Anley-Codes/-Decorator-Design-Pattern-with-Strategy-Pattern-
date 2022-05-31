package com.decoratorproject.src;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class ReceiptFactory {
	String header; // contains line with �Best Buy�, store_num, street_addr, phone
	String state_code;
	ArrayList<StoreItem> catalog;


	private TaxComputation[] taxComputationsObjs=new TaxComputation[4]; // tax computation objects (for each state)
	private AddOn[] addOns=new AddOn[2]; // secondary header, rebate and coupon add-ons
	public ReceiptFactory() { // constructor

		setCatalog();

		TaxComputation tax1=new MDTaxComputation("MD");
		TaxComputation tax2=new MDTaxComputation("DE");
		TaxComputation tax3=new MDTaxComputation("MA");
		TaxComputation tax4=new MDTaxComputation("CA");

		taxComputationsObjs[0]=tax1;
		taxComputationsObjs[1]=tax2;
		taxComputationsObjs[2]=tax3;
		taxComputationsObjs[3]=tax4;
		
		
		
		AddOn addOn1=new HolidayGreeting();
		AddOn addOn2=new Rebate1406();
		
		addOns[0]=addOn1;
		addOns[1]=addOn2;
		
		// 1. Populates array of StateComputation objects and array of AddOn objects (as if downloaded
		//from the BestBuy web site).
		// 2. Reads config file to assign store_num, street_addr, etc.
		// 3. Based on the state code (e.g., �MD�, from the config file) stores appropriate 
		//	StateComputation object to be used on all receipts.
	}
	public Receipt getReceipt(PurchasedItems items) {
		
		BasicReceipt receipt=new BasicReceipt(items);
		Date date=new Date();
		receipt.setDate(date);



		try{
			String configString=new File(System.getProperty("user.dir")+"/bin/config.properties").getCanonicalPath();
			File configFile=new File(configString);

			Properties properties=new Properties();
			FileReader reader=new FileReader(configFile);
			properties.load(reader);
			String storeName= properties.getProperty("storeName");
			String storeAddress= properties.getProperty("storeAddress");
			String storeContact= properties.getProperty("storeContact");
			String stateCode= properties.getProperty("stateCode");
			String storeNumber= properties.getProperty("storeNumber");

			header=storeAddress+", "+storeContact+", "+stateCode+", "+storeNumber;
			state_code=stateCode;


		}
		catch(IOException e){

			System.out.println(e.getMessage());
		}

		receipt.setStoreInfo(header);



		int stateId=0;
		if(state_code=="MD"){
			stateId=0;
		}
		else if(state_code=="DE"){
			stateId=1;
		}
		else if(state_code=="MA"){
			stateId=2;
		}
		else if(state_code=="CA"){
			stateId=3;
		}


		receipt.setTaxComputation(taxComputationsObjs[stateId]);

		Receipt r=(Receipt) receipt;


		for(int i=0;i<addOns.length;i++) {
			
			if(addOns[i].applies(items)) {
				String classType=addOns[i].getClass().getSuperclass().toString();
				
				if(classType.equals("SecondaryHeading")) {
					 r=new PreDecorator(addOns[i],r);

				}
				else {
					r=new PostDecorator(addOns[i],r);

				}
				
			}
			
		
		
		}

		return  r;

	// 1. Sets the current date of the BasicReceipt.
	// 2. Attaches the StateComputation object to the BasicReceipt (by call to the setComputation 
	//method of BasicReceipt).
	// 3. Traverses over all AddOn objects, calling the applies method of each. If an AddOn object 
	//applies, then determines if the AddOn is of type SecondaryHeader, Rebate, or Coupon. 
	//If of type SecondaryHeader, then creates a PreDecorator for othe AddOn. If of type Rebate or 
	//Coupon, then creates a PostDecorator.
	// 4. Links in the decorator object based on the Decorator design pattern.
	// 5. Adds current date to 
	// 6. Returns decorated BasicReceipt object as type Receipt.
	}

	public void setCatalog(){

		ArrayList<StoreItem> catalog=new ArrayList<StoreItem>();

		StoreItem item1=new StoreItem("POP220","Limited Edition Superman #1","200");
		StoreItem item2=new StoreItem("POP112","Acer Predator 2019 Edition","700");
		StoreItem item3=new StoreItem("EOQ203","Macbook Air, 2020","1500");
		StoreItem item4=new StoreItem("RR345","Samsung Galaxy Note 8","600");
		StoreItem item5=new StoreItem("P33RE","Samsung Galaxy Note FE","400");

		catalog.add(item1);
		catalog.add(item2);
		catalog.add(item3);
		catalog.add(item4);
		catalog.add(item5);

		this.catalog=catalog;


	}

	public ArrayList<StoreItem> getCatalog(){

		return this.catalog;

	}



}
