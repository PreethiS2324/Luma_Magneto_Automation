package testcases;

import java.util.ArrayList;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pages.ConfigReader;
import pages.GetDataFromExcel;
import pages.LoginPage;


public class Login extends BaseTest{

	LoginPage login = new LoginPage();
	ConfigReader configReader = new ConfigReader();


	@Test(enabled=false)
	public void create_account() throws Exception
	{	
		
		String path = configReader.getProperty("filePath");
		String sheetName = configReader.getProperty("sheetName1");
		ArrayList<String> data = GetDataFromExcel.getTestData(path, sheetName);
		try {
			login.create_account(data.get(0),data.get(1),data.get(2),data.get(3));
			boolean account_exist= login.check_account_exist();
			if(account_exist)
			{
				String new_email = "abc12345" + Math.random() + "@gmail.com";
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
		String path = configReader.getProperty("filePath");
		String sheetName = configReader.getProperty("sheetName1");
		ArrayList<String> data = GetDataFromExcel.getTestData(path, sheetName);
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
