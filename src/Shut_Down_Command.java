/**
 * Created by Ryan on 3/17/2017.
 */
public class Shut_Down_Command implements Command {
    private LBMS_VisitorKeeper sys;

    public Shut_Down_Command(LBMS_VisitorKeeper sys) {
        this.sys = sys;
    }

    public void execute() {
        this.sys.shutdown();
    }
}
