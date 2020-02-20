package testScripts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
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

public class Tc_Guest_User_01 extends Driver {
	WebDriver dr = null;
	WebDriverWait wait = null;

	@BeforeTest
	@Parameters({ "browser" })
	public void setup(String browser) throws IOException {
		System.setProperty("webdriver.chrome.driver","E:\\selenium class\\Framework_Morning2\\drivers\\chromedriver.exe");
		logger = report.createTest("Set up script for guest user");
		

		dr = lib.launchBrowser(browser);
		

		if (dr != null) {
			MediaEntityModelProvider img = MediaEntityBuilder
					.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
			logger.log(Status.PASS, "Flipkart Home screen is displayed successfully", img);
			wait = new WebDriverWait(dr, config_File.explicitwait);
			dr.findElement(By.xpath(or.getProperty("PopUp"))).click();
		} else {
			
			logger.log(Status.FAIL, "Flipkart Home screen is not displayed");

			throw new SkipException("Launch application failed");
		}

	}

	@Test(dataProvider = "getData", priority = 0)
	public void browse_script(String megamenu, String L1, String L2) throws InterruptedException, IOException {
		// ********Local Variables***********//
		Actions act = new Actions(dr);
		MediaEntityModelProvider img;
		// **********************************//

		logger = report
				.createTest("To test browser feature with Mega menu=" + megamenu + " L1=" + L1 + " and L2=" + L2);
		try
		{
			
		
		logger.log(Status.INFO, "Browse test starts");
		
		String mega=or.getProperty("Mega_P1") + megamenu + or.getProperty("Mega_P2");
		
		System.out.println(mega);
		if (lib.isElementPresent(dr, or.getProperty("Mega_P1") + megamenu + or.getProperty("Mega_P2"))) {
			// Hover mouse cursor over mega menu
			act.moveToElement(
					dr.findElement(By.xpath(or.getProperty("Mega_P1") + megamenu + or.getProperty("Mega_P2")))).build()
					.perform();
			img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
			logger.log(Status.PASS, "Options under " + megamenu + " displayed successfully", img);

			// Click on L1
			if (lib.isElementPresent(dr, or.getProperty("L1_P1") + megamenu + or.getProperty("L1_P2") + L1
					+ or.getProperty("L1_P3") + L2 + or.getProperty("L1_P4"))) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("L1_P1") + megamenu
						+ or.getProperty("L1_P2") + L1 + or.getProperty("L1_P3") + L2 + or.getProperty("L1_P4"))))
						.click();
				img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
				logger.log(Status.PASS, "Successfully clicked on " + L2, img);
			} else {
				img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
				logger.log(Status.FAIL, L2 + " is not displayed", img);

			}

		} else {
			img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
			logger.log(Status.PASS, megamenu + " is not displayed in mega menu bar", img);
		}
		}catch(Exception e)
		{
			img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
			logger.log(Status.FAIL, "Exception occurred: "+e.getMessage(), img);
	
		}

	}

	/*@Test(priority = 2)
	public void script2() throws IOException {
		logger = report.createTest("To test reset functionality");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.arbapps.loanemicalc:id/loanemicalc"))).click();
		MediaEntityModelProvider img = MediaEntityBuilder
				.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
		logger.log(Status.PASS, "Reset button is displayed is displayed", img);

		dr.findElement(By.id("com.arbapps.loanemicalc:id/reset")).click();
		img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
		logger.log(Status.PASS, "Reset button is clicked", img);

		System.out.println("Clicked on Reset button");

	}*/

	@AfterTest
	public void teardown() {
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
