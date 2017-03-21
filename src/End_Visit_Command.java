//FILE::Book_Loan.java
//AUTHOR::Ryan Connors
//DATE::Feb.25.2017
public class End_Visit_Command implements Command {

    private Long vID;

    /**
     *
     * @param vID
     */
    public End_Visit_Command(Long vID){
        this.vID = vID;
    }

    @Override
    public void execute() {
        try {
            Main.vk.endVisit(this.vID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}