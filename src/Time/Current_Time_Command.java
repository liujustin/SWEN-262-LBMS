package Time;

import Network.Command;

//FILE::Time.Current_Time_Command.java
//AUTHOR::Ryan Connors, Justin Liu
//DATE::Feb.25.2017
public class Current_Time_Command implements Command {
    Time_Operations statisticsKeeper = Time_Operations.getInstance();

    public Current_Time_Command(){
    }

    @Override
    public String execute() {
        return statisticsKeeper.printTime();
    }
}
