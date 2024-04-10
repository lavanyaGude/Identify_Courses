package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.Enterprises;
import testBase.ReusableComponents;

public class TC3_enterprises extends ReusableComponents {
	static Enterprises obj5;
	
	@Test(priority=0,groups= {"sanity"})
	public void forEnterprise() throws IOException {
		obj5 = new Enterprises(driver);
        obj5.clkEnterprises();
        logger.info("----- forEnterprises method runs succesful------");
	}
	
	@Test(priority=1,groups= {"sanity","regression"})
	public void submitForm() throws IOException {
		obj5 = new Enterprises(driver);
		obj5.filltheform();
		obj5.errormsg();
		obj5.scrollMsg();
		logger.info("------error message is shown------");
	}

}
