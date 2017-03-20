/**
 * Created by adamn on 3/2/2017.
 */
public class Library_Report_Command implements Command {

    private int days;

    public Library_Report_Command(int days){
        this.days = days;
    }

    public void execute() {
        //Main.sk.Generate_Report(this.days);
    }
}
