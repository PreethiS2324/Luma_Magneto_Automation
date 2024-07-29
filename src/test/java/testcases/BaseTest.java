package testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import pages.ConfigReader;
import weddriver_class.DriverManager;

public class BaseTest {

	 @BeforeSuite
	    public void setUpSuite() {
		 ConfigReader configReader = new ConfigReader();
		    DriverManager.getDriver().manage().window().maximize();
		    String app_url = configReader.getProperty("URL");
		    System.out.println("URL from config file : "+app_url);
	        DriverManager.getDriver().get(app_url);
	       
	    }
	 
	    @AfterSuite
	    public void tearDownSuite() {
	        DriverManager.quitDriver();
	    }

}
