//FILE::LBMS_StatisticsKeeper.java
//AUTHOR::Justin Liu
//DATE::Mar.04.2017

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LBMS_StatisticsKeeper
{
	private Date date;
	private Calendar calendar;

	public LBMS_StatisticsKeeper(){
		this.calendar = Calendar.getInstance();
		this.date = new Date();
	}

	/**
	 * gets the datetime for the datetime command
	 */

	public String Get_Time() {
		DateFormat dateFormat = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
		this.date = new Date();
		String output = dateFormat.format(date);
		System.out.println(output);
		return output;
	}

	public void advanceDay(int days){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		date.setTime(c.getTimeInMillis());

	}

	public void advanceHour(int hours){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hours);
		date.setTime(c.getTimeInMillis());
	}

	
}