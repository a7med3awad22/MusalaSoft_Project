package tasks_musalasoft.org.musalasoft;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import reusablecomponents.Propertiesoperations;


public class TestCase2 {
	 static WebDriver driver;
	boolean result = false;
	
	@Test
	public void TC_02() throws Exception {
		// Navigate to the Musala Website
		LaunchBrowserAndNavigate();
		//Clicking the "Company" tab and Ensuring it's loaded
		driver.findElement(By.xpath("//*[@id=\"menu-main-nav-1\"]/li[1]/a")).click();
		if(driver.getCurrentUrl().equals("https://www.musala.com/company/")) 
			result = true;
		else
			result = false;
		Assert.assertTrue(result, "[ERROR: The URL Not Loaded correctly]");
		
		
		//verifying the Leadership Section 
		boolean leadership_section = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/section[3]/div/h2")).isDisplayed();
		if (leadership_section == true)
			result = true;
		else
			result = false;
		Assert.assertTrue(result, "[ERROR: Leadership section not found]");
			
		
		// Store window handle of parent window
		//String currentwindowID = driver.getWindowHandle();
		
		// Clicking on facebook link
		
		driver.findElement(By.id("wt-cli-accept-all-btn")).click();
		Thread.sleep(2000);
		WebElement facebookLink = driver.findElement(By.xpath("/html/body/footer/div/div/a[4]/span"));
		facebookLink.click();
		for(String windowID : driver.getWindowHandles()) 
		{
			String currentURL = driver.switchTo().window(windowID).getCurrentUrl();
			if(currentURL.equals("https://www.facebook.com/MusalaSoft?fref=ts"))
				assertEquals("https://www.facebook.com/MusalaSoft?fref=ts", driver.getCurrentUrl());

		}
		String expected_href = null;
	    Thread.sleep(5000);
	    List<WebElement> profile_img = driver.findElements(By.tagName("body"));
		for(int m = 0; m<profile_img.size(); m++) {			   
		   expected_href = (profile_img.get(m).findElement(By.tagName("image")).getAttribute("xlink:href"));
		}
		if (expected_href != null ) 
				result = true;
			else
				result = false;
			Assert.assertTrue(result, "[ERROR: profile picture not appeared]");
			
		driver.quit();
		}
		
         
		




	public static void LaunchBrowserAndNavigate() throws Exception {
		String browser = Propertiesoperations.getPropertyValueByKey("browser");
		String baseUrl = Propertiesoperations.getPropertyValueByKey("baseUrl");

		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.get(baseUrl);
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
}
