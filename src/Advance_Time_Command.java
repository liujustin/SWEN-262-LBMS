//FILE::Book_Loan.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017

public class Advance_Time_Command implements Command {
    private int days;
    private int hours;

    public Advance_Time_Command(int days, int hours) {
        this.days = days;
        this.hours = hours;
    }
    @Override
    public void execute() {
        //Main.sk.Advance_Time(this.days,this.hours);
    }
}
