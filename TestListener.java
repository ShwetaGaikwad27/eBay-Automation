package ebay;

import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
	 WebDriver driver;
	 private static final Logger logger = Logger.getLogger(Test1.class.getName());

    // Set the driver instance
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    // ON Test Start
	 @Override
	public void onTestStart(ITestResult result) {
		 logger.info("Test Execution Started: " + result.getName());		
	}

	 // On Test Failure
	 @Override
		public void onTestFailure(ITestResult result) {	  
		 logger.info("Test Failed: " + result.getName());
		 
	        try {
				Test9.screenshot();
			} catch (IOException e) {
				e.printStackTrace();
			}	 
		}
	 
	 //On Test Success
	@Override
	public  void onTestSuccess(ITestResult result) {		
		logger.info("Test Passed: " + result.getName()); 
	}	 
}

