package testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","E:\\selenium class\\TestNG\\chromedriver.exe");
		
		ChromeOptions po = new ChromeOptions();
		po.addArguments("--disable-infobar");
		po.addArguments("--start-maximized");
		WebDriver dr= new ChromeDriver();
		dr.manage().window().maximize();
		dr.get("https://www.flipkart.com/");
	}

}
