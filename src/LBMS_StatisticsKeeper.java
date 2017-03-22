//FILE::LBMS_TimeKeeper.java
//AUTHOR::Kevin.P.Barnett
//DATE::Mar.04.2017

public class LBMS_StatisticsKeeper
{
	public Date Get_Time(){
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return df.format(cal.getTime());
	}

	
}