package Time;//FILE::Time.LBMS_StatisticsKeeper.java
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
	private static final LBMS_StatisticsKeeper timekeeper = new LBMS_StatisticsKeeper();
	private static Date date;
	private Calendar calendar;
	private static boolean isOpen;
	private static boolean starttimer = true;
	private static int seconds = 0;
	private static int skiptime = 0;
	static Timer timer = new Timer();
	static TimerTask task = new TimerTask() {
		@Override
		public void run() {
			seconds++;
		}
	};

	private LBMS_StatisticsKeeper(){
		this.calendar = Calendar.getInstance();
		this.date = new Date();
	}

	public static LBMS_StatisticsKeeper getInstance(){
		return timekeeper;
	}

	/**
	 * gets the current datetime. Used throughout the system
	 */
	public static String Get_Time() {

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, -skiptime);
		if(starttimer) {
			starttimer = false;
			timer.scheduleAtFixedRate(task, 1000, 1000);
		}

		skiptime = seconds;
		DateFormat dateFormat = new SimpleDateFormat ("MM/dd/yyyy,HH:mm:ss");
		c.add(Calendar.SECOND, skiptime);
		date.setTime(c.getTimeInMillis());
		String output = dateFormat.format(date);
		return output;
	}

	public static boolean check_Time(){
		Timer timer = new Timer();
		long interval = (1000);
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				String currentTime = Get_Time().split(",")[1];
				if (currentTime.compareTo( "19:00:00") > 0 || currentTime.compareTo("08:00:00") < 0){
					isOpen = false;
				}
				else {
					isOpen = true;
				}
			}
		},date,interval);

		return isOpen;
	}


	/**
	 * Prints the GUI.time for the datetime command
	 */
	public String printTime(){
		String d2 = LBMS_StatisticsKeeper.Get_Time();
		return "datetime," + d2;
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
			return isOpen = false;
		}else{
			return isOpen = true;
		}
	}

	/**
	 *
	 * @param hours
	 * @throws Exception
     */
	public void advanceHour(int hours)throws Exception{
		if(hours > 23 || hours < 0 ){
			throw new Exception("advance,invalid-number-of-hours," + hours);
		}
		calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		date.setTime(calendar.getTimeInMillis());
		System.out.println("advance,success;");
	}

}