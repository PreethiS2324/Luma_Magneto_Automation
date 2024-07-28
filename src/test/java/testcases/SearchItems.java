package testcases;

import java.util.ArrayList;

import org.testng.annotations.Test;

import pages.Address;
import pages.ConfigReader;
import pages.GetDataFromExcel;
import pages.LoginPage;
import pages.SearchItemsPage;

public class SearchItems extends BaseTest{


	SearchItemsPage search = new SearchItemsPage();
	LoginPage login = new LoginPage();
	ConfigReader configReader = new ConfigReader();
	String path =  configReader.getProperty("filePath");
	private Address address;
	String item = configReader.getProperty("item");
	String item_name = configReader.getProperty("item_name");
	String color = configReader.getProperty("color");
	String quantity = configReader.getProperty("quantity");
	String size = configReader.getProperty("size");
		
	@Test(enabled=false)
	public void add_item_to_cart(){	
		search.add_item_to_cart(item,item_name, size, color,quantity);
	}
	
	@Test(enabled=false)
	public void checkout_items_in_cart() throws InterruptedException
	{
		Thread.sleep(2000);
		address = new Address(
				configReader.getProperty("street"),
	            configReader.getProperty("city"),
	            configReader.getProperty("state"),
	            configReader.getProperty("country"),
	            configReader.getProperty("pincode"),
	            configReader.getProperty("phoneNumber")
	        );	
		String item_name = configReader.getProperty("item_name");
		if(configReader.getProperty("order").equals("OneItem"))
		{
			search.checkout_an_item_in_cart(item_name,address);
		}
		else if(configReader.getProperty("order").equals("AllItems"))
		{
		search.checkout_allitems_in_cart(address);
	}
	}
	
	@Test(enabled=false)
	public void get_items_in_cart() throws Exception
	{
		
		Thread.sleep(2000);
		ArrayList<String> items_in_cart = search.items_in_cart();
		items_in_cart.forEach(number->System.out.println(number));
	}
	
	
	
	@Test(enabled=false)
	public void search_items()
	{
		search.select_item_details(item_name, size, color, quantity);
	}
	
	@Test
	public void add_item_to_wishlist()
	{
		login.sign_in("abc455@gmail.com", "qwerty@12345");
		search.add_item_to_wishlist(item, item_name, size, color, quantity);
	}

}
