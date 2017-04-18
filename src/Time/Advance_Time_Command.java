package Time;//FILE::Time.Advance_Time_Command.java
//AUTHOR::Ryan Connors, Justin Liu
//DATE::Feb.25.2017

import Network.Command;

public class Advance_Time_Command implements Command {
    private int days;
    private int hours;
    Time_Operations sk = Time_Operations.getInstance();

    public Advance_Time_Command(int days, int hours) {
        this.days = days;
        this.hours = hours;
    }
    @Override
    public String execute(){
        try {
            sk.advanceDay(this.days);
            sk.advanceHour(this.hours);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
