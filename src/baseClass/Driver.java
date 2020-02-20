package baseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import config.config_File;
import library.ApplicationLibrary;
import utility.Xls_Reader;
import utility.genericLib;


public class Driver 
{
	public static ExtentHtmlReporter reporter;
	public static ExtentReports report;
	public static ExtentTest logger;
	public  String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	public genericLib ul= new genericLib();
	public ApplicationLibrary lib= new ApplicationLibrary();
	public static Properties or;
	public static Xls_Reader xl;
	
	@BeforeSuite
	public void initialize() throws IOException
	{
		reporter=new ExtentHtmlReporter(config_File.reportPath+"AutomationReport_"+timeStamp+".html");
		report= new ExtentReports();
		report.attachReporter(reporter);
		or= new Properties();		
		FileInputStream file= new FileInputStream(config_File.or);
		
		or.load(file);
		
		xl= new Xls_Reader(config_File.testdata);

		
		
	}
	
	
	
	@AfterSuite
	public void tearDown()
	{
		report.flush();
		
	}

}
