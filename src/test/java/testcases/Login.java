package testcases;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pages.GetDataFromExcel;
import pages.LoginPage;


public class Login {

	public WebDriver driver;

	LoginPage login = new LoginPage();

	@Test(enabled=false)
	public void LaunchURL()
	{
		
		login.EnterURL("https://magento.softwaretestingboard.com");
	}
	
	@Test(enabled=false)
	public void create_account() throws Exception
	{	
		
		String path = "C:\\Users\\LENOVO\\eclipse-workspace\\ExcelR\\luma_input.xlsx";
		ArrayList<String> data = GetDataFromExcel.getTestData(path, "create_account");
		System.out.println("Data from Excel: "+data);
		try {
			login.create_account(data.get(0),data.get(1),data.get(2),data.get(3));
			boolean account_exist= login.check_account_exist();
			System.out.println("Account exists: "+account_exist);
			if(account_exist)
			{
				String new_email = "abc12345" + Math.random() + "@gmail.com";
				System.out.println(new_email);
				login.create_account(data.get(0), data.get(1), new_email, data.get(3));				
			}

		}
			catch(Exception e)
			{
			System.out.println("Exception");
			}
	}	
	
	@Test
	public void sign_in()
	{
	String path = "C:\\Users\\LENOVO\\eclipse-workspace\\ExcelR\\luma_input.xlsx";
	ArrayList<String> data = GetDataFromExcel.getTestData(path, "login");
	System.out.println("Data from Excel: "+data);
	login.sign_in(data.get(0), data.get(1));
    }
	
	@Test(enabled=false)
	public void sign_out()
	{
		login.sign_out();
	}
	
	@AfterTest
	public void closeBroswer()
	{
		login.closeBrowser();
	}

}
