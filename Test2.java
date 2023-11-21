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

public class Test2 extends Test1 {
		
@Test (priority=2)
  public void AddtoCart() throws InterruptedException  {
	 handleNewTab();
	 
  }

public void handleNewTab() {
    // Get the handles of all open tabs/windows
    Set<String> windowHandles = driver.getWindowHandles();

    // Switch to the new tab
    for (String windowHandle : windowHandles) {
        driver.switchTo().window(windowHandle);

       
        // check if the title contains specific keywords

        if (driver.getTitle().contains("Messages from the")) {
        	 logger.log(Level.INFO, "Title of the new window: " + driver.getTitle());
            
        	 JavascriptExecutor js = (JavascriptExecutor) driver;

             // Scroll down by  450 pixels)
             js.executeScript("window.scrollBy(0,450)");
             logger.info("Scrolled By 450 Pixels...");
             //wait for element to be found
             WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             By productLocator;
             try {
             productLocator = By.cssSelector
            		 ("#mainContent > div.vim.d-vi-region.x-atf-center-river--buybox > div > div.x-buybox__section > div.x-buybox__cta-section > ul > li:nth-child(2) > div > a");
             	WebElement cartbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));
             	cartbtn.click();
             	 logger.info("Cart Button Clicked...");
             }
             catch (Exception e) {
            	 productLocator = By.cssSelector("#mainContent > div > div.vim.vi-evo-row-gap > ul > li:nth-child(2) > div > a");
            	 WebElement cartbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));
            	 cartbtn.click();
            	 logger.info("Cart Button Clicked...");
             }        
              break;     
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

