/**
 * Created by adamn on 3/2/2017.
 */
public class End_Visit_Command implements Command {
    private Visitor v;
    private LBMS_VisitorKeeper sys;

    public End_Visit_Command(Visitor v, LBMS_VisitorKeeper sys){
        this.v = v;
        this.sys = sys;
    }

    public void execute() {
        try {
            this.sys.endVisit(v.getVisitor_ID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
