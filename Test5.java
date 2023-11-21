package ebay;

import java.time.Duration;
import java.util.Set;
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test5 extends Test4 {

    @Test
    public void createAccount() {
        try {
            // Switch to the sign-in window
            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle : windowHandles) {
                driver.switchTo().window(windowHandle);
                if (driver.getTitle().contains("Sign in or Register")) {
                    logger.log(Level.INFO, "Title of the new window: " + driver.getTitle());

                    // Wait for the "Create account" link to be visible
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    By productLocator = By.id("create-account-link");
                    WebElement createacc = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));

                    // Click on the create account link to navigate to the page
                    createacc.click();

                    // Switch to the new business account window
                    Set<String> windowHandles1 = driver.getWindowHandles();
                    for (String windowHandle1 : windowHandles1) {
                        driver.switchTo().window(windowHandle1);

                        if (driver.getTitle().contains("business account")) {
                            logger.log(Level.INFO, "Title of the new window: " + driver.getTitle());

                            // Click on the personal account option
                            driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div/fieldset/span[1]/span[1]/span")).click();

                            // Switch to the personal account window
                            Set<String> windowHandles2 = driver.getWindowHandles();
                            for (String windowHandle2 : windowHandles2) {
                                driver.switchTo().window(windowHandle2);

                                if (driver.getTitle().contains("personal account")) {
                                    logger.log(Level.INFO, "Title of the new window: " + driver.getTitle());

                                    JavascriptExecutor js = (JavascriptExecutor) driver;
                                    // Scroll down by 500 pixels
                                    js.executeScript("window.scrollBy(0,500)");

                                    WebElement firstName = driver.findElement(By.id("firstname"));
                                    WebElement lastName = driver.findElement(By.id("lastname"));
                                    WebElement emailId = driver.findElement(By.id("Email"));
                                    WebElement passWord = driver.findElement(By.id("password"));

                                    // Enter all the required details
                                    firstName.sendKeys("Shweta");
                                    lastName.sendKeys("Gaikwad");
                                    emailId.sendKeys("shweta23@gmail.com");
                                    passWord.sendKeys("Shweta@234");

                                    // Click on the register button
                                    WebElement registerBtn = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[3]/div/div/div/div/form/div[6]/div/span/button"));
                                    logger.log(Level.INFO, "Register Button Text: " + registerBtn.getText());

                                    // Wait for the button to be clickable and click
                                    wait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to an exception.");
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}