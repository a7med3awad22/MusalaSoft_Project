package tasks_musalasoft.org.musalasoft;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import reusablecomponents.Propertiesoperations;


public class TestCase1 {
	boolean result = false;
	public static WebDriver driver;
	
	@Test
	public void TC_01() throws Exception {
		LaunchBrowserAndNavigate();
		File file = new File( System.getProperty("user.dir")+"\\src\\test\\java\\tasks_musalasoft\\org\\musalasoft\\testdatafile.txt");
		FileReader fr = new FileReader(file);
		
		BufferedReader reader = new BufferedReader(fr);
		
		
		
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scroll(0,1000)");
	driver.findElement(By.xpath("/html/body/main/section[2]/div/div/div/a[1]/button/span")).click();

	
	for(int i=0 ; i<5 ; i++) {
	String str = reader.readLine();
	driver.findElement(By.xpath("//*[@id=\"cf-1\"]")).sendKeys("Ahmed");
	driver.findElement(By.xpath("//*[@id=\"cf-2\"]")).sendKeys(str);
	driver.findElement(By.xpath("//*[@id=\"cf-3\"]")).sendKeys("01060331791");
	driver.findElement(By.xpath("//*[@id=\"cf-4\"]")).sendKeys("Validation Engineer");
	driver.findElement(By.xpath("//*[@id=\"cf-5\"]")).sendKeys("Hello I am Ahmed Awad and I am Software Engineer");
	Thread.sleep(4000);
	String error_msg = driver.findElement(By.className("wpcf7-not-valid-tip")).getText();
	String actual_error = "The e-mail address entered is invalid.";
	Assert.assertEquals(error_msg, actual_error);
	driver.findElement(By.xpath("//*[@id=\"wpcf7-f875-o1\"]/form/div[2]/p/input")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//*[@id=\"cf-1\"]")).clear();
	driver.findElement(By.xpath("//*[@id=\"cf-2\"]")).clear();
	driver.findElement(By.xpath("//*[@id=\"cf-3\"]")).clear();
	driver.findElement(By.xpath("//*[@id=\"cf-4\"]")).clear();
	driver.findElement(By.xpath("//*[@id=\"cf-5\"]")).clear();
	}
	reader.close();	
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
