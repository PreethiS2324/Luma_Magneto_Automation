package testcases;

import org.testng.annotations.Test;

import pages.Address;
import pages.ConfigReader;
import pages.LoginPage;
import pages.OrderWatchesPage;

public class OrderWatches extends BaseTest{
	
	ConfigReader configReader = new ConfigReader();
	OrderWatchesPage order = new OrderWatchesPage();
	String path =  configReader.getProperty("filePath");
	private Address address;
	LoginPage login = new LoginPage();
	
	@Test
	public void order_watches() throws InterruptedException{
		
		String watch_name = configReader.getProperty("watch_name");
		address = new Address(
				configReader.getProperty("street"),
	            configReader.getProperty("city"),
	            configReader.getProperty("state"),
	            configReader.getProperty("country"),
	            configReader.getProperty("pincode"),
	            configReader.getProperty("phoneNumber")
	        );
		order.add_watch_to_cart(watch_name);
		order.order_watch(watch_name, address);
	
	}
	
}
