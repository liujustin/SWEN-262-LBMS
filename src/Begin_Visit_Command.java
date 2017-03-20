/**
 * Created by adamn on 3/2/2017.
 */
public class Begin_Visit_Command implements Command {
    private Visitor v;

    public Begin_Visit_Command(Visitor v){
        this.v = v;
    }



    public void execute() {
        try {
            Main.vk.beginVisit(v.getVisitor_ID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
