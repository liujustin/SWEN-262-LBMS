package Client.Visitor;

import Network.Command;

/**
 * Created by adamn on 4/16/2017.
 */
public class Connect_Command implements Command {
    LBMS_VisitorKeeper visitorKeeper = LBMS_VisitorKeeper.getInstance();

    @Override
    public String execute() {
        System.out.println(visitorKeeper.startConnection());
        return "";
    }
}
