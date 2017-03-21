/**
 * Created by adamn on 3/2/2017.
 */
public class Begin_Visit_Command implements Command {
    private Long  vID;

    public Begin_Visit_Command(Long vID){
        this.vID = vID;
    }



    public void execute() {
        try {
            Main.vk.beginVisit(this.vID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
