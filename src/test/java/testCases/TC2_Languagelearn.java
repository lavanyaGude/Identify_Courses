package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.CourseraHomePage;
import pageObjects.DropDownElements;
import testBase.ReusableComponents;

public class TC2_Languagelearn extends ReusableComponents{
	
	 DropDownElements obj3;
	 CourseraHomePage chp;
	
	@Test(priority=1,groups= {"sanity"})
	public void searchbox() throws InterruptedException, IOException {
		logger.info("------Clcking on explore button and selecting Language learning option--------");
		obj3 = new DropDownElements (driver);
		chp=new CourseraHomePage(driver);
		chp.clickOnDropDown();
		obj3.clickOnLanguageLearning();
		obj3.clickOnAllLanguages();
		obj3.verifyLanguagePage();
		logger.info("------ shows the language learn courses--------");
	}
	@Test(priority=2,groups= {"sanity","regression"})
	public void languagescourses() throws IOException, InterruptedException {
		logger.info("------For each Level it will count the number of courses available for different languages --------");
		obj3.getCount();
		logger.info("------data taken, prints on console and also written on excel file------");
	}
	
	
}
