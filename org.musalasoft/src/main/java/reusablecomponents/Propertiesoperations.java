package reusablecomponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.opentelemetry.api.internal.StringUtils;

public class Propertiesoperations {
	  static WebDriver driver;
	static Properties prop = new Properties();
	public static String getPropertyValueByKey(String key) throws Exception {
		
		String propFilePath = System.getProperty("user.dir") + "\\src\\test\\java\\configurationfile.properties";
		FileInputStream fis = new FileInputStream(propFilePath);
		prop.load(fis);
		String value = prop.get(key).toString();
		if(StringUtils.isNullOrEmpty(value)) {
			throw new Exception("value is not specified for key: "+key + "in configuration file");
		}
		
		return value;
		}
	
//	public static void LaunchBrowserAndNavigate() throws Exception {
//		String browser = Propertiesoperations.getPropertyValueByKey("browser");
//		String baseUrl = Propertiesoperations.getPropertyValueByKey("baseUrl");
//
//		if(browser.equalsIgnoreCase("chrome")) {
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
//			driver = new ChromeDriver();
//		} else if(browser.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Resources\\geckodriver.exe");
//			driver = new FirefoxDriver();
//		}
//		driver.get(baseUrl);
//	}
}
