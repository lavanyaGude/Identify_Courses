package pageObjects;

import java.io.FileReader;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriver driver=null;
	WebDriverWait mywait=null;
	public static Properties p;
	
	public BasePage(WebDriver driver) throws IOException{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		FileReader propFile=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(propFile);
	}
}
