//FILE::Book_Loan.java
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
    public void execute() {
        //Main.sk.Generate_Report(this.days);
    }
}
