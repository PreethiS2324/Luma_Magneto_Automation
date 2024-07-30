package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends BaseClass{

	 public LoginPage() {
	        super();
	    }
	 
	By create_account_link = By.linkText("Create an Account");
	By firstname = By.id("firstname");
	By lastname = By.id("lastname");
	By email = By.id("email_address");
	By password = By.id("password");
	By confirm_password= By.id("password-confirmation");
	By create_account_button =  By.xpath("//button[@title='Create an Account']");
	By sign_in = By.xpath("//ul[@class='header links']//a[contains(text(),'Sign In')]");
	By email_id = By.id("email");
	By pass = By.id("pass");
	By sign_in_button = By.xpath("//button[@class='action login primary']");
	By error = By.xpath("//div[@role='alert']/div/div");
	By menu = By.xpath("//button[@class='action switch']");
	By sign_out = By.xpath("//a[contains(text(),'Sign Out')]");

	
	public void EnterURL(String URL)
	{
		driver =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		
	}
	
	public void create_account(String first_name, String last_name, String email_address, String pwd)
	{
		driver.findElement(create_account_link).click();
		driver.findElement(firstname).sendKeys(first_name);
		driver.findElement(lastname).sendKeys(last_name);
		driver.findElement(email).sendKeys(email_address);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(confirm_password).sendKeys(pwd);
		driver.findElement(create_account_button).click();
		
	}
	
	public void sign_in(String email_address, String pwd) {

		driver.findElement(sign_in).click();
		driver.findElement(email_id).sendKeys(email_address);
		driver.findElement(pass).sendKeys(pwd);
		driver.findElement(sign_in_button).click();
	}
	
	public boolean check_account_exist()
	{
		System.out.println("Inside check account exist method");
		String error_message = driver.findElement(error).getText();
		System.out.println("Error message: "+error_message);
		if(error_message.contains("There is already an account with this email address"))
			return true;
		else
			return false;
	}
	
	public void closeBrowser()
	{
		driver.close();
	}
	
	
	public void sign_out()
	{
		driver.findElement(menu).click();
		driver.findElement(sign_out).click();
	}
}
