package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class OrderItemsPage extends BaseClass{
	
	
	public OrderItemsPage()
	{
		super();	
		
	}
	SearchItemsPage search = new SearchItemsPage();
	ConfigReader configReader = new ConfigReader();
	
	By men_dropdown = By.xpath("//div[@role='tabpanel']//li//span[contains(text(),'Men')]");
	By women_dropdown = By.xpath("//div[@role='tabpanel']//li//span[contains(text(),'Women')]");
	By search_box = By.id("search");
	By search_button = By.xpath("//button[@title='Search']");
	By add_to_cart = By.xpath("//button[@title='Add to Cart']");
	By add_to_wishlist = By.xpath("//span[contains(text(),'Add to Wish List')]");
	By gear = By.xpath("//div[@role='tabpanel']//li//span[contains(text(),'Gear')]");
	By bags = By.xpath("//a[contains(text(),'Bags')]");
	By watches = By.xpath("//a[contains(text(),'Watches')]");
	By fitness_equipment = By.xpath("//a[contains(text(),'Fitness Equipment')]");
	By bags_count = By.xpath("//a[contains(text(),'Bags')]/following-sibling::span[@class='count']");
	By watches_count = By.xpath("//a[contains(text(),'Watches')]/following-sibling::span[@class='count']");
	By fitness_equipment_count = By.xpath("//a[contains(text(),'Fitness Equipment')]/following-sibling::span[@class='count']");
	By no_of_pages = By.xpath("//div[@class='pages']//li[@class='item' or @class='item current']");
	By next_page = By.xpath("//li[@class='item pages-item-next']");
	
	public void select_items_in_next_page(String item_name,String item_type)
	{
		int pages = driver.findElements(no_of_pages).size();
		driver.findElement(By.xpath("//a[contains(text(),'"+item_type+"')]")).click();
		int items=0;
		for(int i =1;i<=pages;i++)
		{
			driver.findElement(By.xpath("//div[@class='pages']//li[@class='item' or @class='item current']["+i+"]")).click();
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

	
	public void select_tops_bottoms(String item_name,String item_type,String size, String colour, String quantity)
	{
		String gender = configReader.getProperty("gender");
		if(gender.equals("Male"))
			driver.findElement(men_dropdown).click();
		else
			driver.findElement(women_dropdown).click();
		
		String total_items = driver.findElement(By.xpath("//a[contains(text(),'"+item_type+"')]/following-sibling::span[@class='count']")).getText();
		int tot_items =Integer.parseInt(total_items);
		if(tot_items<=12)	
		{
			driver.findElement(By.xpath("//a[contains(text(),'"+item_type+"')]")).click();
		}
		else
			select_items_in_next_page(item_name,item_type);
		search.select_item_details(item_name, size, colour, quantity);
	
	}
	
	public void add_tops_bottoms_to_cart(String item_name, String item_type,String size, String color, String quantity)
	{
		select_tops_bottoms(item_name,item_type,size,color,quantity);
		driver.findElement(add_to_cart).click();
	}
	
	public void order_tops_bottoms(String item_name,String item_type,String size, String color, String quantity, Address address) throws InterruptedException
	{
		
		add_tops_bottoms_to_cart(item_name,item_type,size,color,quantity);
		search.checkout_an_item_in_cart(item_name,address);
	}
	
	public void add_tops_bottoms_to_wishlist(String item_name, String item_type,String size,String color, String quantity)
	{
		select_tops_bottoms(item_name,item_type,size,color,quantity);
		driver.findElement(add_to_wishlist).click();
	}
	
	public void select_bag(String bag_name)
	{
		driver.findElement(gear).click();
		String total_items = driver.findElement(bags_count).getText();
		int tot_items =Integer.parseInt(total_items);
		if(tot_items<=12)	
		{
			driver.findElement(bags).click();
		}
		else
			select_items_in_next_page(bag_name,null);
		
		driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li//a[contains(text(),'"+bag_name+"')]")).click();
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
	
	
	public void add_bag_to_cart(String bag_name)
	{
		
		select_bag(bag_name);
		driver.findElement(add_to_cart).click();
	}
	
	public void add_bag_to_wishlist(String bag_name)
	{
		
		select_bag(bag_name);
		driver.findElement(add_to_wishlist).click();
	}
	
	
	public void add_watch_to_cart(String watch_name)
	{
		
		select_watch(watch_name);
		driver.findElement(add_to_cart).click();
	}
	
	public void add_watch_to_wishlist(String watch_name)
	{
		
		select_watch(watch_name);
		driver.findElement(add_to_wishlist).click();
	}
	
	public void add_fitness_equipment_to_cart(String equipment_name)
	{
		
		select_fitness_equipment(equipment_name);
		driver.findElement(add_to_cart).click();
	}
	
	public void add_fitness_equipment_to_wishlist(String equipment_name)
	{
		
		select_fitness_equipment(equipment_name);
		driver.findElement(add_to_wishlist).click();
	}

	
}
