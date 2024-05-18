package com.cdw.Scenario3;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class EdgeTestables {

WebDriver driver;
	
	@BeforeTest
    public void beforeTest() {
    	driver = new EdgeDriver();
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    	driver.get("https://play1.automationcamp.ir/advanced.html");
    }
	
	@Test
	public void test(){
		CopyText Ct = new CopyText(driver);
		Ct.accessingStars();
		Ct.clickCheckButton();
		Ct.assertSuccessMessage();
		Reporter.log("Successfully Logged in");
		
	}
	
	@AfterTest
	public void tearUp() {
		driver.quit();
	}
}
