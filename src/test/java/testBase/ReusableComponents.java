package testBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class ReusableComponents {

	public static WebDriver driver;
	
	public static Logger logger;
	public ReusableComponents() {
		
	}
	@BeforeClass(groups= {"sanity","regression"})
	@Parameters({"browser"})
	public void InitializeBrowser(String br) throws IOException {
		if(br.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(br.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}else {
			System.out.println(br+" browser is not available");
			return ;
		}
		logger=LogManager.getLogger(this.getClass());
		driver.get("https://www.coursera.org/");
		driver.manage().window().maximize();
	}
	@AfterClass(groups= {"sanity","regression"})
	public void closeBrowser() throws IOException {
		driver.quit();
	}
public static String captureScreen( String f) 
	
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+"_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

	
}
