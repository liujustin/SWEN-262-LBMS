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
	public String Get_Time(){
		DateFormat df = new SimpleDateFormat("dd/MM/yy,HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String time = ("datetime," + df.format(cal.getTime()));
		System.out.println(time);
		return time;
	}

	public void Generate_Report(int days){
		date =
	}

	
}