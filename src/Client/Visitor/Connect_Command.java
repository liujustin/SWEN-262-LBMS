package Client.Visitor;

import Network.Command;

/**
 * Created by adamn on 4/16/2017.
 */
public class Connect_Command implements Command {
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();
    private int test;

    @Override
    public String execute() {
        visitorKeeper.startConnection();
        return "";
    }
}
