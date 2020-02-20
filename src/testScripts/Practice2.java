package testScripts;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Practice2 {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException 
	{
		Class<?> c = Class.forName("library.Testing");
		Object scriptClassObj = c.newInstance();
		
		/*Field mTC_Name = c.getDeclaredField("mstrTC_Name");
		mTC_Name.set(scriptClassObj, mstrTestCase);

		// ---------------Setting TestCase Descriptions
		Field mTC_Desc = c.getDeclaredField("mstrTC_Desc");
		mTC_Desc.set(scriptClassObj, msrtTC_desc);
		// ---------------Setting Module Name

		Field mTC_Module = c.getDeclaredField("mstrModuleName");
		mTC_Module.set(scriptClassObj, msrtModuleName);

		Field mwebproxy=c.getDeclaredField("proxy");
		mwebproxy.set(scriptClassObj,browserproxy);

		Field mwebpath = c.getDeclaredField("filePath");
		mwebpath.set(scriptClassObj, filePath);

		Field mwebTime = c.getDeclaredField("startTime");
		mwebTime.set(scriptClassObj, startTime);

		Field mwebdriver = c.getDeclaredField("webdriver");
		mwebdriver.set(scriptClassObj, driver);

		Field mwebdeviceName = c.getDeclaredField("Browsername");
		mwebdeviceName.set(scriptClassObj, BrowserName);*/
		
		Field mwebuserName = c.getDeclaredField("message");
		mwebuserName.set(scriptClassObj, "hdfgjsdfjjgldfjgl");

		Method m = c.getDeclaredMethod("test");
		m.invoke(scriptClassObj);

	}

}
