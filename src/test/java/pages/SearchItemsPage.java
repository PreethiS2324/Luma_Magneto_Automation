package pages;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchItemsPage extends BaseClass{
	
	public SearchItemsPage()
	{
		super();	
		
	}

	By search_box = By.id("search");
	By search_button = By.xpath("//button[@title='Search']");
	By result = By.xpath("//div[@class='products wrapper grid products-grid']/ol/li");
	By quantity = By.name("qty");
	By add_to_cart = By.xpath("//button[@title='Add to Cart']");
	By cart = By.xpath("//a[@class='action showcart']");
	By items_in_cart = By.xpath("//ol[@id='mini-cart']//li//strong/a");
	By menu_list = By.xpath("//div[@class='section-items nav-sections-items']//nav[@class='navigation']/ul/li");
	By men_tops = By.xpath("//dl[@class='options']//li[1]/a");
	By men_bottoms = By.xpath("//dl[@class='options']//li[2]/a");
	By sort_by = By.xpath("//select[@id='sorter']");
	By proceed_to_checkout = By.xpath("//button[@id='top-cart-btn-checkout']");
	By street_address1 = By.xpath("//input[@name='street[0]']");
	By city = By.name("city");
	By state_dropdown = By.xpath("//select[@name='region_id']");
	By postcode  = By.name("postcode");
	By country_dropdown = By.xpath("//select[@name='country_id']");
	By phoneNumber = By.name("telephone");
	By next_button = By.xpath("//button/span[contains(text(),'Next')]");
	By place_order_button = By.xpath("//button/span[contains(text(),'Place Order')]");
	By thanks_message = By.xpath("//span[contains(text(),'Thank you for your purchase!')]");
	By add_address = By.xpath("//span[contains(text(),'New Address')]");
	By ship_here = By.xpath("//span[contains(text(),'Ship here')]");
	By view_and_edit_cart = By.xpath("//span[contains(text(),'View and Edit Cart')]");
	By cart_items= By.xpath("//div[@class='cart table-wrapper']//tbody");
	
	
	public void add_item_to_cart(String item, String item_name, String size, String colour, int quant) throws InterruptedException
		{
		Thread.sleep(2000);
		driver.findElement(search_box).sendKeys(item);
		driver.findElement(search_button).click();
		Thread.sleep(2000);
		select_item_details(item_name, size, colour, quant);
		driver.findElement(add_to_cart).click();
		}
	
	public ArrayList<String> items_in_cart() throws Exception
	{
		driver.findElement(cart).click();
		Thread.sleep(2000);
		List<WebElement> items = driver.findElements(items_in_cart);
		ArrayList<String> item_names = new ArrayList<String>();
		for(int i=1; i<=items.size();i++)
		{
			String item1 = driver.findElement(By.xpath("//ol[@id='mini-cart']//li//strong/a["+i+"]")).getText();
			item_names.add(item1);
			
		}
		return item_names;
	}
	
	public void order_item_from_menu(String gender, String category, String item_name, String size, String colour, int quant) throws Exception
	{
		if(gender.equals("Male"))
			driver.findElement(By.xpath("//div[@class='section-items nav-sections-items']//nav[@class='navigation']/ul/li[3]")).click();
		else
			driver.findElement(By.xpath("//div[@class='section-items nav-sections-items']//nav[@class='navigation']/ul/li[1]")).click();
		
		if(category.equals("Tops"))
			driver.findElement(men_tops).click();
			
		else
			driver.findElement(men_bottoms).click();
		
		select_item_details(item_name,size,colour,quant);
		driver.findElement(add_to_cart).click();
		checkout_an_item_in_cart(item_name);
		
		
		}
	
	public void select_item_details(String item_name, String size, String colour, int quant)
	{
		try {
			
			driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li//a[contains(text(),'"+item_name+"')]")).click();
			driver.findElement(By.xpath("//div[@option-label='"+size+"']")).click();
			driver.findElement(By.xpath("//div[@option-label='"+colour+"']")).click();	
			driver.findElement(quantity).clear();
			driver.findElement(quantity).sendKeys(Integer.toString(quant));
		}

		catch(Exception e)
		{
			System.out.println("Exception");
		}

	}
	
	public void sort_by(String value)
	{
		Select sort = new Select(driver.findElement(sort_by));
		sort.selectByVisibleText(value);
	}
	
	public void checkout_allitems_in_cart() throws Exception
	{
		System.out.println("Clicking on cart");
		Thread.sleep(3000);
		driver.findElement(cart).click();
		driver.findElement(proceed_to_checkout).click();
		Thread.sleep(3000);
		driver.findElement(next_button).click();
		Thread.sleep(3000);
		driver.findElement(place_order_button).click();
		boolean order_placed = driver.findElement(thanks_message).isDisplayed();
		assertTrue(order_placed,"Order Placed Successfully");
		
	}
	
	public void checkout_allitems_in_cart(String user_type,String address_type,String address, String country, String state, String city_name, long zipcode,long phone) throws Exception
	{
		Thread.sleep(2000);
		driver.findElement(cart).click();
		driver.findElement(proceed_to_checkout).click();
		if(user_type.equals("New"))
				{
				add_address(address,country,state,city_name,zipcode,phone);}
		else if(address_type.equals("New"))
		{
			add_new_address(address,country,state,city_name,zipcode,phone);
		}		
		driver.findElement(next_button).click();
		driver.findElement(place_order_button).click();
		boolean order_placed = driver.findElement(thanks_message).isDisplayed();
		assertTrue(order_placed,"Order Placed Successfully");
		
	}
	

	public void add_address(String address, String country, String state, String city_name, long zipcode,long phone)
	{
		driver.findElement(street_address1).sendKeys(address);
		Select sel_country = new Select(driver.findElement(country_dropdown));
		sel_country.selectByVisibleText(country);
		Select sel_state = new Select(driver.findElement(state_dropdown));
		sel_state.selectByVisibleText(state);
		driver.findElement(city).sendKeys(city_name);
		String zip = Long.toString(zipcode);
		System.out.println("Zipcode:"+zip);
		driver.findElement(postcode).sendKeys(zip);
		String ph = Long.toString(phone);
		System.out.println("Phone: "+ph);
		driver.findElement(phoneNumber).sendKeys(ph);
		
	}
	public void add_new_address(String address, String country, String state, String city_name, long zipcode,long phone)
	{
		driver.findElement(add_address).click();
		add_address(address,country,state,city_name,zipcode,phone);
		driver.findElement(ship_here).click();
	}
	
	public void checkout_an_item_in_cart(String item_name) throws Exception
	{
		driver.findElement(cart).click();
		driver.findElement(view_and_edit_cart).click();
		int count = driver.findElements(cart_items).size();
		for(int i=1;i<=count;i++)
		{
			String name = driver.findElement(By.xpath("//div[@class='cart table-wrapper']//tbody["+i+"]//td//a[contains(text(),'"+item_name+"')]")).getText();
			if(name.equals(item_name))
			{
				continue;
			}
			else
				driver.findElement(By.xpath("//div[@class='cart table-wrapper']//tbody["+i+"]//tr[@class='item-actions']//a[@title='Remove item']"));
		}
		checkout_allitems_in_cart();
	}

	
	
	
}
