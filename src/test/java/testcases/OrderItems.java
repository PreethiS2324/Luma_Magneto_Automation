package testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.Address;
import pages.ConfigReader;
import pages.LoginPage;
import pages.OrderItemsPage;
import pages.SearchItemsPage;

public class OrderItems extends BaseTest {
	
	ConfigReader configReader = new ConfigReader();
	OrderItemsPage order = new OrderItemsPage();
	SearchItemsPage search = new SearchItemsPage();
	LoginPage login = new LoginPage();
	String path =  configReader.getProperty("filePath");
	private Address address;
	
	String item_name = configReader.getProperty("item_name");
	String color = configReader.getProperty("color");
	String quantity = configReader.getProperty("quantity");
	String size = configReader.getProperty("size");
	String item_type = configReader.getProperty("item_type");

	String bag_name = configReader.getProperty("bag_name");
	String watch_name = configReader.getProperty("watch_name");
	String equipment_name = configReader.getProperty("equipment_name");
	
	
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
	public void order_tops_bottom() throws InterruptedException{
		
		order.order_tops_bottoms(item_name,item_type,size, color, quantity, address);
	
	}
	
	@Test
	public void order_watches() throws InterruptedException{
		
		
		order.add_watch_to_cart(watch_name);
		search.checkout_an_item_in_cart(watch_name, address);
		
	}
	
	
	@Test
	public void order_fitness_equipment() throws InterruptedException
	{
		order.add_fitness_equipment_to_cart(equipment_name);
		search.checkout_an_item_in_cart(equipment_name, address);
	}
	
	
	@Test
	public void order_bags() throws InterruptedException
	{
		
		order.add_bag_to_cart(bag_name);
		search.checkout_an_item_in_cart(bag_name, address);
}
	
	
	@Test
	public void add_bag_to_wishlist()
	{
		order.add_bag_to_wishlist(bag_name);
	}
	

	@Test
	public void add_watch_to_wishlist()
	{
		order.add_watch_to_wishlist(bag_name);
	}
	

	@Test
	public void add_fitness_equipment_to_wishlist()
	{
		order.add_fitness_equipment_to_wishlist(bag_name);
	}
	
}
