package testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import weddriver_class.DriverManager;

public class BaseTest {

	 @BeforeSuite
	    public void setUpSuite() {
		    DriverManager.getDriver().manage().window().maximize();
	        DriverManager.getDriver().get("https://magento.softwaretestingboard.com");
	         
	    }
	 
	    @AfterSuite
	    public void tearDownSuite() {
	        DriverManager.quitDriver();
	    }

}
