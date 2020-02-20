package testScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

import baseClass.Driver;
import config.config_File;

public class TC_Regisrereduser_01  extends Driver{
	
	WebDriver dr = null;
	WebDriverWait wait = null;

	@BeforeTest
	@Parameters({ "browser" })
	public void setup(String browser) throws IOException {
		
		logger = report.createTest("Set up script for guest user");
		
		System.setProperty("webdriver.chrome.driver","E:\\selenium class\\Framework_Morning\\drivers\\chromedriver.exe");
		dr = lib.launchBrowser(browser);
		

		if (dr != null) {
			MediaEntityModelProvider img = MediaEntityBuilder
					.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
			logger.log(Status.PASS, "Flipkart Home screen is displayed successfully", img);
			wait = new WebDriverWait(dr, config_File.explicitwait);
			dr.findElement(By.xpath(or.getProperty("PopUp")));
		} else {
			
			logger.log(Status.FAIL, "Flipkart Home screen is not displayed");

			throw new SkipException("Launch application failed");
		}
		
	}
	@Test(dataProvider = "getData", priority = 0)
	public void verify_login(String username,String password,String Firstname)

	{
		// ********Local Variables***********//
				Actions act = new Actions(dr);
				MediaEntityModelProvider img;
				// **********************************//
				logger = report
						.createTest("Verify ogin functionality with username=" +username);
             try{
            	 if(lib.isElementPresent(dr, or.getProperty("Username")))
            	 {
            	 logger.log(Status.INFO, "Login Test starts");
            	 dr.findElement(By.xpath(or.getProperty("Username"))).sendKeys(username);
            	 dr.findElement(By.xpath(or.getProperty("Password"))).sendKeys(password);
            	 
            	 
            	 img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
     			logger.log(Status.PASS, "user name and password entered successsfully", img);
            	 }
            	 else
            	 {
            		 img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
          			logger.log(Status.FAIL, "user name and password entered successsfully", img);
            		 
            	 }
     			
     			//step2 cllickk on  log in button ans verify first name is displayed
     			
     			dr.findElement(By.xpath(or.getProperty("login_btn"))).click();
     			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(or.getProperty(Firstname))));
     			img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
     			logger.log(Status.PASS, "login successfully", img);
     			
            	 
             }
             catch(Exception e)
             {
            	
             }
	}
	@AfterTest
	public void teardown()
	{
		dr.quit();
	}
	@DataProvider
	public Object[][] getData() throws IOException {

		int rows = xl.getrowcount("verify_login");
		int cols = xl.getColumncount("verify_login");

		System.out.println("rows="+rows+" cols="+cols);
		Object[][] data = new Object[rows - 1][cols];

		for (int r = 2; r <= rows; r++) {
			for (int c = 1; c <= cols; c++) {
				data[r - 2][c-1] = xl.getCellData("verify_login", r, c );
			}

		}

		return data;

	}

}
