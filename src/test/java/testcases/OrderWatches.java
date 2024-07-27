package testcases;

import java.util.ArrayList;

import org.testng.annotations.Test;

import pages.ConfigReader;
import pages.GetDataFromExcel;
import pages.OrderWatchesPage;

public class OrderWatches extends BaseTest{
	
	ConfigReader configReader = new ConfigReader();
	OrderWatchesPage order = new OrderWatchesPage();
	String path =  configReader.getProperty("filePath");
	@Test(enabled=false)
	public void order_watches() throws InterruptedException{
		
		String sheetName = configReader.getProperty("sheetName3");
		ArrayList<String> data = GetDataFromExcel.getTestData(path, sheetName);	
		order.add_watch_to_cart(data.get(0));
		String zipcode = data.get(7);
		double pincode = Double.parseDouble(zipcode);
		String phoneNumber = data.get(8);
		double phone = Double.parseDouble(phoneNumber);
		order.order_watch(data.get(0), data.get(1), data.get(2), data.get(3), data.get(4), data.get(5), data.get(6),(long) pincode, (long)phone);
	
	}
	

}
