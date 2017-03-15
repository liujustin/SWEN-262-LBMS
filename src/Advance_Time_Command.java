/**
 * Created by adamn on 3/2/2017.
 */
public class Advance_Time_Command implements Command {
    private LBMS_StatisticsKeeper timeKeeper;

    public Advance_Time_Command(LBMS_StatisticsKeeper timeKeeper){
        this.timeKeeper = timeKeeper;
    }

    public void execute() {
        //this.timeKeeper.Advance_Time();
    }
}
