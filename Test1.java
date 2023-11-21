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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Test1 {

    WebDriver driver;
    protected String URL = "https://www.ebay.com/";
    protected static final Logger logger = Logger.getLogger(Test1.class.getName());

    @BeforeClass
    public void setUp() {
        try {
            // Initialize the ChromeDriver instance
            driver = new ChromeDriver();
            logger.log(Level.INFO, "WebDriver initialized successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during WebDriver initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test(priority = 1)
    public void searchForItem() {
        try {
            driver.get(URL);
            WebElement searchInput = driver.findElement(By.xpath("//input[@id=\"gh-ac\"]"));
            searchInput.sendKeys("The love never ends");

            // Select category as Book
            WebElement allCategories = driver.findElement(By.xpath("//select[@id=\"gh-cat\"]"));
            Select select = new Select(allCategories);
            String valueToSelect = "267";
            select.selectByValue(valueToSelect);

            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"gh-btn\"]"));
            searchButton.click();

            WebElement firstSearchResult = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[1]/div/div[1]/div[1]/div[1]"));
            String resultText = firstSearchResult.getText();
            String expectedKeyword = "love never ends";

            Assert.assertTrue(resultText.toLowerCase().contains(expectedKeyword),
                    "Search result does not contain the expected keyword: " + expectedKeyword);

            // Wait until product found
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            By productLocator = By.cssSelector("#item2d32cf1773 > div > div.s-item__info.clearfix > a");

            WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(productLocator));

            // Get product details and handle new window
            logger.log(Level.INFO, "Product found: " + product.getText());
            Set<String> windowHandles = driver.getWindowHandles();
            for (String windowHandle : windowHandles) {
                driver.switchTo().window(windowHandle);
                if (driver.getTitle().contains("love never ends")) {
                    logger.log(Level.INFO, "Title of the new window: " + driver.getTitle());
                    product.click();
                    logger.log(Level.INFO, "Product clicked");
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Test failed due to an exception.");
        }
    }
}
  