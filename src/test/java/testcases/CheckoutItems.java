package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.Address;
import pages.CheckoutItemsPage;
import pages.ConfigReader;
import pages.LoginPage;

public class CheckoutItems extends BaseTest{

	ConfigReader configReader = new ConfigReader();
	CheckoutItemsPage checkout = new CheckoutItemsPage();
	public Address address;
	
	LoginPage login = new LoginPage();
	String item = configReader.getProperty("item");
	String item_type = configReader.getProperty("item_type");
	String item_name = configReader.getProperty("item_name");
	String size = configReader.getProperty("size");
	String color = configReader.getProperty("color");
	String quantity = configReader.getProperty("quantity");
	String bag_name = configReader.getProperty("bag_name");
	String equipment_name = configReader.getProperty("equipment_name");
	String watch_name = configReader.getProperty("watch_name");
	
	@Test
	public void add_item_to_cart_TC004()
	{

		System.out.println(item_name);
		checkout.add_item_to_cart(item, item_name, item_type, size, color, quantity);
	}
	@Test
	public void checkout_items_in_cart_TC005() throws InterruptedException
	{
		
		  address = new Address(
		            configReader.getProperty("street"),
		            configReader.getProperty("city"),
		            configReader.getProperty("state"),
		            configReader.getProperty("country"),
		            configReader.getProperty("pincode"),
		            configReader.getProperty("phoneNumber")
		        );
		  
		Thread.sleep(2000);
		checkout.checkout_an_item_in_cart(item_name,address);

	}
	
	@Test
	public void add_item_to_wishlist_TC006()
	{
		checkout.add_item_to_wishlist(item, item_name, item_type,size,color,quantity);
		
	}
	
	@Test
	public void order_watches_TC007() throws InterruptedException{
		
		checkout.add_watch_to_cart(watch_name);
		checkout.checkout_an_item_in_cart(watch_name, address);
		
	}
	
	@Test
	public void order_fitness_equipment_TC008() throws InterruptedException
	{
		checkout.add_fitness_equipment_to_cart(equipment_name);
		checkout.checkout_an_item_in_cart(equipment_name, address);
	}
	
	@Test
	public void order_bags_TC009() throws InterruptedException
	{
		
		checkout.add_bag_to_cart(bag_name);
		checkout.checkout_an_item_in_cart(bag_name, address);
}
	@Test
	public void add_bag_to_wishlist_TC010() throws InterruptedException
	{
		checkout.add_bag_to_wishlist(bag_name);
	}
	

	@Test
	public void add_watch_to_wishlist_TC011()
	{
		checkout.add_watch_to_wishlist(bag_name);
	}
	

	@Test
	public void add_fitness_equipment_to_wishlist_TC012()
	{
		checkout.add_fitness_equipment_to_wishlist(bag_name);
	}



	
}
