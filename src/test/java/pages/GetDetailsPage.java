package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GetDetailsPage extends BaseClass {

	public GetDetailsPage()
	{
		super();
	}

	By menu = By.xpath("//button[@class='action switch']");
	By my_account_tab = By.xpath("//a[contains(text(),'My Account')]");
	By my_account = By.xpath("//li/strong[contains(text(),'My Account')]");
	By account_info = By.xpath("//div[@class='block block-dashboard-info']//div[@class='box-content']/p");
	By my_orders = By.xpath("//li/strong[contains(text(),'My Orders')]");
	By my_wishlist = By.xpath("//li/strong[contains(text(),'My Wish List')]");
	By orders_items = By.xpath("//table[@id='my-orders-table']//tbody/tr");
	By total_items_in_wishlist = By.xpath("//ol[@class='product-items']/li");
	By cart = By.xpath("//a[@class='action showcart']");
	By items_in_cart = By.xpath("//ol[@id='mini-cart']//li//strong/a");
	By sale_menu = By.xpath("//a[@role='menuitem']/span[contains(text(),'Sale')]");
	By cart_list = By.xpath("//ol[@id='mini-cart']/li");
	By cart_price = By.xpath("//span[@class='price-wrapper']/span");
	By no_items_in_cart = By.xpath("//strong[contains(text(),'You have no items in your shopping cart.')]");
	
	
	  public String get_account_name_and_email() throws InterruptedException
	     {
	    	Thread.sleep(2000);
		  driver.findElement(menu).click();
	    	 driver.findElement(my_account_tab).click();
	    	 String name_email =driver.findElement(account_info).getText();
			 return name_email;
	     }
	  
	  public ArrayList<String> get_ordered_items() throws InterruptedException
	  {
		  Thread.sleep(2000);
		  driver.findElement(menu).click();
		  driver.findElement(my_account_tab).click();
		  Thread.sleep(2000);
		  driver.findElement(my_orders).click();
		  int total_orders = driver.findElements(orders_items).size();
		  ArrayList<String> orders = new ArrayList<String>();
		  for(int i =1;i<=total_orders;i++)
		  {
			  String order_id = driver.findElement(By.xpath("//table[@id='my-orders-table']//tbody/tr["+i+"]/td[1]")).getText();
			  orders.add(order_id);
		  }
			 
		return orders;
		  
	  }
	  
	  public ArrayList<String> get_items_in_wishlist() throws InterruptedException
	  {
		  Thread.sleep(2000);
		  driver.findElement(menu).click();
		  ArrayList<String> wishlist = new ArrayList<String>();
		  driver.findElement(my_account_tab).click();
		  Thread.sleep(2000);
		  driver.findElement(my_wishlist).click();
		  int total = driver.findElements(total_items_in_wishlist).size();
		  for(int i=1;i<=total;i++)
		  {
			 String item= driver.findElement(By.xpath("//ol[@class='product-items']/li["+i+"]//strong[@class='product-item-name']/a")).getText();
			 wishlist.add(item);
		  }
		return wishlist;
	}
	  public ArrayList<String> items_in_cart() throws Exception
		{
			Thread.sleep(2000);
			driver.findElement(cart).click();
			List<WebElement> items = driver.findElements(items_in_cart);
			ArrayList<String> item_names = new ArrayList<String>();
			for(int i=1; i<=items.size();i++)
			{
				String item1 = driver.findElement(By.xpath("//ol[@id='mini-cart']//li["+i+"]//strong/a[1]")).getText();
				item_names.add(item1);
				
			}
			return item_names;
		}
	  
	  
	  public ArrayList<String> get_women_deals() throws InterruptedException
	  {
		  Thread.sleep(2000);
		  driver.findElement(sale_menu).click();
		  int total = driver.findElements(By.xpath("//div[@class='categories-menu']//ul[1]/li")).size();
		  ArrayList<String> women_deals = new ArrayList<String>();
		  for(int i=1;i<=total;i++)
		  {
			  String item = driver.findElement(By.xpath("//div[@class='categories-menu']//ul[1]/li["+i+"]")).getText();
			  System.out.println("Item: "+item);
			  women_deals.add(item);
		  }
		return women_deals;
		  
	  }
	  
	  
	  public ArrayList<String> get_men_deals() throws InterruptedException
	  {
		  Thread.sleep(2000);
		  driver.findElement(sale_menu).click();
		  int total = driver.findElements(By.xpath("//div[@class='categories-menu']//ul[2]/li")).size();
		  ArrayList<String> men_deals = new ArrayList<String>();
		  for(int i=1;i<=total;i++)
		  {
			  String item = driver.findElement(By.xpath("//div[@class='categories-menu']//ul[2]/li["+i+"]")).getText();
			  men_deals.add(item);
		  }
		return men_deals;
		  
	  }
	  
	  public ArrayList<String> get_gear_deals()
	  {
		  driver.findElement(sale_menu).click();
		  int total = driver.findElements(By.xpath("//div[@class='categories-menu']//ul[3]/li")).size();
		  ArrayList<String> gear_deals = new ArrayList<String>();
		  for(int i=1;i<=total;i++)
		  {
			  String item = driver.findElement(By.xpath("//div[@class='categories-menu']//ul[3]/li["+i+"]")).getText();
			  gear_deals.add(item);
		  }
		return gear_deals;
		  
	  }
	  
	  
	  public void remove_all_items_from_cart() throws InterruptedException
	  {
		  Thread.sleep(2000);
		  driver.findElement(cart).click();
		  int total_items_in_cart = driver.findElements(items_in_cart).size();
		  for(int i=1;i<=total_items_in_cart;i++)
		  {
			  driver.findElement(By.xpath("//ol[@id='mini-cart']/li["+i+"]//div[@class='product actions']//a[@title='Remove item']")).click();
		  }
	  }
	  
	  public String get_cart_subtotal()
	  {
		driver.findElement(cart).click();
		String cart_subtotal = null;
		boolean cart_item = driver.findElement(no_items_in_cart).isDisplayed();
		if(!cart_item)
			cart_subtotal = driver.findElement(cart_price).getText();	
		 return cart_subtotal;
		  
	  }
}
