package testScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

public class TC_gmail extends Driver {
	WebDriver dr = null;
	WebDriverWait wait = null;
	//String url = dr.getCurrentUrl();
    @BeforeTest
	@Parameters({ "browser" })
	public void setup(String browser) throws IOException {
		System.setProperty("webdriver.chrome.driver","E:\\selenium class\\Framework_Morning\\drivers\\chromedriver.exe");
		logger = report.createTest("Set up script for flight booking");
dr = lib.launchBrowser(browser);
  if (dr != null) {
			MediaEntityModelProvider img = MediaEntityBuilder
					.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
			logger.log(Status.PASS, "browser launch successfully and url entered", img);
		}
		else {			
			logger.log(Status.FAIL, "login  page  is not displayed");			 
			throw new SkipException("Launch application failed");
		}	
		}
@Test(dataProvider = "getData", priority = 0)
public void browse_script(String userName,String Pass) throws Throwable
{
	// ********Local Variables***********//
	
				MediaEntityModelProvider img;
				// **********************************//
				
				logger = report
						.createTest("Verify login functionality with username=" +userName);
				try{
					logger.log(Status.INFO, "Browse test starts");
					String mega=or.getProperty("username");

					System.out.println(mega);
					String url = dr.getCurrentUrl();
					if(lib.isElementPresent(dr, or.getProperty("username")))
					{
						dr.findElement(By.xpath(or.getProperty("username"))).sendKeys(userName);
						img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
						logger.log(Status.PASS, " username enter successfully", img);
						
						dr.findElement(By.xpath(or.getProperty("Button"))).click();
						Thread.sleep(5000);
						
							img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
							logger.log(Status.PASS, " username should enter successfully and enter password", img);
							Thread.sleep(5000);
							dr.findElement(By.xpath(or.getProperty("password"))).sendKeys(Pass);
							
							dr.findElement(By.xpath(or.getProperty("btn"))).click();
							if(url.contains("https://myaccount.google.com/signinoptions/recovery-options-collection?utm_source=Web&utm_medium=Web&utm_campaign=interstitial&oev=lytf%3D7%26wvtx%3D2%26trs%3Dli%26stel%3D1&hl=en&service=mail&continue=https://accounts.google.com/ServiceLogin?continue%3Dhttps%253A%252F%252Fmail.google.com%252Fmail%252F%26service%3Dmail%26hl%3Den%26authuser%3D0%26passive%3Dtrue%26sarp%3D1%26aodrpl%3D1%26checkedDomains%3Dyoutube%26checkConnection%3Dyoutube%253A408%253A0%26pstMsg%3D1&rapt=AEjHL4O2xcKk8KQYq7UfOQZrOLsOw0raB2O_IDNhD2VFa6SpxncqI9izpsvjPDptIsddeiHOwm_seliveJqr_GgKVwdy96MXWQ&pli=1"))
							{
								img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
								logger.log(Status.PASS, " gmail login successfully", img);
			                 }
							else{
								img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
								logger.log(Status.FAIL, "password is not valid", img);
								}
						}	
				}catch(Exception e)
				{
					img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
					logger.log(Status.FAIL, "Login failed. Exception:"+e.getMessage(), img);
					
					
				}
	}
@AfterTest
public void teardown()
{
	dr.quit();
}

@DataProvider
public Object[][] getData() throws IOException {

	int rows = xl.getrowcount("gmail");
	int cols = xl.getColumncount("gmail");

	System.out.println("rows="+rows+" cols="+cols);
	Object[][] data = new Object[rows - 1][cols];

	for (int r = 2; r <= rows; r++) {
		for (int c = 1; c <= cols; c++) {
			data[r - 2][c-1] = xl.getCellData("gmail", r, c );
		}
	}
	return data;

}
}
