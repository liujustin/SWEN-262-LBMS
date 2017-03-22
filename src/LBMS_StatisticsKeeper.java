//FILE::LBMS_StatisticsKeeper.java
//AUTHOR::Kevin.P.Barnett
//DATE::Mar.04.2017

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LBMS_StatisticsKeeper
{
	/**
	 * gets the datetime for the datetime command
	 */
	public void Get_Time(){
		DateFormat df = new SimpleDateFormat("dd/MM/yy,HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println("datetime," + df.format(cal.getTime()));
	}

	
}