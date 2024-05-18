package Scenario2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EcommerceWebsite {
	
	WebDriver driver;
	 
	public EcommerceWebsite(WebDriver driver) {
		this.driver=driver;
	}
	
	
	By MegaMenu = By.xpath("//span[normalize-space(text())='Mega Menu']");
	By Apple = By.xpath(" //a[normalize-space(text())='Apple']");
	By ProductList = By.xpath("//div[@class='carousel-item active']");
	By eachProductName = By.xpath("//a[@class='text-ellipsis-2']");
	By eachProductCost = By.xpath("//span[@class='price-new']");
	By FirstProduct = By.xpath("(//div[@class='carousel-item active'])[1]");
	By PLPAddToCart = By.xpath("(//div//button[@title='Add to Cart'])[1]");
	By PLPAddToWish = By.xpath("(//i[@class='fas fa-heart'])[1]");
	By PDPAddToCart = By.xpath("(//div//button[text()='Add to Cart'])[2]");
	
	By SecondProduct = By.xpath("(//div[@class='carousel-item active'])[2]");
	By ProductAvailability= By.xpath("//span[text()='In Stock']");
	By ProductIncrement=By.xpath("(//button[@aria-label='Increase quantity'])[position()=2]");
	
	By viewCartButton = By.xpath("//a[@class='btn btn-primary btn-block']");
	By QuantityText = By.xpath("(//input[@class='form-control'])[position()=2]");
	By RemoveButton = By.xpath("//button[(@class='btn btn-danger')][1]");
	By CheckOutButton = By.xpath("//a[text()='Checkout']");
	
	By GuestButton=By.xpath("//label[@for='input-account-guest']");
	By FirstName = By.xpath("//input[@id='input-payment-firstname']");
	By LastName = By.xpath("//input[@id='input-payment-lastname']");
	By Email = By.xpath("//input[@id='input-payment-email']");
	By Phone = By.xpath("//input[@id='input-payment-telephone']");
	By Company = By.xpath("//input[@id='input-payment-company']");
	By Address1 = By.xpath("//input[@id='input-payment-address-1']");
	By Address2 = By.xpath("//input[@id='input-payment-address-2']");
	By City = By.xpath("//input[@id='input-payment-city']");
	By PostCode = By.xpath("//input[@id='input-payment-postcode']");
	By Country = By.xpath("//select[@id='input-payment-country']");
	By Region = By.cssSelector("#input-payment-zone");
	
	By ContinueButton = By.cssSelector("button#button-save");
	By Agree = By.xpath("//label[@for='input-agree']");
	
	By PaymentAddress = By.xpath("(//div[@class='card-body'])[1]");
	By ShippingAddress = By.xpath("(//div[@class='card-body'])[2]");
	By ConformButton = By.cssSelector("button#button-confirm");
	
	public void clickOnMegaMenu() {
		Actions action = new Actions(driver);
		WebElement megaMenuHover=driver.findElement(MegaMenu);
		action.moveToElement(megaMenuHover).perform();
	}
	
	public void clickOnApple() {
		driver.findElement(Apple).click();
	}
	
	public void clickOnNumberOfProductsListed() {
        List<WebElement> productList=driver.findElements(By.xpath("//div[@class='carousel-item active']"));
		System.out.println("Total Number of Products listed on the page "+productList.size());
	}
	public void printAndSortProductListAssert() {
		try {
	    	   Map<String, String> productMap = new HashMap<>();
	    	   TreeMap<Double, String> sortedProductMap = new TreeMap<>();

	           List<WebElement> name = driver.findElements(eachProductName);
	           List<WebElement> prices = driver.findElements(eachProductCost);
	        		        	        
	           for (int j = 0; j < name.size(); j++) {
	           WebElement productElement = name.get(j);
	           WebElement priceElement = prices.get(j);
	        				    
	           String productName = productElement.getText();
	           String price = priceElement.getText();
	           
	           productMap.put(productName, price);
	        				    
	           System.out.println("Product: " + productName);
	           System.out.println("Price: " + price);
	           
	           //Adding values from Hashmap to TreeMap
	           }
	           for (Map.Entry<String, String> entry : productMap.entrySet()) {
	               String productNameT = entry.getKey();
	               String priceStrT = entry.getValue();
	               double priceT = Double.parseDouble(priceStrT.replaceAll("[^\\d.]", ""));
	               sortedProductMap.put(priceT, productNameT);
	           }
	           
	           System.out.println("Sorted Products based on Price (Low to High):");
	           double prevPrice = Double.MIN_VALUE;
	           for (Map.Entry<Double, String> entry : sortedProductMap.entrySet()) {
	               double sortPrice = entry.getKey();
	               String sortProductName = entry.getValue();
	               System.out.println("Product: " + sortProductName + ", Price: " + sortPrice);
	               
	               assert sortPrice >= prevPrice : "Sorting is incorrect";
	               prevPrice = sortPrice;
	        	}
	           
	        } catch (StaleElementReferenceException e) {
	          System.out.println("Elements became stale. Retrying...");
         }
	}
	public void clickOnPLPAddToCart() {
		Actions action =new Actions(driver);	
		WebElement firstProduct=driver.findElement(FirstProduct);
		action.moveToElement(firstProduct).perform();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(PLPAddToCart));

		
		driver.findElement(PLPAddToCart).click();
	}
	public void clickOnPLPAddToWish() {
		Actions action =new Actions(driver);
		WebElement firstProduct=driver.findElement(FirstProduct);
		action.moveToElement(firstProduct).perform();
		driver.findElement(PLPAddToWish).click();
	}
	
	public void clickOnSecondProduct() {
		driver.findElement(SecondProduct).click();
	}
	
	public void assertAvailabilityStatus() {
		String text="In Stock";
		String availability = driver.findElement(ProductAvailability).getText();
		Assert.assertEquals(text, availability);
	}
	public void incrementProductQuantity() {
		driver.findElement(ProductIncrement).click();
	}
	
	public void clickOnPDPAddToCart() {
		driver.findElement(PDPAddToCart).click();
	}
	
	public void clickOnViewCart() {
		driver.findElement(viewCartButton).click();
	}
	
	public void assertPdpQuantity() {
		String quantity = "2";
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 String count = (String) js.executeScript("return arguments[0].value;", driver.findElement(QuantityText));

		 Assert.assertEquals(quantity, count);
	}
	
	public void removePLPElement() {
		driver.findElement(RemoveButton).click();
	}
	public void scrollPage() {
		WebElement element = driver.findElement(CheckOutButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
	}
	public void clickOnCheckOutButton() {
	
		driver.findElement(CheckOutButton).click();
	}
	
	public void clickOnGuestCheckOut() {
		driver.findElement(GuestButton).click();
	}
	
	public void enterFirstName() {
		driver.findElement(FirstName).sendKeys("Venky");
	}
	public void enterLastName() {
		driver.findElement(LastName).sendKeys("Attirala");
	}
	public void enterEmail() {
		driver.findElement(Email).sendKeys("venky@gmail.com");
	}
	public void enterPhoneNumber() {
		driver.findElement(Phone).sendKeys("+376 6300138226");
	}
	public void enterCompany() {
		driver.findElement(Company).sendKeys("Company");
	}
	public void enterAddress1() {
		driver.findElement(Address1).sendKeys("Andorra City");
	}
	public void enterAddress2() {
		driver.findElement(Address2).sendKeys("Andorra City");
	}
	public void enterCity() {
		driver.findElement(City).sendKeys("City1");
	}
	public void enterPostCode() {
		driver.findElement(PostCode).sendKeys("5467384");
	}
    public void pageScroll() {
    	WebElement element = driver.findElement(ContinueButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);	
    }
    public void enterCountry() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(Country));
        
    	WebElement country = driver.findElement(Country);
        Select dropDownCoun=new Select(country);
        dropDownCoun.selectByVisibleText("Andorra");
    }
    public void enterRegion() {   
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(Region));

        Select objSelect = new Select(driver.findElement(Region));
        objSelect.selectByValue("123");
    }
    public void clickOnAgreeTermsandConditions() {
    	driver.findElement(Agree).click();
    }
    public void clickOnContinue() {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(ContinueButton));
        
    	driver.findElement(ContinueButton).click();
    }
    public void assertAddress() {
    	String paymentAddress=driver.findElement(By.xpath("(//div[@class='card-body'])[1]")).getText();
		String shippingAddress = driver.findElement(By.xpath("(//div[@class='card-body'])[1]")).getText();
		
		Assert.assertEquals(paymentAddress, shippingAddress);
    }
    public void finalPageSroll() {
    	WebElement orderElement = driver.findElement(ConformButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", orderElement);
    }
    public void clickOnConformOrder() {
    	driver.findElement(ConformButton).click();
    	
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
}
