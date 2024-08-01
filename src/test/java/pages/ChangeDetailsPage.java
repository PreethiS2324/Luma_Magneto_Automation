package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class ChangeDetailsPage extends BaseClass {

	public ChangeDetailsPage()
	{
		super();
	}
	
	By menu = By.xpath("//button[@class='action switch']");
	By my_account_tab = By.xpath("//a[contains(text(),'My Account')]");
	By my_account = By.xpath("//li/strong[contains(text(),'My Account')]");
	By edit_contact = By.xpath("//div[@class='box box-information']//span[contains(text(),'Edit')]");
	By change_password = By.xpath("//div[@class='box-actions']//a[contains(text(),'Change Password')]");
	By manage_address = By.xpath("//span[contains(text(),'Manage Addresses')]");
	By change_billing_address = By.xpath("//span[contains(text(),'Change Billing Address')]");
	By change_shipping_address = By.xpath("//span[contains(text(),'Change Shipping Address')]");
	By save_address = By.xpath("//span[contains(text(),'Save Address')]");
	By street_address = By.xpath("//input[@id='street_1']");
	By city = By.name("city");
	By state_dropdown = By.xpath("//select[@name='region_id']");
	By postcode  = By.name("postcode");
	By country_dropdown = By.xpath("//select[@name='country_id']");
	By phoneNumber = By.name("telephone");
	By firstname = By.id("firstname");
	By lastname = By.id("lastname");
	By save_button = By.xpath("//span[contains(text(),'Save')]");
	By email = By.id("email");
	By change_email = By.xpath("//input[@name='change_email']");
	By current_pwd= By.id("current-password");
	By new_pwd = By.id("password");
	By confirm_new_pwd = By.id("password-confirmation");
	
	ConfigReader configReader = new ConfigReader();

	String new_street = configReader.getProperty("new_street");
	String new_city = configReader.getProperty("new_city");
	String new_state = configReader.getProperty("new_state");
	String new_country = configReader.getProperty("new_country");
	String new_pincode = configReader.getProperty("new_pincode");
	String new_phoneNumber = configReader.getProperty("new_phoneNumber");

     public void add_address() {
		
    	driver.findElement(street_address).click();
		driver.findElement(street_address).sendKeys(new_street);
		Select sel_country = new Select(driver.findElement(country_dropdown));
		sel_country.selectByVisibleText(new_country);
		Select sel_state = new Select(driver.findElement(state_dropdown));
		sel_state.selectByVisibleText(new_state);
		driver.findElement(city).clear();
		driver.findElement(city).sendKeys(new_city);
		driver.findElement(postcode).clear();
		driver.findElement(postcode).sendKeys(new_pincode);
		driver.findElement(phoneNumber).clear();
		driver.findElement(phoneNumber).sendKeys(new_phoneNumber);
		driver.findElement(save_address).click();
		
    }
       
     public void edit_account_name(String first_name,String last_name)
     {
    	 driver.findElement(menu).click();
    	 driver.findElement(my_account_tab).click();
    	 driver.findElement(edit_contact).click();
    	 driver.findElement(firstname).clear();
    	 driver.findElement(firstname).sendKeys(first_name);
    	 driver.findElement(lastname).clear();
    	 driver.findElement(lastname).sendKeys(last_name);
    	 driver.findElement(save_button).click();
    	 
     }
     
     public void change_email(String email_address)
     {
    	 driver.findElement(menu).click();
    	 driver.findElement(my_account_tab).click();
    	 driver.findElement(edit_contact).click();
    	 driver.findElement(change_email).click();
    	 driver.findElement(email).clear();
    	 driver.findElement(email).sendKeys(email_address);
    	 driver.findElement(save_button).click();
    	 
     }
     
     public void change_password(String current_password,String new_password)
	
     {
    	 driver.findElement(menu).click();
    	 driver.findElement(my_account_tab).click();
    	 driver.findElement(change_password).click();
    	 driver.findElement(current_pwd).sendKeys(current_password);
    	 driver.findElement(new_pwd).sendKeys(new_password);
    	 driver.findElement(confirm_new_pwd).sendKeys(new_password);
    	 driver.findElement(save_button).click();
    	 
     }
     
     
     public void change_billing_address() throws InterruptedException
     {
    	 driver.findElement(menu).click();
    	 driver.findElement(my_account_tab).click();
    	 Thread.sleep(2000);
    	 driver.findElement(manage_address).click();
    	 driver.findElement(change_billing_address).click();
    	 add_address();
     }
     
     public void change_shipping_address() throws InterruptedException
     {
    	 driver.findElement(menu).click();
    	 driver.findElement(my_account_tab).click();
    	 Thread.sleep(2000);
    	 driver.findElement(manage_address).click();
    	 driver.findElement(change_shipping_address).click();
    	 add_address();
 
     }
}
