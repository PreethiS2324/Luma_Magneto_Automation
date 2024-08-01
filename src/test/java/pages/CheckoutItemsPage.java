package pages;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CheckoutItemsPage extends BaseClass{

	public CheckoutItemsPage()
	{
		super();
		
	}
	
	ConfigReader configReader = new ConfigReader();
	By men_dropdown = By.xpath("//div[@role='tabpanel']//li//span[contains(text(),'Men')]");
	By women_dropdown = By.xpath("//div[@role='tabpanel']//li//span[contains(text(),'Women')]");
	By no_of_pages = By.xpath("//div[@class='pages']//li[@class='item' or @class='item current']");
	By next_page = By.xpath("//li[@class='item pages-item-next']");
	By quantity = By.name("qty");
	By cart = By.xpath("//a[@class='action showcart']");
	By view_and_edit_cart = By.xpath("//span[contains(text(),'View and Edit Cart')]");
	By cart_items= By.xpath("//div[@class='cart table-wrapper']//tbody");
	By quantity_to_order = By.xpath("//input[@class='input-text qty']");
	By update_cart = By.xpath("//span[contains(text(),'Update Shopping Cart')]");
	By proceed_to_checkout = By.xpath("//button[@id='top-cart-btn-checkout']");
	By next_button = By.xpath("//button/span[contains(text(),'Next')]");
	By place_order_button = By.xpath("//button/span[contains(text(),'Place Order')]");
	By thanks_message = By.xpath("//span[contains(text(),'Thank you for your purchase!')]");
	By add_address = By.xpath("//span[contains(text(),'New Address')]");
	By ship_here = By.xpath("//span[contains(text(),'Ship here')]");
	By street_address1 = By.xpath("//input[@name='street[0]']");
	By city = By.name("city");
	By state_dropdown = By.xpath("//select[@name='region_id']");
	By postcode  = By.name("postcode");
	By country_dropdown = By.xpath("//select[@name='country_id']");
	By phoneNumber = By.name("telephone");
	By add_to_cart = By.xpath("//button[@title='Add to Cart']");
	By search_box = By.id("search");
	By search_button = By.xpath("//button[@title='Search']");
	By add_to_wishlist = By.xpath("//span[contains(text(),'Add to Wish List')]");
	By gear = By.xpath("//div[@role='tabpanel']//li//span[contains(text(),'Gear')]");
	By bags = By.xpath("//a[contains(text(),'Bags')]");
	By watches = By.xpath("//a[contains(text(),'Watches')]");
	By fitness_equipment = By.xpath("//a[contains(text(),'Fitness Equipment')]");
	By bags_count = By.xpath("//a[contains(text(),'Bags')]/following-sibling::span[@class='count']");
	By watches_count = By.xpath("//a[contains(text(),'Watches')]/following-sibling::span[@class='count']");
	By fitness_equipment_count = By.xpath("//a[contains(text(),'Fitness Equipment')]/following-sibling::span[@class='count']");
	
	
	public void add_item_to_cart(String item, String item_name, String item_type,String size, String color, String quant)
	{
	select_tops_bottoms(item_name, item_type,size, color, quant);
	driver.findElement(add_to_cart).click();
	}
	
	public void select_tops_bottoms(String item_name,String item_type,String size, String colour, String quantity)
	{
		String gender = configReader.getProperty("gender");
		if(gender.equals("Male"))
			driver.findElement(men_dropdown).click();
		else
			driver.findElement(women_dropdown).click();
		
		String total_items = driver.findElement(By.xpath("//a[contains(text(),'"+item_type+"')]/following-sibling::span[@class='count']")).getText();
		int tot_items =Integer.parseInt(total_items);
		System.out.println("Total items: "+tot_items);
		if(tot_items<=12)	
		{
			driver.findElement(By.xpath("//a[contains(text(),'"+item_type+"')]")).click();
		}
		else
			select_items_in_next_page(item_name,item_type);
		select_item_details(item_name, size, colour, quantity);
	
	}
	
	public void select_items_in_next_page(String item_name,String item_type)
	{
		driver.findElement(By.xpath("//a[contains(text(),'"+item_type+"')]")).click();
		int pages = driver.findElements(no_of_pages).size();
		System.out.println("Pages: "+pages);
		
		int items=0;
		for(int i=1;i<=pages;i++)
		{
			WebElement item = driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li//a[contains(text(),'"+item_name+"')]"));
			if(item.isDisplayed())
			{
				driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li//a[contains(text(),'"+item_name+"')]")).click();
				items=1;
				break;
			}
			else
				driver.findElement(next_page).click();		
		}
		if(items==0)
			Assert.fail("The item is not available.");
	}
	
	public void select_item_details(String item_name, String size, String color, String quant)
	{
		try {
			driver.findElement(By.xpath("//div[@option-label='"+size+"']")).click();	
			int colors = driver.findElements(By.xpath("//div[@class='swatch-attribute color']//div[@class='swatch-option color']")).size();
			System.out.println("Colors count: "+colors);
			System.out.println("Color to order: "+color);
			int color_1=0;
			List<String> availableColors  = new ArrayList<>();
			for(int i=1;i<=colors;i++)
			{
				String color_to_add = driver.findElement(By.xpath("//div[@class='swatch-attribute color']//div[@class='swatch-option color']["+i+"]")).getAttribute("option-label"); 
				availableColors .add(color_to_add);
				if (availableColors.contains(color)) {
					driver.findElement(By.xpath("//div[@option-label='"+color+"']")).click();
					color_1=1;
					break;
		        } else 
		        	continue;			
			}
			if(color_1==0)
				Assert.fail("Color selected is not available");       
			driver.findElement(quantity).clear();
			driver.findElement(quantity).sendKeys(quant);
		}catch(Exception e)
		{
			System.out.println("Exception");
		}
	}

	
	public void checkout_an_item_in_cart(String item_name,Address address) throws InterruptedException
	{
		driver.findElement(cart).click();
		Thread.sleep(2000);
		driver.findElement(view_and_edit_cart).click();
		int count = driver.findElements(cart_items).size();
		for(int i=count;i<=count;i--)
		{
			if(i!=0) {
			String name = driver.findElement(By.xpath("//div[@class='cart table-wrapper']//tbody["+i+"]//td//strong/a")).getText();
			if(name.equals(item_name))
				continue;
			else
				driver.findElement(By.xpath("//div[@class='cart table-wrapper']//tbody["+i+"]//tr[@class='item-actions']//a/span[contains(text(),'Move to Wishlist')]")).click();			
			}
			else
				break;
		}
		driver.findElement(quantity_to_order).clear();
		String quan_to_order = configReader.getProperty("quantity_to_order");
		driver.findElement(quantity_to_order).sendKeys(quan_to_order);
		driver.findElement(update_cart).click();
		Thread.sleep(2000);
		checkout_allitems_in_cart(address);
	}
	
	public void checkout_allitems_in_cart_with_existing_address()
	{
		driver.findElement(cart).click();
		driver.findElement(proceed_to_checkout).click();
		driver.findElement(next_button).click();
		driver.findElement(place_order_button).click();
		boolean order_placed = driver.findElement(thanks_message).isDisplayed();
		assertTrue(order_placed,"Order Placed Successfully");
		
	}
	public void checkout_allitems_in_cart(Address address) throws InterruptedException
	{
		Thread.sleep(3000);
		driver.findElement(cart).click();
		driver.findElement(proceed_to_checkout).click();
		String user_type = configReader.getProperty("user_type");
		String address_type = configReader.getProperty("address_type");
		if(user_type.equals("New") )
		{
			add_adress(address);
		}
		else if(address_type.equals("New"))
		{		
			Thread.sleep(2000);
			driver.findElement(add_address).click();
			add_adress(address);
			driver.findElement(ship_here).click();	
		}		
		Thread.sleep(3000);
		driver.findElement(next_button).click();
		Thread.sleep(3000);
		driver.findElement(place_order_button).click();
		Thread.sleep(2000);
		boolean order_placed = driver.findElement(thanks_message).isDisplayed();
		assertTrue(order_placed,"Order Placed Successfully");
		
	}
		public void add_adress(Address address) {
		
		driver.findElement(street_address1).sendKeys(address.getStreet());
		Select sel_country = new Select(driver.findElement(country_dropdown));
		sel_country.selectByVisibleText(address.getCountry());
		Select sel_state = new Select(driver.findElement(state_dropdown));
		sel_state.selectByVisibleText(address.getState());
		driver.findElement(city).sendKeys(address.getCity());
		driver.findElement(postcode).sendKeys(address.getPincode());
		driver.findElement(phoneNumber).sendKeys(address.getPhoneNumber());
		
    
    }
		
		public void add_item_to_wishlist(String item, String item_name, String item_type,String size,String color,String quant)
		{
		select_tops_bottoms(item_name, item_type,size, color, quant);
		driver.findElement(add_to_wishlist).click();
		}
		
		public void select_fitness_equipment(String equipment_name)
		{
			driver.findElement(gear).click();
			String total_items = driver.findElement(fitness_equipment_count).getText();
			int tot_items =Integer.parseInt(total_items);
			if(tot_items<=12)	
			{
				driver.findElement(fitness_equipment).click();
			}
			else
				select_items_in_next_page(equipment_name,null);
			
			driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li//a[contains(text(),'"+equipment_name+"')]")).click();
		}
		public void select_watch(String watch_name)
		{
			driver.findElement(gear).click();
			String total_items = driver.findElement(watches_count).getText();
			int tot_items =Integer.parseInt(total_items);
			if(tot_items<=12)	
			{
				driver.findElement(watches).click();
			}
			else
				select_items_in_next_page(watch_name,null);
			
			driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li//a[contains(text(),'"+watch_name+"')]")).click();
		}

		public void add_watch_to_cart(String watch_name)
		{
			
			select_watch(watch_name);
			driver.findElement(add_to_cart).click();
		}
		public void add_fitness_equipment_to_cart(String equipment_name)
		{
			
			select_fitness_equipment(equipment_name);
			driver.findElement(add_to_cart).click();
		}
		public void select_bag(String bag_name) throws InterruptedException
		{
			driver.findElement(gear).click();
			String total_items = driver.findElement(bags_count).getText();
			int tot_items =Integer.parseInt(total_items);
			if(tot_items<=12)	
			{
				driver.findElement(bags).click();
				Thread.sleep(2000);
			}
			else
				select_items_in_next_page(bag_name,null);
			
			driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li//a[contains(text(),'"+bag_name+"')]")).click();
		}
		
		public void add_bag_to_cart(String bag_name) throws InterruptedException
		{
			
			select_bag(bag_name);
			driver.findElement(add_to_cart).click();
		}
		
		public void add_bag_to_wishlist(String bag_name) throws InterruptedException
		{
			
			select_bag(bag_name);
			driver.findElement(add_to_wishlist).click();
		}
		
		public void add_watch_to_wishlist(String watch_name)
		{
			
			select_watch(watch_name);
			driver.findElement(add_to_wishlist).click();
		}
		public void add_fitness_equipment_to_wishlist(String equipment_name)
		{
			
			select_fitness_equipment(equipment_name);
			driver.findElement(add_to_wishlist).click();
		}

		
}

