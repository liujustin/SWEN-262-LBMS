/**
 * Created by adamn on 3/2/2017.
 */
public class Begin_Visit_Command implements Command {
    private LBMS_VisitorKeeper sys;
    private Visitor v;

    public Begin_Visit_Command(LBMS_VisitorKeeper sys, Visitor v){
        this.sys = sys;
        this.v = v;
    }

    public void execute() {
        try {
            this.sys.beginVisit(v.getVisitor_ID());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
