/**
 * Created by adamn on 3/2/2017.
 */
public class End_Visit_Command implements Command {
    private Visitor v;

    public End_Visit_Command(Visitor v){
        this.v = v;
    }

    public void execute() {
        try {
            Main.vk.endVisit(v.getVisitor_ID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
