package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CourseraHomePage;
import pageObjects.SearchResult;
import testBase.ReusableComponents;
import utilities.ExcelUtils;


public class TC1_CourseraSearchWebDev extends ReusableComponents {
	CourseraHomePage ch;
	SearchResult res;
	

	@Test(priority = 0,groups= {"sanity"})
	public void searchForCourses() throws IOException, InterruptedException {
		ch = new CourseraHomePage(driver);
		res = new SearchResult(driver);

		logger.info("------Searching for web development courses  --------");
		ch.searchCourse();
		logger.info("------shows the available web devolpment courses  --------");

	}

	@Test(priority = 1,groups= {"sanity","regression"})
	public void Applyfilters() throws InterruptedException {
		logger.info("------clicking on the language(English) and Level(Beginner) checkboxes--------");
		ch.selectLanguage();
		Thread.sleep(3000);
		ch.selectLevel();
		logger.info("------filtered courses is shown -------");
	}

	@Test(priority = 2,groups= {"sanity","regression"})
	public void selectFirstCourse() throws InterruptedException {
		logger.info("------Clicking on the first course--------");
		ch.scrollToCourse();
		ch.selectFirstCourse();
		logger.info("------First course details page shown--------");
	}

	@Test(priority = 3,groups= {"sanity","regression"})
	public void getFirstCourseData() throws InterruptedException {
		logger.info("------Collectiong the title,rating,duration of First Course--------");
		try {
			ch.changeDriver();
			Thread.sleep(5000);
			String courseTitle1 = res.getCourseTitle();
			ExcelUtils.writeData("Web Courses", "Course - 1 Title", 0, 0);
			ExcelUtils.writeData("Web Courses", courseTitle1, 0, 1);
			
			String courseRating1 = res.getRating();
			ExcelUtils.writeData("Web Courses", "Course - 1 Rating", 1, 0);
			ExcelUtils.writeData("Web Courses",  courseRating1, 1, 1);
			int duration = res.getDuration();
			ExcelUtils.writeData("Web Courses", "Course - 1 duration in hours", 2, 0);
			ExcelUtils.writeData("Web Courses",  String.valueOf(duration), 2, 1);
			System.out.println(duration);
			System.out.println(courseTitle1);
			System.out.println(courseRating1);
			Assert.assertTrue(true);
		} catch (Exception ee) {
			Assert.assertTrue(false);
			ee.printStackTrace();
		}
		logger.info("------Data collected, prints on the console and written on excel file--------");
	}

	@Test(priority = 4,groups= {"sanity"})
	public void selectSecondCourse() {
		logger.info("------Clicking on the Second course--------");
		try {
			res.closeChildWindow();
		} catch (Exception e) {
			System.out.println("no child window not avalable");
		}
		ch.selectSecondCourse();
		logger.info("------Second course details page shown--------");
	}

	@Test(priority = 5,groups= {"sanity","regression"})
	public void getSecondCourseData() throws InterruptedException {
		logger.info("------Collectiong the title,rating,duration of Second Course--------");
		try {
			ch.changeDriver();
			Thread.sleep(5000);
			String courseTitle2 = res.getCourseTitle();
			ExcelUtils.writeData("Web Courses", "Course - 2 Title", 3, 0);
			ExcelUtils.writeData("Web Courses", courseTitle2, 3, 1);
			String courseRating2 = res.getRating();
			ExcelUtils.writeData("Web Courses", "Course - 2 Rating", 4, 0);
			ExcelUtils.writeData("Web Courses", courseRating2, 4, 1);
			int duration2 = res.getDuration();
			ExcelUtils.writeData("Web Courses", "Course - 2 duration in hours", 5, 0);
			ExcelUtils.writeData("Web Courses",  String.valueOf(duration2), 5, 1);
			System.out.println(duration2);
			System.out.println(courseTitle2);
			System.out.println(courseRating2);
			Assert.assertTrue(true);
		} catch (Exception ee) {
			Assert.assertTrue(false);
		}
		
		logger.info("------Data collected, prints on the console and written on excel file--------");
	}
}
