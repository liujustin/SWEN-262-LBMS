package Shutdown;

import Time.LBMS_StatisticsKeeper;
import Client.Visitor.LBMS_VisitorKeeper;

//FILE::Shutdown.Library_Close_Thread.java
//AUTHOR::Adam Nowak
//DATE::Apr.1.2017

public class Library_Close_Thread implements Runnable {
    @Override
    public void run() {
        try {
            if(!LBMS_StatisticsKeeper.getIsopen(LBMS_StatisticsKeeper.Get_Time())){
                LBMS_VisitorKeeper.getActiveVisitors().clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
