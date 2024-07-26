package pages;


import org.openqa.selenium.WebDriver;
import weddriver_class.DriverManager;

public class BaseClass {

	 protected WebDriver driver;

	    public BaseClass() {
	        this.driver = DriverManager.getDriver();
	    }
}
