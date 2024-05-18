package Scenario2;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EdgeDriverTestableScenario2 {
	
	WebDriver driver;
	@BeforeTest
	public void beforeTest() {
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.manage().window().maximize();
		driver.get("https://ecommerce-playground.lambdatest.io/");
	}
	@Test
	public void action() {
		EcommerceWebsite obj = new EcommerceWebsite(driver);
		obj.clickOnMegaMenu();
		obj.clickOnApple();
		obj.clickOnNumberOfProductsListed();
		obj.printAndSortProductListAssert();
		obj.clickOnPLPAddToCart();
		obj.clickOnPLPAddToWish();
		obj.clickOnSecondProduct();
		obj.assertAvailabilityStatus();
		Reporter.log("Successfully Checked Stock Availability");
		obj.incrementProductQuantity();
		obj.clickOnPDPAddToCart();
		obj.clickOnViewCart();
		obj.assertPdpQuantity();
		obj.removePLPElement();
		Reporter.log("Successfully Removed the product added in PLP");
		obj.clickOnCheckOutButton();
		obj.scrollPage();
		obj.clickOnCheckOutButton();
		obj.clickOnGuestCheckOut();
		obj.enterFirstName();
		obj.enterLastName();
		obj.enterEmail();
		obj.enterPhoneNumber();
		obj.enterCompany();
		obj.enterAddress1();
		obj.enterAddress2();
		obj.enterCity();
		obj.enterPostCode();
		obj.pageScroll();
		obj.enterCountry();
		obj.enterRegion();
		obj.clickOnAgreeTermsandConditions();
		obj.clickOnContinue();
		obj.assertAddress();
		obj.finalPageSroll();
		obj.clickOnConformOrder();
		Reporter.log("Successfully Placed the Order");
		
	}


}
