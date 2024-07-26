package testcases;

import java.util.ArrayList;

import org.testng.annotations.Test;

import pages.GetDataFromExcel;
import pages.SearchItemsPage;

public class SearchItems extends BaseTest{


	SearchItemsPage search = new SearchItemsPage();
	@Test(enabled=false)
	public void add_item_to_cart() throws InterruptedException {
		
		String path = "C:\\Users\\LENOVO\\eclipse-workspace\\ExcelR\\luma_input.xlsx";
		ArrayList<String> data = GetDataFromExcel.getTestData(path, "items");
		System.out.println("Data from Excel: "+data);	
		double d = Double.parseDouble(data.get(3));
		search.add_item_to_cart(data.get(0), data.get(1), data.get(2), data.get(3),(int) d);
	
	}
	
	@Test
	public void checkout_items_in_cart() throws Exception
	{
		String path = "C:\\Users\\LENOVO\\eclipse-workspace\\ExcelR\\luma_input.xlsx";
		ArrayList<String> data = GetDataFromExcel.getTestData(path, "items");
		System.out.println("Data from Excel: "+data);
		String zipcode = data.get(11);
		System.out.println("Zipcode and Phonenumber: "+data.get(11)+" "+data.get(12));
		double pincode = Double.parseDouble(zipcode);
		String phoneNumber = data.get(12);
		double phone = Double.parseDouble(phoneNumber);
	
		search.checkout_allitems_in_cart(data.get(5), data.get(6), data.get(7),data.get(8),data.get(9), data.get(10),(long)pincode,(long)phone);
	}
	
}
