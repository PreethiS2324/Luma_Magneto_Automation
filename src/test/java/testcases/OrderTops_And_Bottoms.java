package testcases;

import org.testng.annotations.Test;

import pages.Address;
import pages.ConfigReader;
import pages.Order_Tops_And_BottomsPage;

public class OrderTops_And_Bottoms extends BaseTest {
	
	ConfigReader configReader = new ConfigReader();
	Order_Tops_And_BottomsPage order = new Order_Tops_And_BottomsPage();
	String path =  configReader.getProperty("filePath");
	private Address address;
	
	String item_name = configReader.getProperty("item_name");
	String color = configReader.getProperty("color");
	String quantity = configReader.getProperty("quantity");
	String size = configReader.getProperty("size");

	
	@Test
	public void order_tops_bottom_item() throws InterruptedException{
		
		address = new Address(
				configReader.getProperty("street"),
	            configReader.getProperty("city"),
	            configReader.getProperty("state"),
	            configReader.getProperty("country"),
	            configReader.getProperty("pincode"),
	            configReader.getProperty("phoneNumber")
	        );
		
		order.order_item(item_name, size, color, quantity, address);
	
	}

	
}
