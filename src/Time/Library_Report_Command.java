package Time;

import Network.Command;

//FILE::Time.Library_Report_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Library_Report_Command implements Command {
    Time_Operations statisticsKeeper = Time_Operations.getInstance();

    private int days;

    /**
     *
     * @param days
     */
    public Library_Report_Command(int days){
        this.days = days;
    }

    @Override
    public String execute() {
        //Client_Access_View.sk.Generate_Report(this.days);
        return "";
    }
}
