package testScripts;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Practice {

	public static void main(String[] args) throws IOException 
	{

		String timeStamp = 
		new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

		ExtentHtmlReporter report= new ExtentHtmlReporter("D:\\02072018Morning\\Framework_Morning\\src\\results\\Automation_Report_"+timeStamp+".html");
		ExtentReports repo= new ExtentReports();
		repo.attachReporter(report);
		
		
		ExtentTest tc1=repo.createTest("To test login functionality");
		tc1.log(Status.INFO, "Set system property");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-infobars");
		op.addArguments("--start-maximized");
		WebDriver dr = new ChromeDriver(op);
		tc1.log(Status.PASS, "Browser launched successfully");
		
		dr.get("http://www.newtours.demoaut.com/");	
		tc1.log(Status.PASS, "Successfully navigated to application URL");
		
		String screenshotName=System.getProperty("user.dir")+"\\src\\results\\screenshots\\"+timeStamp+".jpg";
		
		File src= ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(screenshotName));
		
		//tc1.addScreenCaptureFromPath(screenshotName);
		
		tc1.fail("Step failed because application not launched", MediaEntityBuilder.createScreenCaptureFromPath(screenshotName).build());
		
		
		tc1=repo.createTest("To test login functionality2");
		tc1.log(Status.PASS, "Browser launched successfully");
		
		
		repo.flush();
		
		dr.quit();

	}

}
