/**
 * Created by adamn on 3/2/2017.
 */
public class Library_Report_Command implements Command {
    private LBMS_StatisticsKeeper sys;

    public Library_Report_Command(LBMS_StatisticsKeeper sys){
        this.sys = sys;
    }

    public void execute() {
        //this.sys.Generate_Report();
    }
}
