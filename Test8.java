package ebay;

import java.time.Duration;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Test8 {
     private WebDriver driver;
    private static final Logger logger = Logger.getLogger(Test8.class.getName());

  @Parameters({"username", "password"})
  @Test
 public  void watchlistIsEmpty(String username, String password) throws InterruptedException {
			 
  				 driver=new ChromeDriver();
  				 logger.info("Navigating to eBay...");
  				  driver.get("https://www.ebay.com/");
  				  waitForPageLoad(driver);
		      // Navigate to the watchlist
		      driver.findElement(By.linkText("Watchlist")).click();
		      logger.info("Clicked on Watchlist Icon...");
		      Thread.sleep(5000);
		      
			     //switch to new window
			      Set<String> windowHandles = driver.getWindowHandles();
			      for (String windowHandle : windowHandles) {
			          driver.switchTo().window(windowHandle);
			         if (driver.getTitle().contains("Sign in")) {
			        	 logger.log(Level.INFO, "Title of the new window: " + driver.getTitle());
			        	  
		        	  //Username field
		        	WebElement usernameInput=driver.findElement(By.id("userid"));
		        	WebElement continueBtn=driver.findElement(By.id("signin-continue-btn"));
		        	  usernameInput.sendKeys(username);
		        	  continueBtn.click();
		        	  logger.info("Username Entered...");
		        	
		        	//Password field  
		             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        	 WebElement passwordInput=driver.findElement(By.id("pass"));
		        	 System.out.println("Is Password Input Enabled: " + passwordInput.isEnabled());
		        	 wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
		        	 passwordInput.sendKeys(password);  
		        	 logger.info("Password Entered...");
		        	 WebElement signinBtn=driver.findElement(By.id("sgnBt"));
		        	 logger.info("Clicked on Sign In Button...");
		        	  signinBtn.click();
		        	  
		        	  //switch to watchlist window
		        	  Set<String> windowHandles1 = driver.getWindowHandles();
						for (String windowHandle1 : windowHandles1) {
							driver.switchTo().window(windowHandle1);

							if (driver.getTitle().contains("Watchlist")) {
								logger.log(Level.INFO, "Title of the new window: " + driver.getTitle());
								//Check watchlist is empty
								WebElement messageOnPage=driver.findElement(By.cssSelector("#middle-wrapper-grid > div:nth-child(4) > div.m-empty > div > div"));
								 Assert.assertTrue(messageOnPage.isDisplayed(), "Watchlist is not in the empty state.");
								 logger.info("Message  verified...");
}
}
			         }
			      }
			    
}
  
		  public static void waitForPageLoad(WebDriver driver) {
		      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		
		      // Use the JavaScript executor to check the ready state of the page
		    //The lambda expression returns a String representing the ready state of the document.
		     // The equals part ensures that the condition is true only when the ready state is complete
		      wait.until(driver1 -> ((String) ((JavascriptExecutor) driver1).executeScript("return document.readyState")).equals("complete"));
		  }
}
