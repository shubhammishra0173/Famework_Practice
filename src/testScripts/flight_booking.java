package testScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;

import baseClass.Driver;

public class flight_booking extends Driver {
	WebDriver dr = null;
	WebDriverWait wait = null;

	@BeforeTest
	@Parameters({ "browser" })
	public void setup(String browser) throws IOException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Shiv\\selenium class\\flipkart\\chromedriver.exe");
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
	public void flightbooking(String Firstname, String Lastname,String mbl,String mail,String Add,String City,String state,String postal,String country,String myname,String pass) throws Throwable
	
	{
		MediaEntityModelProvider img;
		// **********************************//
		
		logger = report
				.createTest("Verify login functionality with username=" +Firstname);
		
			
			logger.log(Status.INFO, "Login test starts");
			
			//Step 1
			String url = dr.getCurrentUrl();
			if(url.contains("http://newtours.demoaut.com"))
			{
			dr.findElement(By.xpath(or.getProperty("user"))).sendKeys(Firstname);
			dr.findElement(By.xpath(or.getProperty("pass"))).sendKeys(Lastname);
			dr.findElement(By.xpath(or.getProperty("click"))).click();
			img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
			logger.log(Status.PASS, "Username and password entered successfully", img);
			}
			if(url.contains("http://newtours.demoaut.com/mercurysignon.php")){
				img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
				logger.log(Status.FAIL, "Username and password not entered successfully", img);
				}
			//step2
			String url1 = dr.getCurrentUrl();
			if(url1.contains("http://newtours.demoaut.com/mercuryreservation.php?osCsid=8aa4bb1bce03c7ae25f66ef7cf91d423")){
			
			dr.findElement(By.xpath(or.getProperty("oneway"))).click();
			dr.findElement(By.xpath(or.getProperty("count"))).sendKeys(mbl);
			dr.findElement(By.xpath(or.getProperty("source"))).sendKeys(mail);
			dr.findElement(By.xpath(or.getProperty("month"))).sendKeys(Add);
			
			dr.findElement(By.xpath(or.getProperty("day"))).sendKeys(City);
			dr.findElement(By.xpath(or.getProperty("deestination"))).sendKeys(state);
			dr.findElement(By.xpath(or.getProperty("Tomonth"))).sendKeys(postal);
			dr.findElement(By.xpath(or.getProperty("Today"))).sendKeys(country);
			dr.findElement(By.xpath(or.getProperty("Business"))).click();
			dr.findElement(By.xpath(or.getProperty("Airline"))).sendKeys(myname);
			img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
			logger.log(Status.PASS, "Page loaded successfully", img);
			dr.findElement(By.xpath(or.getProperty("button"))).click();
			img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
			logger.log(Status.PASS, "PAge loaded  successfully", img);
			
			dr.findElement(By.xpath(or.getProperty("flightselect"))).click();
			dr.findElement(By.xpath(or.getProperty("continue"))).click();
			dr.findElement(By.xpath(or.getProperty("passengerfirstname"))).sendKeys(pass);
			dr.findElement(By.xpath(or.getProperty("purchase"))).click();
			if(url.contains("http://newtours.demoaut.com/mercurypurchase2.php"))
			img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
			logger.log(Status.PASS, "Username and password not entered successfully", img);
			}
			
			
	}
	
			@DataProvider
			public Object[][] getData() throws IOException {

				int rows = xl.getrowcount("flightbooking");
				int cols = xl.getColumncount("flightbooking");

				System.out.println("rows="+rows+" cols="+cols);
				Object[][] data = new Object[rows - 1][cols];

				for (int r = 2; r <= rows; r++) {
					for (int c = 1; c <= cols; c++) {
						data[r - 2][c-1] = xl.getCellData("flightbooking", r, c );
					}
				}
				return data;

			}
		}
