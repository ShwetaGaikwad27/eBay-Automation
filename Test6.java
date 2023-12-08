package ebay;

import java.time.Duration;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class Test6 {
	private WebDriver driver;
    private static final Logger logger = Logger.getLogger(Test6.class.getName());

    @BeforeMethod
    public void setUp() {
        logger.log(Level.INFO, "Initializing WebDriver...");
        driver = new ChromeDriver();     
    }

    @Test
    public void helpAndContact() {
        try {
            logger.log(Level.INFO, "Navigating to eBay...");
            driver.get("https://www.ebay.com/");

            // Check that the "Help and Contact" link is present
            WebElement helpAndContactLink = driver.findElement(By.linkText("Help & Contact"));
            Assert.assertTrue(helpAndContactLink.isDisplayed(), "Help and Contact link is not present.");

            // Confirm that the link is clickable
            helpAndContactLink.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleContains("Customer Service"));

            logger.log(Level.INFO, "Verifying the Help page...");
            WebElement someElementOnHelpPage = driver.findElement(By.xpath("/html/body/div[5]/div[2]/div[1]/div[1]/div[3]/h1"));
            Assert.assertTrue(someElementOnHelpPage.isDisplayed(), "Help page did not load successfully.");

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Scroll down by 800 pixels)
            js.executeScript("window.scrollBy(0,800)");

            logger.log(Level.INFO, "Verifying categories on Help page...");
            WebElement accountCategory = driver.findElement(By.id("account"));
            Assert.assertTrue(accountCategory.isDisplayed(), "Account Information category is not present.");

            WebElement returnRefundCategory = driver.findElement(By.id("returns-refunds"));
            Assert.assertTrue(returnRefundCategory.isDisplayed(), "Orders category is not present.");

            WebElement shipTrackCategory = driver.findElement(By.id("ship-track"));
            Assert.assertTrue(shipTrackCategory.isDisplayed(), "Payments category is not present.");
            Thread.sleep(2000);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to an exception.");
        }
    }

    @AfterMethod
    public void tearDown() {
        logger.log(Level.INFO, "Closing WebDriver...");
        if (driver != null) {
            driver.quit();
        }
    }
}
