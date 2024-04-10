package stepDefinations;

import java.io.IOException;



//import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Enterprises;



public class enterprises {
	Enterprises ent;
	
	@Given("the user nagivate to Coursera page")
	public void  the_user_nagivate_to_Coursera_page() throws IOException {
		ent = new Enterprises(Hooks.getDriver());
	}
	
	@When("the user navigates to the Enterprise section")
	public void  the_user_navigates_to_the_Enterprise_section()  {
		ent.clkEnterprises();
	}
	
	@When("the user fills the Form with invalid email")
	public void  the_user_fills_the_Form_with_invalid_email() throws IOException, InterruptedException   {
		ent.filltheform();
	}
	
	@Then("the user should capture and display the error message")
	public void the_user_should_capture_and_display_the_error_message() {
		ent.errormsg();
	}
	@Then("capture error message")
	public void capture_error_message() {
	    ent.scrollMsg();
	}


}
