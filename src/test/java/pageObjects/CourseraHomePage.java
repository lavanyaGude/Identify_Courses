package pageObjects;

import java.io.IOException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class CourseraHomePage extends BasePage{
	
	public CourseraHomePage(WebDriver driver) throws IOException {
		super(driver);
		
	}
	
	
	// @CacheLookup This annotation, when applied over a WebElement, instructs Selenium to keep a 
	//cache of the WebElement instead of searching for the WebElement every time from the WebPage. 
	@FindBy(xpath="//input[@placeholder='What do you want to learn?']") @CacheLookup 
	private WebElement searchBox;
	//search button
	@FindBy(xpath="//button[@class='nostyle search-button']//div[@class='magnifier-wrapper']") @CacheLookup
	private WebElement submitSearch;

    // beginers level check box
	@FindBy(xpath="//*[@id=\"search-results-header-wrapper\"]/div/div/div/div[4]/div/div/div[1]/label") @CacheLookup 
	private WebElement beginerLevelCheckBox;
	// english check box
	@FindBy(xpath="//*[@id=\"search-results-header-wrapper\"]/div/div/div/div[2]/div/div/div[1]/label/span") @CacheLookup 
	private WebElement englishLanguageCheckBox;
	
	//after beginer and english click on first course and title of course 
	@FindBy(xpath="//h1[@data-e2e='hero-title']") @CacheLookup
	private WebElement course1Title;
	//rating
	@FindBy(xpath="//main/section[2]/div/div/div[2]/div/div/section/div[2]/div/div") @CacheLookup
	private WebElement rating1;
	
	@FindBy(xpath="//main/section[2]/div/div/div[1]/div[2]/section/div[2]/div[3]/div[1]") @CacheLookup
	private WebElement duration;
	
	@FindBy(xpath="//*[@id=\"rendered-content\"]/div/div/main/div[1]/div/div/div/div/div[2]/ul/li[1]/div/div/div/div/div/div[1]/div/img") @CacheLookup
	private WebElement firstCourse;
	
	@FindBy(xpath="//*[@id=\"rendered-content\"]/div/div/main/div[1]/div/div/div/div/div[2]/ul/li[2]/div/div/div/div/div/div[1]/div/img") @CacheLookup
	private WebElement secondCourse;
	
	By languagebtn=By.xpath("//button[contains(text(),\"Language: English\")]") ;
	By levelbtn=By.xpath("//button[contains(text(),\"Beginner\")]");
	By allCourses=By.cssSelector("li.cds-9");
	By dropDown=By.xpath("//button[@data-e2e=\"megamenu-explore-button\"]/span");
	
	public void searchCourse() {
		mywait.until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(p.getProperty("searchCategory"));
		submitSearch.click();
	}
	public void selectLanguage() {
		englishLanguageCheckBox.click();
	}
	public void selectLevel() {
		beginerLevelCheckBox.click();
	}
	public void scrollToCourse() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
	}
	public void selectFirstCourse() throws InterruptedException{
		mywait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(languagebtn));
		
		mywait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(levelbtn));
		mywait.until(ExpectedConditions.elementToBeClickable(firstCourse));
		try{
			driver.findElement(By.cssSelector("li.cds-9")).click();
		}catch(Exception e) {
			System.out.println("exception occured");
		}

	}
	
	public WebDriver switchToParentWindow() {

		Set<String> se=driver.getWindowHandles();
		List<String> ids=new ArrayList<String>(se);
		String parent=ids.get(0);
		driver.switchTo().window(parent);
		return driver;
	}
	public void selectSecondCourse(){
		
		Set<String> se=driver.getWindowHandles();
		List<String> ids=new ArrayList<String>(se);
//		System.out.println(ids);
		driver.switchTo().window(ids.get(0));	
		mywait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(languagebtn));
		mywait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(levelbtn));
		try{
			List<WebElement> elements=driver.findElements(allCourses);
			elements.get(1).click();
		}catch(Exception e) {
			System.out.println("exception occured");
		}
	}
	public void changeDriver() {
	
		Set<String> se=driver.getWindowHandles();
		List<String> ids=new ArrayList<String>(se);
		String child=ids.get(1);
		driver.switchTo().window(child);
		
	}
	public void clickOnDropDown() {
		driver.findElement(dropDown).click();
	}
	
	public void closeSurvey() {
		WebDriverWait mm=new WebDriverWait(driver,Duration.ofSeconds(3));
		try {
		mm.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id=\"surveyModal~title\"]")));
		driver.findElement(By.xpath("//button[contains(text(),'No thanks')]")).click();
		}catch(Exception e) {
			
		}
	}
	
}
