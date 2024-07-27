package testcases;

import java.util.ArrayList;

import org.testng.annotations.Test;

import pages.ConfigReader;
import pages.GetDataFromExcel;
import pages.SearchItemsPage;

public class SearchItems extends BaseTest{


	SearchItemsPage search = new SearchItemsPage();
	ConfigReader configReader = new ConfigReader();
	String path =  configReader.getProperty("filePath");
	@Test(enabled=false)
	public void add_item_to_cart(){
		
		String sheetName = configReader.getProperty("sheetName2");
		ArrayList<String> data = GetDataFromExcel.getTestData(path, sheetName);
		double d = Double.parseDouble(data.get(4));
		search.add_item_to_cart(data.get(0), data.get(1), data.get(2), data.get(3),(int) d);
	
	}
	
	@Test(enabled=false)
	public void checkout_items_in_cart() throws InterruptedException
	{
		Thread.sleep(2000);
		String sheetName = configReader.getProperty("sheetName2");
		ArrayList<String> data = GetDataFromExcel.getTestData(path, sheetName);
		String zipcode = data.get(11);
		double pincode = Double.parseDouble(zipcode);
		String phoneNumber = data.get(12);
		double phone = Double.parseDouble(phoneNumber);
		Thread.sleep(2000);
		if(configReader.getProperty("order").equals("OneItem"))
		{
			search.checkout_an_item_in_cart(data.get(1),data.get(5), data.get(6), data.get(7),data.get(8),data.get(9), data.get(10),(long)pincode,(long)phone);
		}
		else if(configReader.getProperty("order").equals("AllItems"))
		{
		search.checkout_allitems_in_cart(data.get(5), data.get(6), data.get(7),data.get(8),data.get(9), data.get(10),(long)pincode,(long)phone);
	}
	}
	
	@Test
	public void get_items_in_cart() throws Exception
	{
		
		Thread.sleep(2000);
		ArrayList<String> items_in_cart = search.items_in_cart();
		items_in_cart.forEach(number->System.out.println(number));
	}
	

}
