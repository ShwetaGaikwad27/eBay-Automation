package ebay;

import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;



@Listeners(TestListener.class)
public class Test7 {
	WebDriver driver;
	private static final Logger logger = Logger.getLogger(Test7.class.getName());
	  @Test
	  public void testRegionChange() {
	      logger.info("Starting the testRegionChange method...");

	      // Initialize the ChromeDriver
	      driver = new ChromeDriver();

	      // Navigate to eBay
	      driver.get("https://www.ebay.com/");

	      logger.info("Navigated to eBay.");

	      // Create a JavaScriptExecutor to perform scrolling
	      JavascriptExecutor js = (JavascriptExecutor) driver;

	      // Scroll down by 2000 pixels
	      js.executeScript("window.scrollBy(0,2000)");

	      logger.info("Scrolled down the page.");

	      // Find the region selector element
	      WebElement regionSelector = driver.findElement(By.id("gf-fbtn-arr"));
	      regionSelector.click();

	      logger.info("Clicked on the region selector.");

	      // Locate the region options list and the desired region (replace with actual locators)
	      WebElement regionOptionsList = driver.findElement(By.cssSelector("#gf-f > ul"));
	      WebElement desiredRegion = regionOptionsList.findElement(By.xpath("//*[@id=\"gf-f\"]/ul/li[21]/a"));
	      desiredRegion.click();

	      logger.info("Selected the desired region.");

	      // Verify that the region has changed by checking the page title
	      Assert.assertTrue(driver.getTitle().contains("Export from India"), "Region not changed to India");

	      logger.info("Region change verified. Page title: " + driver.getTitle());

	      // Close the WebDriver session
	      driver.quit();

	      logger.info("WebDriver session closed.");
	  }
	}
