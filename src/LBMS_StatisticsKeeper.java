//FILE::LBMS_StatisticsKeeper.java
//AUTHOR::Justin Liu, Adam Nowak
//DATE::Mar.04.2017

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class LBMS_StatisticsKeeper
{
	private static Date date;
	private Calendar calendar;
	private static boolean isopen;
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
<<<<<<< HEAD
=======
		this.isopen = isopen;



>>>>>>> eed5af2e2e2fcf1736573891847190c5526a147e
	}

	/**
	 * gets the current datetime. Used throughout the system
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

	/**
	 * Prints the time for the datetime command
	 */
	public void printTime(){
		String d2 = LBMS_StatisticsKeeper.Get_Time();
		System.out.println("datetime," + d2);
	}

	/**
	 *
	 * @param days
	 * @throws Exception
     */
	public void advanceDay(int days)throws Exception{
		if(days < 0 || days > 7 ) {
			throw new Exception("advance,invalid-number-of-days," + days);
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, days);
		date.setTime(c.getTimeInMillis());

	}
	public static boolean getIsopen(String time)throws ParseException{

		time = time.substring(11,19);
		Date time1 = new SimpleDateFormat("HH:mm:ss").parse(time);
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(time1);
		Date currdate = calendar1.getTime();

		String close = "19:00:00";
		Date closetime = new SimpleDateFormat("HH:mm:ss").parse(close);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(closetime);

		String open = "08:00:00";
		Date opentime = new SimpleDateFormat("HH:mm:ss").parse(open);
		Calendar calendar3 = Calendar.getInstance();
		calendar3.setTime(opentime);

		if(currdate.after(calendar2.getTime()) || currdate.before(calendar3.getTime())){
			return isopen = false;
		}else{
			return isopen = true;
		}
	}

	/**
	 *
	 * @param hours
	 * @throws Exception
     */
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