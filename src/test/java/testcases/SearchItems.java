package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.Address;
import pages.ConfigReader;
import pages.LoginPage;
import pages.SearchItemsPage;

public class SearchItems extends BaseTest{

	LoginPage login = new LoginPage();
	SearchItemsPage search = new SearchItemsPage();
	ConfigReader configReader = new ConfigReader();
	String path =  configReader.getProperty("filePath");
	private Address address;
	String item = configReader.getProperty("item");
	String item_name = configReader.getProperty("item_name");
	String color = configReader.getProperty("color");
	String quantity = configReader.getProperty("quantity");
	String size = configReader.getProperty("size");
	String item_type = configReader.getProperty("item_type");
	
	@BeforeClass
    public void address() {
        address = new Address(
            configReader.getProperty("street"),
            configReader.getProperty("city"),
            configReader.getProperty("state"),
            configReader.getProperty("country"),
            configReader.getProperty("pincode"),
            configReader.getProperty("phoneNumber")
        );
    }
	
		
	@Test
	public void add_item_to_cart_TC004()
	{	
		search.add_item_to_cart(item,item_name,item_type, size, color,quantity);
	}
	
	@Test
	public void checkout_items_in_cart_TC005() throws InterruptedException
	{
		Thread.sleep(2000);
		String item_name = configReader.getProperty("item_name");
		if(configReader.getProperty("order").equals("OneItem"))
		{
			search.checkout_an_item_in_cart(item_name,address);
		}
		else 
		{
		search.checkout_allitems_in_cart(address);
	}
	}
	
	@Test
	public void add_item_to_wishlist_TC006()
	{
		search.add_item_to_wishlist(item, item_name, size, color, quantity);
	}

}
