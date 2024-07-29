package testcases;

import org.testng.annotations.Test;

import pages.ChangeDetailsPage;
import pages.ConfigReader;

public class ChangeDetails extends BaseTest{

	ChangeDetailsPage changedetails = new ChangeDetailsPage();
	ConfigReader configReader = new ConfigReader();
	
	@Test
	public void change_account_name()
	{
		String firstname = configReader.getProperty("new_firstname");
		String lastname = configReader.getProperty("new_lastname");
		changedetails.edit_account_name(firstname, lastname);
	}
	
	@Test
	public void change_email()
	{
		String email = configReader.getProperty("new_email");
		changedetails.change_email(email);
	}
	
	@Test
	public void change_password()
	{
		String password = configReader.getProperty("new_password");
		changedetails.change_email(password);
	}
	
	@Test
	public void change_billing_address()
	{
		changedetails.change_billing_address();
	}
	
	@Test
	public void change_shipping_address()
	{
		changedetails.change_shipping_address();
	}
}
