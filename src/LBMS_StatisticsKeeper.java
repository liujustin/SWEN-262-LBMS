//FILE::LBMS_StatisticsKeeper.java
//AUTHOR::Justin Liu, Adam Nowak
//DATE::Mar.04.2017

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LBMS_StatisticsKeeper
{
	private static Date date;
	private Calendar calendar;
	private static int seconds = 0;
	static Timer timer = new Timer();
	static TimerTask task = new TimerTask() {
		@Override
		public void run() {
			seconds++;
		}
	};

	public LBMS_StatisticsKeeper(){
		this.calendar = Calendar.getInstance();
		this.date = new Date();
	}

	/**
	 * gets the datetime for the datetime command
	 */
	public static String Get_Time() {
		if(seconds == 0) {
			timer.scheduleAtFixedRate(task, 1000, 1000);
		}
		DateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy,HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, seconds);
		date.setTime(c.getTimeInMillis());
		String output = dateFormat.format(date);
		return output;
	}
	public void printTime(){
		String d2 = LBMS_StatisticsKeeper.Get_Time();
		System.out.println("datetime," + d2);
	}
	public void advanceDay(int days)throws Exception{
		if(days < 0 || days > 7 ) {
			throw new Exception("advance,invalid-number-of-days," + days);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		date.setTime(c.getTimeInMillis());

	}

	public void advanceHour(int hours)throws Exception{
		if(hours > 23 || hours < 0 ){
			throw new Exception("advance,invalid-number-of-hours" + hours);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hours);
		date.setTime(c.getTimeInMillis());
	}
}