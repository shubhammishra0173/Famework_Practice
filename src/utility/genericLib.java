package utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class genericLib 
{
	public String timeStamp()
	{
		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	}

}
