//FILE::Advance_Time_Command.java
//AUTHOR::Ryan Connors, Justin Liu
//DATE::Feb.25.2017

public class Advance_Time_Command implements Command {
    private int days;
    private int hours;

    public Advance_Time_Command(int days, int hours) {
        this.days = days;
        this.hours = hours;
    }
    @Override
    public void execute(){
        try {
            Main.sk.advanceDay(this.days);
            Main.sk.advanceHour(this.hours);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
