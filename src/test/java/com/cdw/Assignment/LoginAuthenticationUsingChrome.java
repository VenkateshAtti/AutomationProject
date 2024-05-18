package com.cdw.Assignment;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class LoginAuthenticationUsingChrome {
	
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		driver=new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("http://admin:admin@the-internet.herokuapp.com/");
	}
	
	@Test
	public void loginToSite() throws IOException {
		LoginAuthentication log = new LoginAuthentication(driver);
		log.login();
		log.verifyTextAfterLogin("Congratulations! You must have the proper credentials.");
		Reporter.log("Successfully Logged in");
	}
    @AfterTest
	public void afterTest() {
		driver.quit();
	}
}

