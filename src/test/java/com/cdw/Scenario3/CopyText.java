package com.cdw.Scenario3;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class CopyText {
	
	WebDriver driver;
    public String stars="";
	String expectedMsg="Well done!";
	By textBoxInfo = By.xpath("//input[@placeholder='Enter the * Rating of the book. E.g. ****']");
	By checkBox = By.xpath("//button[@id='check_rating']");
	By successMsgInfo = By.xpath("//span[@id='validate_rating']");
	
	public CopyText(WebDriver driver) {
		this.driver=driver;
	}
	

	public void accessingStars(){
		String jscode = "return window.getComputedStyle(document.querySelector('label.star-rating'), ':after').getPropertyValue('content')";
		String ratingtext = (String) ((JavascriptExecutor) driver).executeScript(jscode);
		stars = ratingtext.replace("\"", "");
		
		WebElement textBox = driver.findElement(textBoxInfo);
		textBox.click();
		System.out.println(stars);
		textBox.sendKeys(stars);
		
	}
	
	public void clickCheckButton(){
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		wait.until(ExpectedConditions.elementToBeClickable(checkBox));
		driver.findElement(checkBox).click();
		
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
	}
	
	public void assertSuccessMessage() {
		String actualMsg=driver.findElement(successMsgInfo).getText();
		Assert.assertEquals(actualMsg,expectedMsg);
	}
	
}





