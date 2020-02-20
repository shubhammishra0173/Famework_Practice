package library;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import config.config_File;


public class ApplicationLibrary 
{

	public WebDriver launchBrowser(String Browser)
	{
		WebDriver dr=null;
		if(Browser.equalsIgnoreCase("Chrome"))
		{
			ChromeOptions op= new ChromeOptions();
			op.addArguments("--disable-infobars");
			op.addArguments("--start-maximized");
			dr= new ChromeDriver(op);
			dr.manage().timeouts().implicitlyWait(config_File.implicitwait, TimeUnit.SECONDS);
			dr.get(config_File.url);			
			
		}else if(Browser.equalsIgnoreCase("Firefox"))
		{
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "D:\\browser.log");
			dr= new FirefoxDriver();
		}
		else if(Browser.equalsIgnoreCase("ie"))
		{
			dr= new InternetExplorerDriver();
		}else if(Browser.equalsIgnoreCase("edge"))
		{
			dr= new EdgeDriver();
		}else if(Browser.equalsIgnoreCase("opera"))
		{
			OperaOptions op= new OperaOptions();
			op.setBinary("C:\\Users\\AB46772\\AppData\\Local\\Programs\\Opera\\launcher.exe");
			dr= new OperaDriver(op);
		}
		
		return dr;
	}
		
	
	
	public  String captureScreenShot(WebDriver dr, String name) throws IOException
	{		
		TakesScreenshot scrShot =((TakesScreenshot)dr);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		String  path=config_File.screenShotPath+name+".jpg";
		File DestFile=new File(path);		
		FileUtils.copyFile(SrcFile, DestFile);		
		return path;
	}
	
	
	public boolean isElementPresent(WebDriver dr, String element_xpath)
	{
		if(dr.findElements(By.xpath(element_xpath)).size()<=1)
		{
			return true;
		}else
		{
			return false;
		}
	}
}
