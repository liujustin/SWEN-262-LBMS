//FILE::Book_Loan.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class Shut_Down_Command implements Command {
    private LBMS_VisitorKeeper sys;

    /**
     *
     * @param sys
     */
    public Shut_Down_Command(LBMS_VisitorKeeper sys) {
        this.sys = sys;
    }

    @Override
    public void execute() {
        this.sys.shutdown();
    }
}
