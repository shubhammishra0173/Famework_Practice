package testScripts;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utility.Xls_Reader;

public class DynamicFunCalling {
	
	@Parameters({"browser"})
	@Test
	public  void driver(String browser) throws Exception
	{
		
		Xls_Reader xl= new Xls_Reader(System.getProperty("user.dir")+"\\src\\testdata\\TestData1.xls");
		String tc="Testing";
		Class<?> c = Class.forName("library."+tc);
		Object scriptClassObj = c.newInstance();
		
		Field mwebuserName = c.getDeclaredField("browser");
		mwebuserName.set(scriptClassObj, browser);
		
		for(int r=2; r<=xl.getrowcount("Keywords");r++ )
		{
			//System.out.println(xl.getCellData("Keywords", r, 1));
			Method m = c.getDeclaredMethod(xl.getCellData("Keywords", r, 1));
			m.invoke(scriptClassObj);
		}
	}
	
	

}
