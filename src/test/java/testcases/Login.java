package testcases;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pages.ConfigReader;
import pages.LoginPage;


public class Login extends BaseTest{

	LoginPage login = new LoginPage();
	ConfigReader configReader = new ConfigReader();
	String email = configReader.getProperty("email");
	String password = configReader.getProperty("password");
	String firstname = configReader.getProperty("firstname");
	String lastname = configReader.getProperty("lastname");

	@Test(enabled=false)
	public void create_account_TC001() throws Exception
	{	
		try {
			login.create_account(firstname,lastname,email,password);
			boolean account_exist= login.check_account_exist();
			if(account_exist)
			{
				String new_email = "abc12345" + Math.random() + "@gmail.com";
				login.create_account(firstname, lastname, new_email, password);				
			}

		}
			catch(Exception e)
			{
			System.out.println("Exception");
			}
	}	
	
	@Test
	public void sign_in_TC002()
	{
		login.sign_in(email,password);
    }
	
	@Test(enabled=false)
	public void sign_out_TC003()
	{
		login.sign_out();
	}
	
	@AfterTest
	public void closeBroswer()
	{
		login.closeBrowser();
	}

}
