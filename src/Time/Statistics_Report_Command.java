package Time;

import Network.Command;

//FILE:: Statistics_Report_Command.java
//AUTHOR::Adam Nowak
//DATE::Apr.15.2017
public class Statistics_Report_Command  implements Command{
    private int days;

    public Statistics_Report_Command(int days){ this.days = days; }

    @Override
    public String execute() {
        Statistics_Report.generateReport(this.days);
        return "";
    }
}
