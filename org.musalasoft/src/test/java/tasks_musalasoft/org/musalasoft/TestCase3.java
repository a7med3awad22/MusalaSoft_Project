package tasks_musalasoft.org.musalasoft;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import reusablecomponents.Propertiesoperations;


public class TestCase3 extends Propertiesoperations{
	static WebDriver driver;
	boolean result = false;
	
	@Test
	public void TC_03() throws Exception {
		// Navigate to the Musala Website
		LaunchBrowserAndNavigate();
		//Navigate to careers Menu
		driver.findElement(By.xpath("//*[@id=\"menu-main-nav-1\"]/li[5]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/div/section/div/a/button/span")).click();
		Thread.sleep(2000);
		
		if(driver.getCurrentUrl().equals("https://www.musala.com/careers/join-us/")) 
			result = true;
		else
			result = false;
		Assert.assertTrue(result, "[ERROR: the Join us page not opened]");
		
		
		//verifying the Leadership Section 
		WebElement optionList = driver.findElement(By.xpath("//*[@id=\"get_location\"]"));
		Select selectOptions = new Select(optionList);
		//checking non multiple choices and the number of available options
		Assert.assertFalse(selectOptions.isMultiple());
		Assert.assertEquals(9, selectOptions.getOptions().size());
		
		selectOptions.selectByVisibleText("Anywhere");
		Thread.sleep(2000);

		WebElement element = driver.findElement(By.cssSelector("#content > section > div.inner-wraper > article:nth-child(2) > div > a > div > div.back > img"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		
		
		
		String generalDescription = driver.findElement(By.xpath("//*[@id=\"post-1501\"]/div/div[2]/div[1]/div[1]/div[1]/div[2]/h2")).getText();
		Assert.assertEquals(generalDescription, "General description");
		
		String requirements = driver.findElement(By.xpath("//*[@id=\"post-1501\"]/div/div[2]/div[1]/div[1]/div[2]/div[2]/h2")).getText();
		Assert.assertEquals(requirements, "Requirements");
		
		String responsibilitis = driver.findElement(By.xpath("//*[@id=\"post-1501\"]/div/div[2]/div[1]/div[2]/div[1]/div[2]/h2")).getText();
		Assert.assertEquals(responsibilitis, "Responsibilities");
		
		String whatWeOffer = driver.findElement(By.xpath("//*[@id=\"post-1501\"]/div/div[2]/div[1]/div[2]/div[2]/div[2]/h2")).getText();
		Assert.assertEquals(whatWeOffer, "What we offer");
		

		boolean applyBtn = driver.findElement(By.xpath("//*[@id=\"post-1501\"]/div/div[2]/div[2]/a/input")).isDisplayed();
		if (applyBtn == true)
			result = true;
		else
			result = false;
		Assert.assertTrue(result, "[ERROR: apply button not appeared]");
	
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,2000)");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#post-1501 > div > div:nth-child(3) > div.btn-apply-container > a > input")).click();
		
		
		// applying negative data
		String linkDocument = System.getProperty("user.dir")+"\\src\\test\\java\\tasks_musalasoft\\org\\musalasoft\\cvdocument.txt";
		driver.findElement(By.xpath("//*[@id=\"cf-1\"]")).sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"cf-2\"]")).sendKeys("ahmed@test");
		driver.findElement(By.xpath("//*[@id=\"cf-3\"]")).sendKeys("01060331791");
		driver.findElement(By.xpath("//*[@id=\"cf-4\"]")).sendKeys(linkDocument);
		Thread.sleep(4000);
		

		String error_msg_name_actual = driver.findElement(By.cssSelector("#wpcf7-f880-o1 > form > p:nth-child(4) > span > span")).getText();
		String error_msg_name_expected = "The field is required.";
		Assert.assertEquals(error_msg_name_actual, error_msg_name_expected);
		Thread.sleep(2000);
		
		
		
		String error_msg_Email_actual = driver.findElement(By.cssSelector("#wpcf7-f880-o1 > form > p:nth-child(5) > span > span")).getText();
		String error_msg_Email_expected = "The e-mail address entered is invalid.";
		Assert.assertEquals(error_msg_Email_actual, error_msg_Email_expected);
		Thread.sleep(2000);

		
		
		WebElement check1 = driver.findElement(By.xpath("//*[@id=\"adConsentChx\"]"));
		check1.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"wpcf7-f880-o1\"]/form/div[4]/p/input")).click();
		
		
		Thread.sleep(5000);
		String error_msg_final_actual = driver.findElement(By.cssSelector("#wpcf7-f880-o1 > form > div.message-form > div > div")).getText();
		String error_msg_final_expected = "One or more fields have an error. Please check and try again.";
		Thread.sleep(2000);
		Assert.assertEquals(error_msg_final_actual, error_msg_final_expected);
		
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
	}
}
