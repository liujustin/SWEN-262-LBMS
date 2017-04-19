package Shutdown;

import Client.Visitor.Visitor_Operations;
import Time.Time_Operations;

//FILE::Shutdown.Library_Close_Thread.java
//AUTHOR::Adam Nowak
//DATE::Apr.1.2017

public class Library_Close_Thread implements Runnable {
    Visitor_Operations visitorOperations = Visitor_Operations.getInstance();
    @Override
    public void run() {
        try {
            if(! Time_Operations.getIsopen(Time_Operations.Get_Time())){
                visitorOperations.getActiveVisitors().clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
