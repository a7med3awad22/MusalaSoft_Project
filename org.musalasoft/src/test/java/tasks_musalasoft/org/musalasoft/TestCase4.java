package tasks_musalasoft.org.musalasoft;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import reusablecomponents.Propertiesoperations;


public class TestCase4 extends Propertiesoperations{
	static WebDriver driver;
	boolean result = false;
	
	@Test
	public void TC_04() throws Exception {
		// Navigate to the Musala Website
		LaunchBrowserAndNavigate();
		//Go to careers 
		driver.findElement(By.xpath("//*[@id=\"menu-main-nav-1\"]/li[5]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/div/section/div/a/button/span")).click();
		Thread.sleep(4000);
		
		// choosing the sofia city
		WebElement optionList_sofia = driver.findElement(By.xpath("//*[@id=\"get_location\"]"));
		Select selectOptions = new Select(optionList_sofia);
		
		selectOptions.selectByVisibleText("Sofia");
		Thread.sleep(3000);
		

		List<WebElement> listpos_sofia = driver.findElements(By.tagName("article"));
		System.out.println("sofia");
		for(int m = 0; m<listpos_sofia.size(); m++) {			   
			    System.out.println("Position:" + listpos_sofia.get(m).findElement(By.tagName("h2")).getAttribute("data-alt"));
			    System.out.println("More info:" + listpos_sofia.get(m).findElement(By.tagName("a")).getAttribute("href"));
			}
		// choosing Skopje city
		WebElement optionList_Skopje = driver.findElement(By.xpath("//*[@id=\"get_location\"]"));
		Select selectOptions_2 = new Select(optionList_Skopje);
		selectOptions_2.selectByVisibleText("Skopje");
		Thread.sleep(4000);
		
		List<WebElement> listpos_Skopje = driver.findElements(By.tagName("article"));
		System.out.println("Skopje");
		for(int m = 0; m<listpos_Skopje.size(); m++) {			   
			    System.out.println("Position:" + listpos_Skopje.get(m).findElement(By.tagName("h2")).getAttribute("data-alt"));
			    System.out.println("More info:" + listpos_Skopje.get(m).findElement(By.tagName("a")).getAttribute("href"));
			}
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
