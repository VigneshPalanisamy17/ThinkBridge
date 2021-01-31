package com.thinkbridge.AssessmentQ2;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class FlightSearch_Test {

		public static WebDriver driver;
	
		FlightSearch_Bean bean = null;
		private String driverLocation= System.getProperty("user.dir") + "\\" + "browserDriver" + "\\" + "chromedriver.exe";
			
		@BeforeTest
		public void Launch_and_Login(){
			System.setProperty("webdriver.chrome.driver",driverLocation);
			driver = new ChromeDriver();
			bean = new FlightSearch_Bean(driver);
			driver.get("https://jt-dev.azurewebsites.net/#/SignUp");
			driver.manage().window().maximize();
			bean.verifyLanguage();
			bean.WaitTime(3000);
			bean.Name("Vignesh");
			bean.WaitTime(3000);
			bean.Organisation("Capgemini");
			bean.WaitTime(3000);
			bean.SendEmail("itzvicky8080@gmail.com");
			bean.Terms_Cons();
			bean.WaitTime(3000);
		}
		
		@AfterTest
	    public void CloseDriver(){
	        driver.close();
	    }
		
		@Test(priority = 1)
		public void Validate_Page_Title() throws InterruptedException{
			String expectedTitle = "Jabatalks";
			String actualTitle = bean.Page_Title();
			Assert.assertEquals(expectedTitle, actualTitle);
		}

		@Test(priority = 2)
		public void Validate_Email() {	
			//Sending Email
			bean.submit();
			bean.WaitTime(6000);
			//Validating Email
			String message=bean.getObservedMessage();
			System.out.println(message);
			String expectedMessage = "A welcome email has been sent. Please check your email.";
			Assert.assertEquals(expectedMessage, message);					
		}	   
}
