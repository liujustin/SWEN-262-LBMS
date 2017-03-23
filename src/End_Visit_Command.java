//FILE::End_Visit_Command.java
//AUTHOR::Kevin.P.Barnett
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