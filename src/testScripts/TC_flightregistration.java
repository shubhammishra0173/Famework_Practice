

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

	public class TC_flightregistration extends Driver
	{
		
		WebDriver dr = null;
		WebDriverWait wait = null;

		@BeforeTest
		@Parameters({ "browser" })
		public void setup(String browser) throws IOException {
			System.setProperty("webdriver.chrome.driver","E:\\selenium class\\Framework_Morning\\drivers\\chromedriver.exe");
			logger = report.createTest("Set up script for guest user");

			dr = lib.launchBrowser(browser);

			if (dr != null) {
				MediaEntityModelProvider img = MediaEntityBuilder
						.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
				logger.log(Status.PASS, "Flipkart Home screen is displayed successfully", img);
				wait = new WebDriverWait(dr, config_File.explicitwait);
				dr.findElement(By.xpath(or.getProperty("link"))).click();
			} else {			
				logger.log(Status.FAIL, "Registration page  is not displayed");			 
				throw new SkipException("Launch application failed");
			}

		}
		
		@Test(dataProvider = "getData", priority = 0)
		public void flightregistration(String Firstname, String Lastname,String mbl,String mail,String Add,String City,String state,String postal,String country,String myname,String pass,String cnfmpass) throws IOException
		{
			// ********Local Variables***********//
			
			MediaEntityModelProvider img;
			// **********************************//
			
			logger = report
					.createTest("Verify login functionality with username=" +Firstname);
			try{
				
				logger.log(Status.INFO, "Login test starts");
				
				//Step 1
				if(lib.isElementPresent(dr, or.getProperty("name")))
				{
				dr.findElement(By.xpath(or.getProperty("name"))).sendKeys(Firstname);
				
				dr.findElement(By.xpath(or.getProperty("lastname"))).sendKeys(Lastname);
				dr.findElement(By.xpath(or.getProperty("mobile"))).sendKeys(mbl);
				dr.findElement(By.xpath(or.getProperty("mail"))).sendKeys(mail);
				dr.findElement(By.xpath(or.getProperty("address"))).sendKeys(Add);
				dr.findElement(By.xpath(or.getProperty("city"))).sendKeys(City);
				dr.findElement(By.xpath(or.getProperty("state"))).sendKeys(state);
				dr.findElement(By.xpath(or.getProperty("postal"))).sendKeys(postal);
				dr.findElement(By.xpath(or.getProperty("country"))).sendKeys(country);
				dr.findElement(By.xpath(or.getProperty("myname"))).sendKeys(myname);
				dr.findElement(By.xpath(or.getProperty("pass"))).sendKeys(pass);
				dr.findElement(By.xpath(or.getProperty("cnfmpass"))).sendKeys(cnfmpass);
				
				img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
				logger.log(Status.PASS, "Username and password entered successfully", img);
				
				
				logger = report
						.createTest("Verify login functionality with username=" +Firstname);
				dr.findElement(By.xpath(or.getProperty("submit"))).click();
			
	             String url = dr.getCurrentUrl();
	             if(url.contains("http://newtours.demoaut.com/create_account_success.php")){
				img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
				logger.log(Status.PASS, "user registered succcessfully", img);
	             }
	             else{
	            	 logger.log(Status.FAIL, "user not registered succcessfully your test cases has failed please try again",img);
	             }
				
				//Step 2 - Click on Login button and verify user's first name is displayed on homepage
				//dr.findElement(By.xpath(or.getProperty("Login_btn"))).click();
				//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty("Firstname_P1")+firstname+or.getProperty("Firstname_P2"))));
				img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
				logger.log(Status.PASS, "Successfully logged in", img);

				}else
				{
					img = MediaEntityBuilder.createScreenCaptureFromPath(lib.captureScreenShot(dr, ul.timeStamp())).build();
					logger.log(Status.FAIL, "Username is not displayed", img);
					
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

			int rows = xl.getrowcount("flightregistration");
			int cols = xl.getColumncount("flightregistration");

			System.out.println("rows="+rows+" cols="+cols);
			Object[][] data = new Object[rows - 1][cols];

			for (int r = 2; r <= rows; r++) {
				for (int c = 1; c <= cols; c++) {
					data[r - 2][c-1] = xl.getCellData("flightregistration", r, c );
				}
			}
			return data;

		}
	}
