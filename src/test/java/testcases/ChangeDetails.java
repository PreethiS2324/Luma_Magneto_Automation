package testcases;

import org.testng.annotations.Test;

import pages.ChangeDetailsPage;
import pages.ConfigReader;
import pages.LoginPage;

public class ChangeDetails extends BaseTest{

	ChangeDetailsPage changedetails = new ChangeDetailsPage();
	ConfigReader configReader = new ConfigReader();
	LoginPage login = new LoginPage();
	
	@Test
	public void change_account_name_TC013()
	{
		String firstname = configReader.getProperty("new_firstname");
		String lastname = configReader.getProperty("new_lastname");
		changedetails.edit_account_name(firstname, lastname);
	}
	
	@Test(enabled=false)
	public void change_email_TC014()
	{
		String email = configReader.getProperty("new_email");
		changedetails.change_email(email);
	}
	
	@Test(enabled=false)
	public void change_password_TC015()
	{
		String password = configReader.getProperty("new_password");
		changedetails.change_email(password);
	}
	
	@Test
	public void change_billing_address_TC016() throws InterruptedException
	{
		changedetails.change_billing_address();
	}
	
	@Test
	public void change_shipping_address_TC017() throws InterruptedException
	{
		changedetails.change_shipping_address();
	}
}
