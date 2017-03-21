/**
 * Created by adamn on 3/2/2017.
 */
public class End_Visit_Command implements Command {
    private Long vID;

    public End_Visit_Command(Long vID){
        this.vID = vID;
    }

    public void execute() {
        try {
            Main.vk.endVisit(this.vID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}