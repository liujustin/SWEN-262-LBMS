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
        //this.sys.End_Visit(v);
    }
}
