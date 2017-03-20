/**
 * Created by adamn on 3/2/2017.
 */
public class Current_Time_Command implements Command {
    private LBMS_StatisticsKeeper sys;

    public Current_Time_Command(LBMS_StatisticsKeeper sys){
        this.sys = sys;
    }

    public void execute() {
        this.sys.Get_Time();
    }
}
