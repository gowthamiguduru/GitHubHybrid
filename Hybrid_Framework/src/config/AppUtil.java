package config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class AppUtil {
	//global statitc object and reuse another class
	public static Properties conpro;
	public static WebDriver driver;
	@BeforeSuite
	public static void setup()throws Throwable

	{
		conpro=new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))

		{
			driver= new ChromeDriver();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		}
		else if(conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			driver.get(conpro.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		}
		else
		{
			Reporter.log("Browser value not Matching",true);
		}

	}
	@AfterSuite
	public static void tearDown()
	{
		driver.quit();
	}
}



