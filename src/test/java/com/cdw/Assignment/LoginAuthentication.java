package com.cdw.Assignment;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LoginAuthentication {
	WebDriver driver;
	
	public LoginAuthentication(WebDriver driver) {
		this.driver=driver;
	}

	By Basic_Auth = By.linkText("Basic Auth");
	By SucceessfulLoginTest = By.xpath("//p");
	
	public void login() {
		driver.findElement(Basic_Auth).click();
	}
	
	public void verifyTextAfterLogin(String verify)  {
		String text = driver.findElement(SucceessfulLoginTest).getText();
		
		
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String DT = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy__HHmmss"));
        String screenshotPath = "/Users/venkatesh/eclipse-workspace/Assignment/images/ScreenShot"+DT+".jpeg";
 
        // Save the screenshot to the specified path
        try {
            org.openqa.selenium.io.FileHandler.copy(screenshotFile, new File(screenshotPath));
            System.out.println("Screenshot saved to: " + screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
		  
	
		System.out.println(text);
		Assert.assertEquals(verify, text);
	}
	
}
