package pageObjects;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utilities.ExcelUtils;

public class DropDownElements extends BasePage {

	public DropDownElements(WebDriver driver) throws IOException {
		super(driver);
	}

	By language_learning = By.xpath("//header/div/div/div[2]/div[1]/div[2]/div/div/nav/div/div/div[1]/div/div/div[2]/ul/li[5]");
	By allLanguages = By.xpath("//ul[@aria-labelledby=\"Language-Learning-tab-Popular-skills-title\"]/li[1]/div/a");
	By levels = By.xpath("//div[@data-testid=\"search-filter-group-Level\"]/div/div/div/label/div/span/span");
	By levelCount = By.xpath("//div[@data-testid=\"search-filter-group-Level\"]/div/div/div/label/div/span/span/span");
	By showmore = By.xpath("//div[@data-testid=\"search-filter-group-Language\"]/div[2]/button/span");
	By boxCheck = By.xpath("//div[@data-testid=\"scroll-container\"]/div/h2");
	
	By allLanguageCheckBoxes = By.xpath("//div[@id=\"checkbox-group\"]/div/label/div/span/span");
	By close = By.xpath("//div[@aria-labelledby=\"checkbox-group\"]/div/button/span");

	public void clickOnLanguageLearning() {
		mywait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[@data-e2e='megamenu-item~language-learning']"))).click();

	}

	public void clickOnAllLanguages() {
		mywait.until(ExpectedConditions.visibilityOf(driver.findElement(allLanguages))).click();
	}


	public void verifyLanguagePage() {
		String s = mywait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[@data-e2e=\"NumberOfResultsSection\"]/span")))
				.getText();
		if (s.contains("Language Courses")) {
			System.out.println("page loaded");
		}
	}

	

	public List<String> getCount() throws InterruptedException, IOException {
		List<WebElement> lev=driver.findElements(levels);
		
		JavascriptExecutor jsp=(JavascriptExecutor)driver;
		jsp.executeScript("arguments[0].scrollIntoView(false);",lev.get(3));
		List<String> courseCount=new ArrayList<String>();
		for(int i=0;i<lev.size();i++) {
			Map<String,String> map=new HashMap<String,String>();
			System.out.println("----------------");
			String temp=lev.get(i).getText();
			System.out.println(temp);
			courseCount.add(temp);
			lev.get(i).click();
			jsp.executeScript("arguments[0].scrollIntoView(false);",driver.findElement(showmore));
			driver.findElement(showmore).click();
			try {
				if(driver.findElement(boxCheck).getText().contains("Language")){
					List<WebElement> ll=driver.findElements(allLanguageCheckBoxes);
					for(WebElement each:ll) {
						String languagecheckbox=each.getText();
						System.out.println(languagecheckbox);
						courseCount.add(languagecheckbox);
						}
				}
			}catch(Exception e) {
				List<WebElement> ll=driver.findElements(allLanguageCheckBoxes);
				for(WebElement each:ll) {
					String languagecheckbox=each.getText();
					System.out.println(languagecheckbox);
					courseCount.add(languagecheckbox);
				}
				
			}
			driver.findElement(close).click();
			jsp.executeScript("arguments[0].scrollIntoView(false);",lev.get(3));
			lev.get(i).click();
		}
		ExcelUtils.writeData("Course_Count", courseCount, 0, 0);
		return courseCount;
		
	}
}
