package ebay;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.google.common.io.Files;



@Listeners(TestListener.class)
public class Test9 {

    static WebDriver driver;
    private static final Logger logger = Logger.getLogger(Test9.class.getName());

    @Test
    public void languageChange() {
        try {
            driver = new ChromeDriver();
            driver.get("https://www.ebay.com/");

            waitForPageLoad(driver);

            WebElement searchInput = driver.findElement(By.xpath("//input[@id=\"gh-ac\"]"));
            searchInput.sendKeys("द लव नेवर एंडस");

            WebElement allCategories = driver.findElement(By.xpath("//select[@id=\"gh-cat\"]"));
            Select select = new Select(allCategories);
            String valueToSelect = "267"; // Replace with the actual value you want to select
            select.selectByValue(valueToSelect);

            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"gh-btn\"]"));
            searchButton.click();

            WebElement firstSearchResult = driver.findElement(By.xpath("//*[@id=\"srp-river-results\"]/ul/li/div/div[1]"));
            String resultText = firstSearchResult.getText();
            String expectedKeyword = "लव नेवर"; 
           
          Assert.assertTrue(resultText.contains(expectedKeyword),
                    "Search result does not contain the expected keyword: " + expectedKeyword);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage());
            e.printStackTrace();
            try {
                screenshot();
            } catch (IOException ioException) {
                logger.log(Level.SEVERE, "Failed to capture screenshot: " + ioException.getMessage());
                ioException.printStackTrace();
            }
            Assert.fail("Test failed due to an exception.");
        } 
    }

    public static void waitForPageLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Use the JavaScript executor to check the ready state of the page
        wait.until(driver1 -> ((String) ((JavascriptExecutor) driver1)
                .executeScript("return document.readyState")).equals("complete"));
    }

        public static void screenshot() throws IOException {
            // Generate a timestamp for the screenshot file name
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(new Date());

            // Construct the file name with timestamp
            String fileName = "Ebay_" + timestamp + ".jpg";

            // Capture screenshot and save with the timestamped file name
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(screenshotFile, new File("C:\\Users\\AdminT\\Desktop\\Selenium\\ScreenshotProject\\" + fileName));
        }
    }

