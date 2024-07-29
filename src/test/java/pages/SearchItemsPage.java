package pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

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
	By add_to_wishlist = By.xpath("//span[contains(text(),'Add to Wish List')]");	
	By quantity_to_order = By.xpath("//input[@class='input-text qty']");
	By update_cart = By.xpath("//span[contains(text(),'Update Shopping Cart')]");
	By available_colors= By.xpath("//div[@class='swatch-attribute color']//div[@class='swatch-option color']");
	By next_page = By.xpath("//li[@class='item pages-item-next']");
	By no_of_pages = By.xpath("//div[@class='pages']//li[@class='item' or @class='item current']");
	
	ConfigReader configReader = new ConfigReader();
	String item_type = configReader.getProperty("item_type");
	OrderItemsPage order = new OrderItemsPage();
	
	public void add_item_to_cart(String item, String item_name, String item_type,String size, String color, String quant)
		{
		order.select_tops_bottoms(item_name, item_type,size, color, quant);
		driver.findElement(add_to_cart).click();
		}
	
	public void order_item_from_menu(String gender, String category, String item_name, String size, String color, String quant,Address address) throws Exception
	{
		if(gender.equals("Male"))
			driver.findElement(By.xpath("//div[@class='section-items nav-sections-items']//nav[@class='navigation']/ul/li[3]")).click();
		else
			driver.findElement(By.xpath("//div[@class='section-items nav-sections-items']//nav[@class='navigation']/ul/li[1]")).click();
		
		if(category.equals("Tops"))
			driver.findElement(men_tops).click();
			
		else
			driver.findElement(men_bottoms).click();
		
		select_item_details(item_name,size,color,quant);
		driver.findElement(add_to_cart).click();
		
		checkout_an_item_in_cart(item_name,address);
		
		
		}
	
	
	public void select_item_details(String item_name, String size, String color, String quant)
	{
		try {

			driver.findElement(By.xpath("//div[@option-label='"+size+"']")).click();	
			int colors = driver.findElements(By.xpath("//div[@class='swatch-attribute color']//div[@class='swatch-option color']")).size();
			System.out.println("Colors count: "+colors);
			int color_1=0;
			for(int i=1;i<=colors;i++)
			{
				String get_color = driver.findElement(By.xpath("//div[@option-label='"+color+"']")).getText();
				System.out.println("color: "+get_color);
				if(get_color.equals(color))
				{
					driver.findElement(By.xpath("//div[@option-label='"+color+"']")).click();
					color_1=1;
					break;
				}
				else
					continue;			
			}
			if(color_1==0)
				Assert.fail("Color is not available");       
			driver.findElement(quantity).clear();
			driver.findElement(quantity).sendKeys(quant);
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
		driver.findElement(next_button).click();
		Thread.sleep(3000);
		driver.findElement(place_order_button).click();
		Thread.sleep(2000);
		boolean order_placed = driver.findElement(thanks_message).isDisplayed();
		assertTrue(order_placed,"Order Placed Successfully");
		
	}
		public void add_adress(Address address) {
		
		driver.findElement(street_address1).sendKeys(address.getAddress());
		Select sel_country = new Select(driver.findElement(country_dropdown));
		sel_country.selectByVisibleText(address.getCountry());
		Select sel_state = new Select(driver.findElement(state_dropdown));
		sel_state.selectByVisibleText(address.getState());
		driver.findElement(city).sendKeys(address.getCity());
		driver.findElement(postcode).sendKeys(address.getPincode());
		driver.findElement(phoneNumber).sendKeys(address.getPhoneNumber());
		
    
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
		checkout_allitems_in_cart(address);
	}

	
	public void add_item_to_wishlist(String item, String item_name, String size, String color, String quant)
	{
	driver.findElement(search_box).sendKeys(item);
	driver.findElement(search_button).click();
	select_item_details(item_name, size, color, quant);
	driver.findElement(add_to_wishlist).click();
	}
	
}
