package ebay;

import java.time.Duration;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test4 {

    protected WebDriver driver;
    public static final String URL = "https://www.ebay.com/";
    protected static final Logger logger = Logger.getLogger(Test4.class.getName());

    @Test
    public void cartIsEmptyState() throws InterruptedException {
        try {
            driver = new ChromeDriver();
            driver.get(URL);

            // Click on the cart icon to navigate to the cart page
            WebElement cartIcon = driver.findElement(By.id("gh-minicart-hover"));
            cartIcon.click();

            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle : windowHandles) {
                driver.switchTo().window(windowHandle);
                if (driver.getTitle().contains("shopping cart")) {
                    logger.log(Level.INFO, "Title of the new window: " + driver.getTitle());

                    WebElement spanElement = driver.findElement(By.cssSelector("#mainContent > div > div.columns > div > div > div > div.font-title-3 > span> span>span"));

                    // Get the text content of the <span> element
                    String actualText = spanElement.getText();
                    String expectedText = "You don't have any items in your cart.";

                    // Assert that the actual text matches the expected text
                    Assert.assertEquals(actualText, expectedText, "Text in the <span> element does not match the expected text.");
                    logger.log(Level.INFO, actualText);

                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    By productLocator = By.cssSelector("#mainContent > div > div.columns > div > div > div > div.actions.multi-actions > a.fake-btn.sign-in.action.fake-btn--primary.fake-btn--fluid");
                    WebElement signInBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));

                    // Click on the sign-in button to navigate to the page
                    signInBtn.click();
                    Thread.sleep(2000);
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to an exception.");
        }
    }
}
