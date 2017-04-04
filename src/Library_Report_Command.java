//FILE::Library_Report_Command.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Library_Report_Command implements Command {

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
        //Main.sk.Generate_Report(this.days);
        return "";
    }
}
