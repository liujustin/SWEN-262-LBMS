import java.text.ParseException;

//FILE::Library_Close_Thread.java
//AUTHOR::Adam Nowak
//DATE::Apr.1.2017

public class Library_Close_Thread implements Runnable {
    @Override
    public void run() {
        try {
            if(!LBMS_StatisticsKeeper.getIsOpen(LBMS_StatisticsKeeper.Get_Time())){
                LBMS_VisitorKeeper.getActiveVisitors().clear();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
