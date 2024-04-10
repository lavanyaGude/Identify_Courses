package stepDefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CourseraHomePage;
import pageObjects.SearchResult;


public class HomePageSearch{
	public WebDriver driver;
	public static  CourseraHomePage chp;
	public static SearchResult sr;
	public static int rowNum=0;
	public static int colNum=0;
	public static List<String> exceldata=new ArrayList<String>();
	@Given("User opens the application")
	public void user_opens_the_application() throws IOException {
	    driver=Hooks.getDriver();
	    chp=new CourseraHomePage(driver);
	    
	    driver.get("https://www.coursera.org/");
	}

	@When("User search for {string} courses and clicks on search button")
	public void user_search_for_courses(String string) {
	    chp.searchCourse();
	}

	@When("select the English Language filter option")
	public void select_the_english_language_filter_option() {
	    chp.selectLanguage();
	}

	@When("select the Beginner level filter option")
	public void select_the_beginner_level_filter_option() {
	    chp.selectLevel();
	}

	@When("user selects the first course")
	public void user_selects_the_first_course() throws InterruptedException {
		chp.selectFirstCourse();
	}

	@Then("user should naviagate to separate window")
	public void user_should_naviagate_to_separate_window() throws IOException {
	    sr=new SearchResult(driver);
	    sr.switchToChild();
	}

	@Then("Collect the title,rating and duration of course in hours")
	public void collect_the_title_rating_and_duration_of_course_in_hours() {
		
	    String t1=sr.getCourseTitle();
	   exceldata.add(t1);
	    
	    String t2=sr.getRating();
	    exceldata.add(t2);
	  
	   String t3= String.valueOf(sr.getDuration());
	    exceldata.add(t3);
	    
	}

	@Then("After gathering the data. That child window should be closed")
	public void after_gathering_the_data_that_child_window_should_be_closed() throws IOException {
		
	    sr.closeChildWindow();
	    chp.switchToParentWindow();
	}

	@When("Now User select the second course with above filter options.")
	public void now_user_select_the_second_course_with_above_filter_options() throws IOException {
		chp.selectSecondCourse();
		
	}
	@Then("add data to excel")
	public void add_data_to_excel() throws IOException {
		sr.setAllData(exceldata);
	}
}
