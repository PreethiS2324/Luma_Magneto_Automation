package pages;

import org.openqa.selenium.By;

public class Order_Tops_And_BottomsPage extends BaseClass{
	
	
	public Order_Tops_And_BottomsPage()
	{
		super();	
		
	}
	SearchItemsPage search = new SearchItemsPage();
	ConfigReader configReader = new ConfigReader();
	
	By men_dropdown = By.xpath("//div[@role='tabpanel']//li//span[contains(text(),'Men')]");
	By women_dropdown = By.xpath("//div[@role='tabpanel']//li//span[contains(text(),'Women')]");
	By tops = By.xpath("//a[contains(text(),'Tops')]");
	By bottoms = By.xpath("//a[contains(text(),'Bottoms')]");
	By add_to_cart = By.xpath("//button[@title='Add to Cart']");
	
	
	public void order_item(String item_name,String size, String colour, String quantity, Address address) throws InterruptedException
	{
		String gender = configReader.getProperty("gender");
		if(gender.equals("Male"))
			driver.findElement(men_dropdown).click();
		else
			driver.findElement(women_dropdown).click();
		String item_type= configReader.getProperty("item_type");
		if(item_type.equals("Tops"))
			driver.findElement(tops).click();
		else
			driver.findElement(bottoms).click();
		search.select_item_details(item_name, size, colour, quantity);
		driver.findElement(add_to_cart).click();
		search.checkout_an_item_in_cart(item_name,address);
		
	}
	
}
