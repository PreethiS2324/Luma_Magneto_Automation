package pages;

import org.openqa.selenium.By;

public class OrderWatchesPage extends BaseClass{

	public OrderWatchesPage()
	{
		super();	
		
	}
	SearchItemsPage search = new SearchItemsPage();
	By search_box = By.id("search");
	By search_button = By.xpath("//button[@title='Search']");
	By add_to_cart = By.xpath("//button[@title='Add to Cart']");
	By add_to_wishlist = By.xpath("//span[contains(text(),'Add to Wish List')]");
	
	public void add_watch_to_cart(String watch_name)
	{
		driver.findElement(search_box).sendKeys("watch");
		driver.findElement(search_button).click();
		driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li//a[contains(text(),'"+watch_name+"')]")).click();
		driver.findElement(add_to_cart).click();
		}
	
	public void order_watch(String watch_name,Address address) throws InterruptedException 
	{
		search.checkout_an_item_in_cart(watch_name,address);
	
	}
	
	public void add_watch_to_wishlist(String watch_name)
	{
		driver.findElement(search_box).sendKeys("watch");
		driver.findElement(search_button).click();
		driver.findElement(By.xpath("//div[@class='products wrapper grid products-grid']/ol/li//a[contains(text(),'"+watch_name+"')]")).click();
		driver.findElement(add_to_wishlist).click();
	}
}
	
