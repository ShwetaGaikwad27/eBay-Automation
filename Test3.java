package ebay;

import java.time.Duration;
import java.util.Set;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Test3 extends Test2 {
	
  @Test (priority=3)
  public void proceedCheckout() throws InterruptedException {
	 
	  handleNewTab1();
	
  }
	
  		public void handleNewTab1() throws InterruptedException  {
  			 waitForPageLoad(driver);
  			
  				Set<String> windowHandles = driver.getWindowHandles();
  				
  				for (String windowHandle : windowHandles) {
  					driver.switchTo().window(windowHandle);
  					 
  					if (driver.getTitle().contains("shopping cart")) {
  						 logger.log(Level.INFO, "Title of the new window: " + driver.getTitle());
  						
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
          By productLocator = By.xpath("//div[@id=\"mainContent\"]/div/div[3]/div[2]/div/div[1]/button");  
           WebElement chkoutBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));
        // Click on the checkout button 
           logger.log(Level.INFO, "Checkout Button Enabled: " + chkoutBtn.isEnabled());
           wait.until(ExpectedConditions.elementToBeClickable(chkoutBtn));
  	    chkoutBtn.click();
  	  logger.info("Checkout Button Clicked...");
  	    }
			} 				
}
  	 
  }
  